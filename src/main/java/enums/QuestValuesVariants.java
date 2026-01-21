package enums;

import constructors.QuestConstructor;
import operations.Formulas;

public enum QuestValuesVariants {
    ROOKIE("Для новичков", 2, 2,2,0),
    LIGHTLOW("Лёгкий", 2, 3,3,1),
    LIGHTHIGH("Лёгкий", 3, 3, 3,1),
    MIDDLELOW("Умеренный", 2, 4,4,1),
    MIDDLEHIGH("Умеренный", 3, 4,4,1),
    DIFFICULTLOW("Сложный", 2, 5,5,2),
    DIFFICULTHIGH("Сложный", 3, 5,5,2),
    HEAVYLOW("Тяжёлый", 2, 6,6,2),
    HEAVYHIGH("Тяжёлый", 3, 6,6,2),
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



}
