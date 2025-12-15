package enums;

import constructors.*;
import operations.Formulas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Enemies {
    DUMMY("Человек",
            "Манекен",
            1,
            25,
            100),
    HEAVYDUMMY("Робот",
            "Тяжёлый манекен",
            5,
            30,
            200);

    String enemyType;
    String enemyName;
    int enemyMinLevel;
    int enemyMaxLevel;
    int enemyPoints;

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyMinLevel() {
        return enemyMinLevel;
    }

    public int getEnemyMaxLevel() {
        return enemyMaxLevel;
    }

    public int getEnemyPoints() {
        return enemyPoints;
    }

    public String getEnemyType() {
        return enemyType;
    }

    Enemies(String enemyType, String enemyName, int enemyMinLevel, int enemyMaxLevel, int enemyPoints) {
        this.enemyType = enemyType;
        this.enemyName = enemyName;
        this.enemyMinLevel = enemyMinLevel;
        this.enemyMaxLevel = enemyMaxLevel;
        this.enemyPoints = enemyPoints;
    }

    public static List<Enemies> filterEnemies(QuestConstructor quest) { // получаем выборку подходящих соперников
        List<Enemies> filteredEnemies = Arrays.stream(Enemies.values())
                .filter(Enemies -> Enemies.getEnemyMinLevel() <= quest.getQuestLevel()) // фильтрует типы квеста, подходящие по уровню
                .filter(Enemies -> Enemies.getEnemyMaxLevel() >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих типов противников: " + filteredEnemies);

        if (filteredEnemies.size() == 0) {
            System.out.println("Ни один из типов противников не подходит для уровня: " + quest.getQuestLevel());
            return null;
        }

        return filteredEnemies;
    }

    public static void chooseEnemyHuman(QuestConstructor quest, EnemyHumanCreator enemy) { // выбираем противника человека из выборки

        List<Enemies> filteredEnemies = filterEnemies(quest);


        int index = Formulas.randomNumber.nextInt(filteredEnemies.size());

//todo придумать формулу подбора противников исходя из общих очков противников в квесте
        enemy.setEnemyHumanName(filteredEnemies.get(index).getEnemyName()); // передаём название квеста
        System.out.println("Наименование противника человека " + enemy.getEnemyHumanName() + " передано");
        enemy.setEnemyHumanPoints(filteredEnemies.get(index).getEnemyPoints()); // передаём название квеста
        System.out.println("Количество очков противника человека " + enemy.getEnemyHumanPoints() + " передано");


    }


}
