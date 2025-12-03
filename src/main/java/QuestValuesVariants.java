public enum QuestValuesVariants {
    ROOKIE("Для новичков", 2, 2),
    LIGHTLOW("Лёгкий", 3, 2),
    LIGHTHIGH("Лёгкий", 4, 2),
    MIDDLELOW("Умеренный", 3, 3),
    MIDDLEHIGH("Умеренный", 4, 3),
    DIFFICULTLOW("Сложный", 3, 4),
    DIFFICULTHIGH("Сложный", 4, 4),
    HEAVYLOW("Тяжёлый", 3, 5),
    HEAVYHIGH("Тяжёлый", 3, 6),
    HARDCORE("Хардкорный", 2, 7);

    String questDifficulty;
    int randomValue;
    int constantValue;
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

    QuestValuesVariants(String questDifficulty, int randomValue, int constantValue) {
        this.questDifficulty = questDifficulty;
        this.randomValue = randomValue;
        this.constantValue = constantValue;
    }

    public static void getQuestValues(QuestConstructor quest) { // выбирает подходящий тип для сгенерированного квеста
        QuestValuesVariants[] questValuesVariants= QuestValuesVariants.values();
        quest.setDifficultyRatio(quest.getQuestLevel()/3); // коэффициент сложности

        quest.setQuestDifficulty(questValuesVariants[quest.getDifficultyRatio()].getQuestDifficulty());// наименование сложности
        System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
        quest.setStagesInQuest(Formulas.randomNumber.nextInt( // количество этапов в квесте,
                questValuesVariants[quest.getDifficultyRatio()].getRandomValue()) +
                questValuesVariants[quest.getDifficultyRatio()].getConstantValue());
        System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
    }
}
