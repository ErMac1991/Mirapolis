import java.util.List;
import java.util.Random;

public class QuestConstructor {
    String questType; // тип задания
    String questName; // название задания
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
    public Random getRandomNumber() {
        return randomNumber;
    }
    public void setRandomNumber(Random randomNumber) {
        this.randomNumber = randomNumber;
    }
    public int[] getQuestValues() {
        return questValues;
    }
    public void setQuestValues(int[] questValues) {
        this.questValues = questValues;
    }

    public void questStructureGenerate(){

        questLevel = randomNumber.nextInt(9) + 1; // Получаем уровень квеста
        System.out.println("Уровень квеста: " + questLevel);

        int stageSize;
        String stageType;
        int stagesInQuest;
        int stageNumber;

        questValues = new int[4];

        // Относим квест к категории сложности
        if ( questLevel >= 1 && questLevel <=3) {
            setQuestDifficulty("Лёгкий");
            System.out.println("Уровень " + questLevel + " попадает в раздел: " + questDifficulty);
            setDifficultyRatio(-3);
            setStagesInQuest(randomNumber.nextInt(3) + 2);
            for(int i = 1; i <= getStagesInQuest(); i++){
                stageGenerate();
                System.out.println("Этап " + i + " сгенерирован");
            }


            questValues = Formulas.getQuestValues(difficultyRatio);

        } else if ( questLevel >= 4 && questLevel <=6) {
            System.out.println("Уровень " + questLevel + " попадает в раздел средних квестов");
            setDifficultyRatio(0);

        } else if ( questLevel >= 7 && questLevel <=9) {
            System.out.println("Уровень " + questLevel + " попадает в раздел тяжёлых квестов");
            setDifficultyRatio(3);

        } else if ( questLevel ==10) {
            System.out.println("Уровень " + questLevel + " попадает в раздел хардкорных квестов");
            setDifficultyRatio(5);

        }
            else {
            System.out.println("Вы ввели число больше 10.");
        }
    }

    public void stageGenerate(){

    }


}
