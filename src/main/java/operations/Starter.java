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

    public File getActionsQueueFile() {
        return actionsQueueFile;
    }

    public void updateGameData() throws IOException {

        FileManager.deleteEmptyFile(actionsQueueFile);
        if (!Checks.isSystemUpdated(actionsQueueFile)) {
            System.out.println("Обновления игровых файлов не найдены");
        }
        Checks.isSystemUpdated(actionsQueueFile);

        updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
        //todo протестировать проверку на пустую строку в файле и метод на удаление пустой строки
        while (updateData.equals("\n")){
            System.out.println("Подтянута пустая строка. Удаляем пустую строку");
            FileManager.eraseLineFromFile(actionsQueueFile, 1, true);
            Checks.isSystemUpdated(actionsQueueFile);
            updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
        }

        typeOfSubjectFromArgs = updateData.split("\"")[3];

        switch (typeOfSubjectFromArgs) { // тип изменяемого субъекта: персонаж/противник/квест

            case "newCharacter":{
                System.out.println("Тип субъекта - Новый персонаж");
                String userLogin = updateData.split("\"")[7];
                newCharacter(userLogin);
                break;}

            case "changeCharacter":{
                //todo переписать под изменение параметров персонажа
                System.out.println("Тип субъекта - Существующий персонаж");
                String userLogin = updateData.split("\"")[7];
                changeCharacter(userLogin);
                break;}

            case "newVacantQuest":{
                System.out.println("Тип субъекта - Новый Вакантный Квест");
                newVacantQuest();
                break;}

            case "vacantQuestsList":{
                System.out.println("Тип субъекта - Список Вакантных Квестов");
                String userLogin = updateData.split("\"")[7];
                vacantQuestsList(userLogin);
                break;}

            case "newReceivedQuest":{ // в команде передавать логин игрока, берущего квест, ID вакантного квеста
                System.out.println("Тип субъекта - Новый Полученный Квест");
                String userLogin = updateData.split("\"")[7];
                String questID = updateData.split("\"")[11];
                newReceivedQuest(userLogin, questID);
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

    public void newCharacter(String userLogin) throws IOException {

            CharacterCreator.createCharacter(userLogin);
    }

    public void changeCharacter(String userLogin) throws IOException {
        character.setUserLogin(userLogin);
        System.out.println("userNameFromArgs = " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        charactersChanges = character;
        charactersChanges = FileManager.parseStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
        if (!userLogin.equals(charactersChanges.getUserLogin())) {
            System.out.println("Логин игрока из файла: " + userLogin + " не совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
            return;
        }
        System.out.println("Логин игрока из файла: " + userLogin + " совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
        System.out.println("Квест персонажа " + character.getUserLogin() + " до изменений: " + character.getQuest());
        System.out.println("Квест в изменениях: " + charactersChanges.getQuest());
        character = CharacterCreator.updateCharacterPojo(character, charactersChanges);//Внесение изменений в Pojo персонажа слиянием с объектом изменений
        System.out.println("Квест персонажа " + character.getUserLogin() + " после изменений: " + character.getQuest());
        FileManager.fillPojoToJsonFile(character);// Перенос данных из Pojo персонажа в Json файл персонажа
    }

    public void newVacantQuest() throws IOException {
        QuestConstructor.generateVacantQuest(quest);
        System.out.println("Новый вакантный квест создан");
    }

    public void vacantQuestsList(String userLogin) throws IOException {
        String vacantQuests;
        character.setUserLogin(userLogin);
        System.out.println("userNameFromArgs = " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        List<String> vacantQuestsList = Formulas.getVacantQuestsList(objectMapper, quest, character);
                /*if (Checks.hasDuplicates(vacantQuestsList)){
                    vacantQuestsList = Formulas.deleteDuplicates(vacantQuestsList);
                }*/ //проверка на дубликаты квестов в листе

        vacantQuests = QuestConstructor.getVacantQuests(vacantQuestsList);

        System.out.println("Список вакантных квестов, доступных персонажу " + character.getUserLogin() +
                ": \n" + vacantQuests);
    }

    public void newReceivedQuest(String userLogin, String questID) throws IOException {
        character.setUserLogin(userLogin);
        System.out.println("userLogin из команды: " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        System.out.println("questID из команды: " + questID);

        if (!Checks.isFileExist("Квесты/Пул/" + questID + ".txt")){
            System.out.println("Файл вакантного квеста с ID = " + questID + " не найденв пуле");
            vacantQuestsList(userLogin);
            return;
        }

        quest.setQuestID(Integer.parseInt(questID));
        System.out.println("Передан questID " + quest.getQuestID());
        quest = QuestConstructor.chooseQuest(objectMapper, quest);
        System.out.println("Выбран вакантный квест " + quest.getQuestID());

        if (!Checks.isNumberValid(character.getLevel(), quest.getQuestLevel() - 3, quest.getQuestLevel() + 3)){
            System.out.println("Вакантного квеста с ID = " + questID +
                    " и сложностью " + quest.getQuestLevel() +
                    " не подходит персонажу " + character.getUserLogin() +
                    " с уровнем " + character.getLevel());
            vacantQuestsList(userLogin);
            return;
        }

        QuestConstructor.generateReceivedQuest(quest, character); // создаём и заполняем файл принятого квеста
        System.out.println("Создан файл принятого квеста");
        StageConstructor.generateAllStages(objectMapper,quest,character,stage); // создаём и заполняем базовые параметры этапов квеста


        //todo прописать вычисление типа каждого этапа взятого квеста

        stage.setStageType(Formulas.calculateStageType(character, stage)); // вычислять исходя из присутствия соперников, известности игрока, сложности

        System.out.println("Квест принят");
        // todo удалить вакантный квест после взятия
    }

}
