import java.io.IOException;
import java.util.List;
import java.util.Random;

public class QuestConstructor {
    String questType; // тип задания
    String questName; // название задания
    int questID; // идентификатор квеста
    int questLevel; // уровень квеста
    String questDifficulty; // обозначение сложности
    String stageName; // название территории этапа
    int stageSize; // размер этапа
    String stageType; // тип этапа: спокойный, экшн, засада и т.д.
    int stagesInQuest; // количество этапов в квесте
    int stageNumber; // порядковый номер текущего этапа
    boolean stageIsOpenSpace; // этап под открытым небом или в помещении
    List<String> objectsOnStage; // объекты на этапе
    List<String> enemiesOnStage; // противники на этапе
    List<String> systemsOnStage; // системы охраны на этапе
    int difficultyRatio; // коэффициент сложности квеста
    int[] questValues;
    int royalty; // Награда за выполнение задания
    int deposit; // Взнос за взятие квеста

    Random randomNumber = new Random();

    public String getQuestType() {
        return questType;
    }
    public void setQuestType(String questType) {
        this.questType = questType;
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
    public String getStageName() {
        return stageName;
    }
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    public int getStageSize() {
        return stageSize;
    }
    public void setStageSize(int stageSize) {
        this.stageSize = stageSize;
    }
    public String getStageType() {
        return stageType;
    }
    public void setStageType(String stageType) {
        this.stageType = stageType;
    }
    public int getStagesInQuest() {
        return stagesInQuest;
    }
    public void setStagesInQuest(int stagesInQuest) {
        this.stagesInQuest = stagesInQuest;
    }
    public int getStageNumber() {
        return stageNumber;
    }
    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
    public boolean isStageIsOpenSpace() {
        return stageIsOpenSpace;
    }
    public void setStageIsOpenSpace(boolean stageIsOpenSpace) {
        this.stageIsOpenSpace = stageIsOpenSpace;
    }
    public List<String> getObjectsOnStage() {
        return objectsOnStage;
    }
    public void setObjectsOnStage(List<String> objectsOnStage) {
        this.objectsOnStage = objectsOnStage;
    }
    public List<String> getEnemiesOnStage() {
        return enemiesOnStage;
    }
    public void setEnemiesOnStage(List<String> enemiesOnStage) {
        this.enemiesOnStage = enemiesOnStage;
    }
    public List<String> getSystemsOnStage() {
        return systemsOnStage;
    }
    public void setSystemsOnStage(List<String> systemsOnStage) {
        this.systemsOnStage = systemsOnStage;
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
    public int[] getQuestValues() {
        return questValues;
    }
    public void setQuestValues(int[] questValues) {
        this.questValues = questValues;
    }

    public void generateVacantQuest(QuestConstructor quest) throws IOException {
        //todo Разбить метод на создание неактивного квеста и генерацию этапов
        quest = null;

        quest.setQuestLevel(randomNumber.nextInt(29) + 1); // Получаем уровень квеста
        System.out.println("Уровень квеста: " + quest.getQuestLevel());

        setQuestID(FileManager.getQuestID());
        questValues = new int[4];

        // Относим квест к категории сложности
        if ( quest.getQuestLevel() >= 1 && quest.getQuestLevel() <=3) {
            quest.setQuestDifficulty("Лёгкий");
            System.out.println("Уровень " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(-3);
            quest.setStagesInQuest(randomNumber.nextInt(3) + 2);
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());

            for(int i = 1; i <= quest.getStagesInQuest(); i++){ // цикл по созданию этапов
                stageGenerate();
                System.out.println("Этап " + i + " сгенерирован");
            }


            Formulas.calculateQuestValues(quest, difficultyRatio);

        } else if ( quest.getQuestLevel() >= 4 && quest.getQuestLevel() <=6) {
            System.out.println("Уровень " + quest.getQuestLevel() + " попадает в раздел средних квестов");
            setDifficultyRatio(0);

        } else if ( quest.getQuestLevel() >= 7 && quest.getQuestLevel() <=9) {
            System.out.println("Уровень " + quest.getQuestLevel() + " попадает в раздел тяжёлых квестов");
            setDifficultyRatio(3);

        } else if ( quest.getQuestLevel() >=10) {
            System.out.println("Уровень " + quest.getQuestLevel() + " попадает в раздел хардкорных квестов");
            setDifficultyRatio(5);

        }
            else {
            System.out.println("В уровне квеста записано что то непонятное: " + quest.getQuestLevel());
        }
    }

    public void stageGenerate(){

    }


}
