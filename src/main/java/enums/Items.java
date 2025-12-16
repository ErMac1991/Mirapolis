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
            50),
    THING("Вещь",
            10,
            1,
            20,
            Arrays.asList(),
            5,
            20),
    BOOSTER("Бустер",
            50,
            10,
            30,
            Arrays.asList("Выносливость + 1", "Внимательность + 2", "Реакция + 3", "Сила + 4"),
            1,
            10)
;
    String itemName;
    int itemPoints;
    int itemMinLevel;
    int itemMaxLevel;
    List<String> changeMods;
    int stackLimit;

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

    Items(String itemName, int itemPoints, int itemMinLevel, int itemMaxLevel, List<String> changeMods, int stackLimit, int generationChance) {
        this.itemName = itemName;
        this.itemPoints = itemPoints;
        this.itemMinLevel = itemMinLevel;
        this.itemMaxLevel = itemMaxLevel;
        this.changeMods = changeMods;
        this.stackLimit = stackLimit;
    }

    /*public static List<Items> filterItems(QuestConstructor quest) { // получаем выборку подходящих предметов
        List<Items> filteredItems = Arrays.stream(Items.values())
                .filter(Items -> Items.itemMinLevel <= quest.getQuestLevel()) // фильтрует предметы, подходящие по уровню квеста
                .filter(Items -> Items.itemMaxLevel >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих квесту предметов: " + filteredItems);

        if (filteredItems.size() == 0) {
            System.out.println("Ни один из предметов не подходит для уровня квеста: " + quest.getQuestLevel());
            return null;
        }

        return filteredItems;
    }*/

    public static List<Items> filterItems(QuestConstructor quest, EnemyHumanCreator enemyHuman) { // получаем выборку подходящих предметов
        List<Items> filteredItems = Arrays.stream(Items.values())
                .filter(Items -> Items.itemMinLevel <= quest.getQuestLevel()) // фильтрует предметы, подходящие по уровню квеста
                .filter(Items -> Items.itemMaxLevel >= quest.getQuestLevel())
                .filter(Items -> Items.itemMinLevel <= enemyHuman.getLevel()) // фильтрует предметы, подходящие по уровню противника
                .filter(Items -> Items.itemMaxLevel >= enemyHuman.getLevel())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих квесту и противнику предметов: " + filteredItems);

        if (filteredItems.size() == 0) {
            System.out.println("Ни один из предметов не подходит для уровней квеста " + quest.getQuestLevel() +
                    " и противника: " + enemyHuman.getLevel());
            return null;
        }

        return filteredItems;
    }

}
