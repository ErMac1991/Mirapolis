import com.fasterxml.jackson.databind.ObjectMapper;

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
    int difficultyRatio; // коэффициент сложности квеста
    int royalty; // Награда за выполнение задания
    int deposit; // Взнос за взятие квеста
    LocalDateTime questGenerationDateTime; // Дата и время генерации вакантного квеста
    LocalDateTime questTakeDateTime; // Дата и время взятия квеста игроком

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

    public static QuestConstructor chooseCharacter(int questID, ObjectMapper objectMapper, QuestConstructor quest) throws IOException {
        System.out.println(questID);
        if (!Checks.isFileExist("Квесты","Пул", questID + ".txt")) {
            System.out.println("При выборе вакантного квеста файл квеста " + questID + ".txt не найден");
            return null;
        }
        System.out.println("При выборе вакантного квеста файл квеста " + questID + ".txt найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + questID + ".txt")));
        quest = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + questID + ".txt")), objectMapper, quest);
        System.out.println("Выбран вакантный квест " + quest.questID);

        return quest;
    }

    public static void generateVacantQuest(QuestConstructor quest) throws IOException {

        //quest = null;

        quest.setQuestLevel(Formulas.randomNumber.nextInt(30) + 1); // Получаем уровень квеста
        System.out.println("Уровень квеста: " + quest.getQuestLevel());
        quest.setQuestID(FileManager.getQuestID());

        Formulas.calculateQuestValues(quest);
        quest.setQuestGenerationDateTime(LocalDateTime.now()); // время генерации квеста

        FileManager.fillPojoToJsonFile(quest);
    }

    public void generateReceivedQuest(QuestConstructor quest, CharacterHelper character){ // Создаём содержание взятого квеста



        for(int i = 1; i <= quest.getStagesInQuest(); i++){ // цикл по созданию этапов
            StageConstructor.generateStageOfQuest(quest);
            System.out.println("Этап " + i + " сгенерирован");
        }

        quest.setQuestTakeDateTime(LocalDateTime.now()); // время взятия квеста игроком

    }


}
