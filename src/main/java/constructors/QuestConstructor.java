package constructors;

import com.fasterxml.jackson.databind.ObjectMapper;
import operations.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class QuestConstructor {
    String questTask; // Описание квестовой задачи
    String questName; // название задания
    int questID; // идентификатор квеста
    int questLevel; // уровень квеста
    String questDifficulty; // обозначение сложности
    int stagesInQuest; // количество этапов в квесте
    boolean isQuestOpenSpace; // этап под открытым небом или в помещении
    boolean isQuestMultiPlayer; // этап под открытым небом или в помещении
    boolean isKeyObject; // имеется ли ключевой объект
    int keyStageNumber; // Порядковый номер ключевого этапа
    int difficultyRatio; // коэффициент сложности квеста
    int QuestEnemyPoints; // очки противников на квест
    int QuestObjectsPoints; // очки интерактивных объектов на квест
    int QuestSystemsPoints; // очки охранных систем на квест
    int QuestItemsPoints; // очки предметов лута на квест
    int royalty; // Награда за выполнение задания
    int deposit; // Взнос за взятие квеста
    LocalDateTime questGenerationDateTime; // Дата и время генерации вакантного квеста
    LocalDateTime questTakeDateTime; // Дата и время взятия квеста игроком
    final File questCounterFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\Системные файлы\\QuestsCounter.txt");

    // ГЕТТЕРЫ И СЕТТЕРЫ
    public String getQuestTask() {
        return questTask;
    }
    public void setQuestTask(String questTask) {
        this.questTask = questTask;
    }
    public String getQuestName() {
        return questName;
    }
    public void setQuestName(String questName) {
        this.questName = questName;
    }
    public int getQuestID() {
        return questID;
    }
    public void setQuestID(int questID) {
        this.questID = questID;
    }
    public int getStagesInQuest() {
        return stagesInQuest;
    }
    public void setStagesInQuest(int stagesInQuest) {
        this.stagesInQuest = stagesInQuest;
    }
    public int getQuestLevel() {
        return questLevel;
    }
    public void setQuestLevel(int questLevel) {
        this.questLevel = questLevel;
    }
    public String getQuestDifficulty() {
        return questDifficulty;
    }
    public void setQuestDifficulty(String questDifficulty) {
        this.questDifficulty = questDifficulty;
    }
    public int getDifficultyRatio() {
        return difficultyRatio;
    }
    public void setDifficultyRatio(int difficultyRatio) {
        this.difficultyRatio = difficultyRatio;
    }
    public int getRoyalty() {
        return royalty;
    }
    public void setRoyalty(int royalty) {
        this.royalty = royalty;
    }
    public int getDeposit() {
        return deposit;
    }
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
    public LocalDateTime getQuestGenerationDateTime() {
        return questGenerationDateTime;
    }
    public void setQuestGenerationDateTime(LocalDateTime questGenerationDateTime) {
        this.questGenerationDateTime = questGenerationDateTime;
    }
    public boolean isQuestOpenSpace() {
        return isQuestOpenSpace;
    }
    public void setQuestOpenSpace(boolean isQuestOpenSpace) {
        this.isQuestOpenSpace = isQuestOpenSpace;
    }
    public boolean isQuestMultiPlayer() {
        return isQuestMultiPlayer;
    }
    public void setQuestMultiPlayer(boolean questMultiPlayer) {
        isQuestMultiPlayer = questMultiPlayer;
    }
    public LocalDateTime getQuestTakeDateTime() {
        return questTakeDateTime;
    }
    public void setQuestTakeDateTime(LocalDateTime questTakeDateTime) {
        this.questTakeDateTime = questTakeDateTime;
    }
    public File getQuestCounterFile() {
        return questCounterFile;
    }
    public int getQuestEnemyPoints() {
        return QuestEnemyPoints;
    }
    public void setQuestEnemyPoints(int questEnemyPoints) {        this.QuestEnemyPoints = questEnemyPoints;
    }
    public int getQuestObjectsPoints() {
        return QuestObjectsPoints;
    }
    public void setQuestObjectsPoints(int questObjectsPoints) {
        QuestObjectsPoints = questObjectsPoints;
    }
    public int getQuestSystemsPoints() {
        return QuestSystemsPoints;
    }
    public void setQuestSystemsPoints(int questSystemsPoints) {
        QuestSystemsPoints = questSystemsPoints;
    }
    public int getQuestItemsPoints() {
        return QuestItemsPoints;
    }
    public void setQuestItemsPoints(int questItemsPoints) {
        QuestItemsPoints = questItemsPoints;
    }
    public boolean isKeyObject() {
        return isKeyObject;
    }
    public void setKeyObject(boolean keyObject) {
        isKeyObject = keyObject;
    }
    public int getKeyStageNumber() {
        return keyStageNumber;
    }
    public void setKeyStageNumber(int keyStageNumber) {
        this.keyStageNumber = keyStageNumber;
    }

    public static QuestConstructor chooseQuest(ObjectMapper objectMapper, QuestConstructor quest) throws IOException {
        System.out.println(quest.getQuestID());
        if (!Checks.isFileExist("Квесты\\Пул\\" + quest.getQuestID() + ".txt")) {
            System.out.println("При выборе вакантного квеста файл квеста " + quest.getQuestID() + ".txt не найден");
            return null;
        }
        System.out.println("При выборе вакантного квеста файл квеста " + quest.getQuestID() + ".txt найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + quest.getQuestID() + ".txt")));
        quest = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + quest.getQuestID() + ".txt")), objectMapper, quest);
        System.out.println("Выбран вакантный квест " + quest.getQuestID());

        return quest;
    }

    public static QuestConstructor chooseQuest(ObjectMapper objectMapper, CharacterCreator character, QuestConstructor quest) throws IOException {
        System.out.println(quest.getQuestID());
        if (!Checks.isFileExist("Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\QuestData.txt")) {
            System.out.println("При выборе вакантного квеста файл квеста " + quest.getQuestID() + ".txt не найден");
            return null;
        }
        System.out.println("При выборе вакантного квеста файл квеста " + quest.getQuestID() + ".txt найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + ".txt")));
        quest = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + quest.getQuestID() + ".txt")), objectMapper, quest);
        System.out.println("Выбран вакантный квест " + quest.getQuestID());

        return quest;
    }

    public static void generateVacantQuest(QuestConstructor quest) throws IOException {

        quest.setQuestLevel(Formulas.randomNumber.nextInt(30) + 1); // Получаем уровень квеста
        System.out.println("Уровень квеста: " + quest.getQuestLevel());
        quest.setQuestID(FileManager.getID(quest.getQuestCounterFile()));

        Formulas.calculateQuestParameters(quest);
        quest.setQuestGenerationDateTime(LocalDateTime.now()); // время генерации квеста

        FileManager.fillPojoToJsonFile(quest);
    }

    public static void generateReceivedQuest(QuestConstructor quest, CharacterCreator character, StageConstructor stage) throws IOException { // Создаём содержание взятого квеста

        quest.setQuestTakeDateTime(LocalDateTime.now()); // время взятия квеста игроком
        FileManager.fillPojoToJsonFile(quest, character); // заполняется файл взятого квеста

        for(int i = 1; i <= quest.getStagesInQuest(); i++){ // цикл по созданию этапов ВЫНЕСТИ В ОТДЕЛЬНЫЙ МЕТОД
            StageConstructor.generateStageOfQuest(quest, character, stage, i);
            System.out.println("Этап " + i + " сгенерирован");
        }

    }


}
