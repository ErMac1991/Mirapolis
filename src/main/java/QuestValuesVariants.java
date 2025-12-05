public enum QuestValuesVariants {
    ROOKIE("Для новичков", 2, 2,2),
    LIGHTLOW("Лёгкий", 3, 2,3),
    LIGHTHIGH("Лёгкий", 4, 2,3),
    MIDDLELOW("Умеренный", 3, 3,4),
    MIDDLEHIGH("Умеренный", 4, 3,5),
    DIFFICULTLOW("Сложный", 3, 4,5),
    DIFFICULTHIGH("Сложный", 4, 4,5),
    HEAVYLOW("Тяжёлый", 3, 5,6),
    HEAVYHIGH("Тяжёлый", 3, 6,6),
    HARDCORE("Хардкорный", 2, 7,6);

    String questDifficulty;
    int randomValue;
    int constantValue;
    int keyStageNumber;
    QuestValuesVariants[] questValuesVariants;

    public String getQuestDifficulty() {
        return questDifficulty;
    }
    public int getRandomValue() {
        return randomValue;
    }
    public int getConstantValue() {
        return constantValue;
    }
    public int getKeyStageNumber() {
        return keyStageNumber;
    }

    QuestValuesVariants(String questDifficulty, int randomValue, int constantValue, int minkeyStageNumber) {
        this.questDifficulty = questDifficulty;
        this.randomValue = randomValue;
        this.constantValue = constantValue;
    }


}
