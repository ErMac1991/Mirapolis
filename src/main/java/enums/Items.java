package enums;

import constructors.EnemyHumanCreator;
import constructors.QuestConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Items {
    JAWS("Джос",
            1,
            1,
            30,
            Arrays.asList("Джос + 1"),
            0,
            75,
            false),
    THING("Вещь",
            10,
            1,
            20,
            Arrays.asList(),
            5,
            20,
            false),
    BOOSTER("Бустер",
            50,
            10,
            30,
            Arrays.asList("Выносливость + 1", "Внимательность + 2", "Реакция + 3", "Сила + 4"),
            1,
            10,
            true)
;
    String itemName;
    int itemPoints;
    int itemMinLevel;
    int itemMaxLevel;
    List<String> changeMods;
    int stackLimit;
    int generationChance;
    boolean isUnique; // попадает ли в выборку только один раз

    public String getItemName() {
        return itemName;
    }
    public int getItemPoints() {
        return itemPoints;
    }
    public int getItemMinLevel() {
        return itemMinLevel;
    }
    public int getItemMaxLevel() {
        return itemMaxLevel;
    }
    public List<String> getChangeMods() {
        return changeMods;
    }
    public int getStackLimit() {
        return stackLimit;
    }
    public int getGenerationChance() {
        return generationChance;
    }
    public boolean isUnique() {
        return isUnique;
    }


    Items(String itemName, int itemPoints, int itemMinLevel, int itemMaxLevel, List<String> changeMods, int stackLimit, int generationChance, boolean isUnique) {
        this.itemName = itemName;
        this.itemPoints = itemPoints;
        this.itemMinLevel = itemMinLevel;
        this.itemMaxLevel = itemMaxLevel;
        this.changeMods = changeMods;
        this.stackLimit = stackLimit;
        this.generationChance = generationChance;
        this.isUnique = isUnique;
    }



    public static List<Items> filterItems(QuestConstructor quest, EnemyHumanCreator enemyHuman) { // получаем выборку подходящих предметов
        List<Items> filteredItems = Arrays.stream(Items.values())
                .filter(Items -> Items.getItemMinLevel() <= quest.getQuestLevel()) // фильтрует предметы, подходящие по уровню квеста
                .filter(Items -> Items.getItemMaxLevel() >= quest.getQuestLevel())
                .filter(Items -> Items.getItemMinLevel() <= enemyHuman.getLevel()) // фильтрует предметы, подходящие по уровню противника
                .filter(Items -> Items.getItemMaxLevel() >= enemyHuman.getLevel())
                .collect(Collectors.toList()); // Собираем в список
        logger.info("Список подходящих квесту и противнику предметов: " + filteredItems);

        if (filteredItems.size() == 0) {
            logger.info("Ни один из предметов не подходит для уровней квеста " + quest.getQuestLevel() +
                    " и противника: " + enemyHuman.getLevel());
            return null;
        }

        return filteredItems;
    }

}
