import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class QuestConstructor {
    String questTask; // Описание квестовой задачи
    String questName; // название задания
    int questID; // идентификатор квеста
    int questLevel; // уровень квеста
    String questDifficulty; // обозначение сложности
    int stagesInQuest; // количество этапов в квесте
    boolean isQuestOpenSpace; // этап под открытым небом или в помещении
    int difficultyRatio; // коэффициент сложности квеста
    int royalty; // Награда за выполнение задания
    int deposit; // Взнос за взятие квеста
    LocalDateTime questGenerationDateTime; //

    Random randomNumber = new Random();

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

    public void generateVacantQuest(QuestConstructor quest) throws IOException {
        //todo Создать файл с перечнем наименований квестов, описаний заданий в квесте, условий их генерации
        quest = null;

        quest.setQuestLevel(randomNumber.nextInt(29) + 1); // Получаем уровень квеста
        System.out.println("Уровень квеста: " + quest.getQuestLevel());
        setQuestID(FileManager.getQuestID());

        Formulas.calculateQuestValues(quest);
        quest.setQuestGenerationDateTime(LocalDateTime.now()); // время генерации квеста

        FileManager.fillPojoToJsonFile(quest);
    }

    public void generateReceivedQuest(QuestConstructor VacantQuest){ // Создаём содержание взятого квеста
        for(int i = 1; i <= VacantQuest.getStagesInQuest(); i++){ // цикл по созданию этапов
            StageConstructor.generateStageOfQuest(VacantQuest);
            System.out.println("Этап " + i + " сгенерирован");
        }

    }


}
