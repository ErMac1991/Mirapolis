package enums;

import constructors.QuestConstructor;
import operations.Formulas;

public enum QuestValuesVariants {
    ROOKIE("Для новичков", 2, 2,2,0),
    LIGHTLOW("Лёгкий", 3, 2,3,1),
    LIGHTHIGH("Лёгкий", 4, 2, 4,1),
    MIDDLELOW("Умеренный", 3, 3,4,1),
    MIDDLEHIGH("Умеренный", 4, 3,5,2),
    DIFFICULTLOW("Сложный", 3, 4,5,2),
    DIFFICULTHIGH("Сложный", 4, 4,6,2),
    HEAVYLOW("Тяжёлый", 3, 5,6,2),
    HEAVYHIGH("Тяжёлый", 3, 6,7,2),
    HARDCORE("Хардкорный", 2, 7,7,2);

    String questDifficulty;
    int randomValue;
    int constantValue;
    int minKeyStage; // минимальный возможный ключевой этап
    int maxIntroStage; // максимальный возможный входной этап


    public String getQuestDifficulty() {
        return questDifficulty;
    }
    public int getRandomValue() {
        return randomValue;
    }
    public int getConstantValue() {
        return constantValue;
    }
    public int getMinKeyStage() {
        return minKeyStage;
    }
    public int getMaxIntroStage() {
        return maxIntroStage;
    }

    QuestValuesVariants(String questDifficulty, int randomValue, int constantValue, int minKeyStage, int maxIntroStage) {
        this.questDifficulty = questDifficulty;
        this.randomValue = randomValue;
        this.constantValue = constantValue;
        this.minKeyStage = minKeyStage;
        this.maxIntroStage = maxIntroStage;
    }

    //todo завершить генерацию структуру квеста по этапам
    public static String[][] calculateQuestStructure(QuestConstructor quest, String[][] stagesStructure){
        int introStages;
        String[] typeStructure = new String[quest.getStagesInQuest()];
        String[] spaceStructure = new String[quest.getStagesInQuest()];

        spaceStructure = Formulas.calculateOpenSpace(quest);

        for(int i = 0; i < stagesStructure.length; i++){
            typeStructure[i] = "";
        }

        introStages = Formulas.randomNumber.nextInt(QuestValuesVariants.values()[quest.getDifficultyRatio()].getMaxIntroStage() + 1);

        for(int i = 0; i <= introStages; i++){
            typeStructure[i] = "Интро";
        }

        if (quest.isKeyObject()){
            typeStructure[quest.getKeyStageNumber()] = "Ключевой";
        }

        return stagesStructure;
    }

}
