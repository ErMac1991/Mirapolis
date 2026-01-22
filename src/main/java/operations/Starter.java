package operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import constructors.*;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Starter {
    ObjectMapper objectMapper = new ObjectMapper();
    CharacterCreator character = new CharacterCreator();
    CharacterCreator charactersChanges = new CharacterCreator();
    QuestConstructor quest = new QuestConstructor();
    StageConstructor stage = new StageConstructor();
    EnemyHumanCreator enemyHuman = new EnemyHumanCreator();
    EnemyMachineCreator enemyMachineCreator = new EnemyMachineCreator();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\СистемныеФайлы\\ActionsQueue.txt");
    String updateData; // Строка изменений
    String typeOfSubjectFromArgs;
    String userLoginFromArgs;

    public File getActionsQueueFile() {
        return actionsQueueFile;
    }

    public void updateGameData() throws IOException {

        FileManager.deleteEmptyFile(actionsQueueFile);
        if (!Checks.isSystemUpdated(actionsQueueFile)) {
            System.out.println("Обновления игровых файлов не найдены");
        }
        Checks.isSystemUpdated(actionsQueueFile);
        //todo вставить проверку на пустую строку в файле и метод на удаление пустой строки
        updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);

        typeOfSubjectFromArgs = updateData.split("\"")[3];

        switch (typeOfSubjectFromArgs) { // тип изменяемого субъекта: персонаж/противник/квест

            case "newCharacter":{
                System.out.println("Тип субъекта - Новый персонаж");
                //updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
                userLoginFromArgs = updateData.split("\"")[7];
                System.out.println("updateData = " + updateData);
                CharacterCreator.createCharacter(userLoginFromArgs);

                // Очистка переменных
                updateData = null;
                break;}

            case "character":{
                System.out.println("Тип субъекта - Существующий персонаж");
                updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
                System.out.println("updateData = " + updateData);
                character.setUserLogin(updateData.split("\"")[7]);
                System.out.println("userNameFromArgs = " + character.getUserLogin());
                character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

                charactersChanges = character;
                charactersChanges = FileManager.parseStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
                if (!userLoginFromArgs.equals(charactersChanges.getUserLogin())) {
                    System.out.println("Логин игрока из файла: " + userLoginFromArgs + " не совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                    return;
                }
                System.out.println("Логин игрока из файла: " + userLoginFromArgs + " совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                System.out.println("Квест персонажа " + character.getUserLogin() + " до изменений: " + character.getQuest());
                System.out.println("Квест в изменениях: " + charactersChanges.getQuest());
                character = CharacterCreator.updateCharacterPojo(character, charactersChanges);//Внесение изменений в Pojo персонажа слиянием с объектом изменений
                System.out.println("Квест персонажа " + character.getUserLogin() + " после изменений: " + character.getQuest());
                FileManager.fillPojoToJsonFile(character);// Перенос данных из Pojo персонажа в Json файл персонажа

                // Очистка переменных
                charactersChanges = null;
                character = null;
                updateData = null;
                break;}

            case "newVacantQuest":{
                System.out.println("Тип субъекта - Новый Вакантный Квест");
                QuestConstructor.generateVacantQuest(quest);
                System.out.println("Новый вакантный квест создан");
                break;}

            case "vacantQuestsList":{
                System.out.println("Тип субъекта - Список Вакантных Квестов");
                String vacantQuests;
                character.setUserLogin(updateData.split("\"")[7]);
                System.out.println("userNameFromArgs = " + character.getUserLogin());
                character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

                List<String> vacantQuestsList = Formulas.getVacantQuestsList(objectMapper, quest, character);
                if (Checks.hasDuplicates(vacantQuestsList)){
                    vacantQuestsList = Formulas.deleteDuplicates(vacantQuestsList);
                }

                vacantQuests = QuestConstructor.getVacantQuests(vacantQuestsList);

                System.out.println("Список вакантных квестов, доступных персонажу " + character.getUserLogin() +
                        ": " + vacantQuests);
                break;}

            case "newReceivedQuest":{ // в команде передавать логин игрока, берущего квест, ID вакантного квеста
                System.out.println("Тип субъекта - Новый Полученный Квест");

                character.setUserLogin(updateData.split("\"")[7]);
                System.out.println("userNameFromArgs = " + character.getUserLogin());
                character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

                quest.setQuestID(Integer.parseInt(updateData.split("\"")[11]));
                System.out.println("questIDFromArgs = " + quest.getQuestID());

                // Проверка на возможность взять квест по уровню (?)
                quest = QuestConstructor.chooseQuest(objectMapper, quest);
                System.out.println("Выбран вакантный квест " + quest.getQuestID());
                QuestConstructor.generateReceivedQuest(quest, character); // создаём и заполняем файл принятого квеста
                System.out.println("Создан файл принятого квеста");
                StageConstructor.generateAllStages(quest,character,stage); // создаём и заполняем базовые параметры этапов квеста
                Formulas.calculateStagesParameters(quest, stage);

//todo прописать очередь методов для генерации взятого квеста
                System.out.println("Квест принят");
                // Удаление вакантного квеста после взятия
                break;}

            default:{
                try {System.out.println("Тип изменяемого субъекта: " + updateData.split("\"")[3] + " не распознан!");}
                catch (NullPointerException e){
                    System.out.println("Произошла попытка получить часть несуществующей команды: " + e.getMessage());
                }
                break;}

        }

        FileManager.eraseLineFromFile(actionsQueueFile, 1,true);// Метод, стирающий верхнюю строку изменений и удаляющий файл изменений в случае их отутствия
        System.out.println("Из очереди удалено выполненное действие");
        if (Checks.isSystemUpdated(actionsQueueFile)){
            updateGameData();
        }

    }

    public void constructActionsQueue(File actionsQueueFile, String command) throws IOException {
        if (!Checks.isFileExist(actionsQueueFile.getName())) {
            System.out.println("Файл с очередью действий не найден");
            FileManager.createActionsQueueFile(actionsQueueFile);
            FileManager.fillActionsQueueFile(actionsQueueFile, command);
            System.out.println("Файл с очередью действий " + actionsQueueFile.getName() + " создан и заполнен");
        }
        else{
            System.out.println("Файл с очередью действий найден");
        }
    }

}
