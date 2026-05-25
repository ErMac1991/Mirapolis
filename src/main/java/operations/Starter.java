package operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import constructors.*;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class Starter {
    ObjectMapper objectMapper = new ObjectMapper();
    CharacterCreator character = new CharacterCreator();
    CharacterCreator charactersChanges = new CharacterCreator();
    QuestConstructor quest = new QuestConstructor();
    StageConstructor stage = new StageConstructor();
    EnemyHumanCreator enemyHuman = new EnemyHumanCreator();
    EnemyMachineCreator enemyMachineCreator = new EnemyMachineCreator();
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Starter.class);
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
            logger.info("Обновления игровых файлов не найдены");
        }
        Checks.isSystemUpdated(actionsQueueFile);

        updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
        //todo протестировать проверку на пустую строку в файле и метод на удаление пустой строки
        while (updateData.equals("\n")){
            logger.info("Подтянута пустая строка. Удаляем пустую строку");
            FileManager.eraseLineFromFile(actionsQueueFile, 1, true);
            Checks.isSystemUpdated(actionsQueueFile);
            updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
        }

        typeOfSubjectFromArgs = updateData.split("\"")[3];

        switch (typeOfSubjectFromArgs) { // тип изменяемого субъекта: персонаж/противник/квест

            case "newCharacter":{
                logger.info("Тип субъекта - Новый персонаж");
                String userLogin = updateData.split("\"")[7];
                newCharacter(userLogin);
                break;}

            case "changeCharacter":{
                //todo переписать под изменение параметров персонажа
                logger.info("Тип субъекта - Существующий персонаж");
                String userLogin = updateData.split("\"")[7];
                changeCharacter(userLogin);
                break;}

            case "newVacantQuest":{
                logger.info("Тип субъекта - Новый Вакантный Квест");
                newVacantQuest();
                break;}

            case "vacantQuestsList":{
                logger.info("Тип субъекта - Список Вакантных Квестов");
                String userLogin = updateData.split("\"")[7];
                vacantQuestsList(userLogin);
                break;}

            case "newReceivedQuest":{ // в команде передавать логин игрока, берущего квест, ID вакантного квеста
                logger.info("Тип субъекта - Новый Полученный Квест");
                String userLogin = updateData.split("\"")[7];
                String questID = updateData.split("\"")[11];
                newReceivedQuest(userLogin, questID);
                break;}

            default:{
                try {logger.info("Тип изменяемого субъекта: " + updateData.split("\"")[3] + " не распознан!");}
                catch (NullPointerException e){
                    logger.info("Произошла попытка получить часть несуществующей команды: " + e.getMessage());
                }
                break;}

        }

        FileManager.eraseLineFromFile(actionsQueueFile, 1,true);// Метод, стирающий верхнюю строку изменений и удаляющий файл изменений в случае их отутствия
        //logger.info("Из очереди удалено выполненное действие");
        logger.info("Из очереди удалено выполненное действие");
        if (Checks.isSystemUpdated(actionsQueueFile)){
            updateGameData();
        }

    }

    public void constructActionsQueue(File actionsQueueFile, String command) throws IOException {
        if (!Checks.isFileExist(actionsQueueFile.getName())) {
            logger.info("Файл с очередью действий не найден");
            FileManager.createActionsQueueFile(actionsQueueFile);
            FileManager.fillActionsQueueFile(actionsQueueFile, command);
            logger.info("Файл с очередью действий " + actionsQueueFile.getName() + " создан и заполнен");
        }
        else{
            logger.info("Файл с очередью действий найден");
        }
    }

    public void newCharacter(String userLogin) throws IOException {

            CharacterCreator.createCharacter(userLogin);
    }

    public void changeCharacter(String userLogin) throws IOException {
        character.setUserLogin(userLogin);
        logger.info("userNameFromArgs = " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        charactersChanges = character;
        charactersChanges = FileManager.parseStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
        if (!userLogin.equals(charactersChanges.getUserLogin())) {
            logger.info("Логин игрока из файла: " + userLogin + " не совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
            return;
        }
        logger.info("Логин игрока из файла: " + userLogin + " совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
        logger.info("Квест персонажа " + character.getUserLogin() + " до изменений: " + character.getQuest());
        logger.info("Квест в изменениях: " + charactersChanges.getQuest());
        character = CharacterCreator.updateCharacterPojo(character, charactersChanges);//Внесение изменений в Pojo персонажа слиянием с объектом изменений
        logger.info("Квест персонажа " + character.getUserLogin() + " после изменений: " + character.getQuest());
        FileManager.fillPojoToJsonFile(character);// Перенос данных из Pojo персонажа в Json файл персонажа
    }

    public void newVacantQuest() throws IOException {
        QuestConstructor.generateVacantQuest(quest);
        logger.info("Новый вакантный квест создан");
    }

    public void vacantQuestsList(String userLogin) throws IOException {
        String vacantQuests;
        character.setUserLogin(userLogin);
        logger.info("userNameFromArgs = " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        List<String> vacantQuestsList = Formulas.getVacantQuestsList(objectMapper, quest, character);
                /*if (Checks.hasDuplicates(vacantQuestsList)){
                    vacantQuestsList = Formulas.deleteDuplicates(vacantQuestsList);
                }*/ //проверка на дубликаты квестов в листе

        vacantQuests = QuestConstructor.getVacantQuests(vacantQuestsList);

        logger.info("Список вакантных квестов, доступных персонажу " + character.getUserLogin() +
                ": \n" + vacantQuests);
    }

    public void newReceivedQuest(String userLogin, String questID) throws IOException {
        character.setUserLogin(userLogin);
        logger.info("userLogin из команды: " + character.getUserLogin());
        character = CharacterCreator.chooseCharacter(objectMapper, character);// Переключение на изменяемого персонажа

        logger.info("questID из команды: " + questID);

        if (!Checks.isFileExist("Квесты/Пул/" + questID + ".txt")){
            logger.info("Файл вакантного квеста с ID = " + questID + " не найденв пуле");
            vacantQuestsList(userLogin);
            return;
        }

        quest.setQuestID(Integer.parseInt(questID));
        logger.info("Передан questID " + quest.getQuestID());
        quest = QuestConstructor.chooseQuest(objectMapper, quest);
        logger.info("Выбран вакантный квест " + quest.getQuestID());

        if (!Checks.isNumberValid(character.getLevel(), quest.getQuestLevel() - 3, quest.getQuestLevel() + 3)){
            logger.info("Вакантного квеста с ID = " + questID +
                    " и сложностью " + quest.getQuestLevel() +
                    " не подходит персонажу " + character.getUserLogin() +
                    " с уровнем " + character.getLevel());
            vacantQuestsList(userLogin);
            return;
        }

        QuestConstructor.generateReceivedQuest(quest, character); // создаём и заполняем файл принятого квеста
        logger.info("Создан файл принятого квеста");
        StageConstructor.generateAllStages(quest,character,stage,enemyHuman); // создаём и заполняем базовые параметры этапов квеста

        for (int i = 0; i < quest.getStagesInQuest(); i++) {
            stage.setStageNumber(i+1);
            StageConstructor.chooseStage(objectMapper, character, quest, stage);

        }

        //todo прописать вычисление типа каждого этапа взятого квеста

        stage.setStageType(Formulas.calculateStageType(character, stage)); // вычислять исходя из присутствия соперников, известности игрока, сложности

        logger.info("Квест принят");
        // todo удалить вакантный квест после взятия
    }

}
