public enum QuestValuesVariants {
    ROOKIE("Для новичков", 2, 2,2),
    LIGHTLOW("Лёгкий", 3, 2,3),
    LIGHTHIGH("Лёгкий", 4, 2, 4),
    MIDDLELOW("Умеренный", 3, 3,4),
    MIDDLEHIGH("Умеренный", 4, 3,5),
    DIFFICULTLOW("Сложный", 3, 4,5),
    DIFFICULTHIGH("Сложный", 4, 4,6),
    HEAVYLOW("Тяжёлый", 3, 5,6),
    HEAVYHIGH("Тяжёлый", 3, 6,7),
    HARDCORE("Хардкорный", 2, 7,7);

    String questDifficulty;
    int randomValue;
    int constantValue;
    int minKeyStage;
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
    public int getMinKeyStage() {
        return minKeyStage;
    }

    QuestValuesVariants(String questDifficulty, int randomValue, int constantValue, int minKeyStage) {
        this.questDifficulty = questDifficulty;
        this.randomValue = randomValue;
        this.constantValue = constantValue;
        this.minKeyStage = minKeyStage;
    }


}
