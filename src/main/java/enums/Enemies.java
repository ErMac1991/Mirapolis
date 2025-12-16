package enums;

import constructors.*;
import operations.Formulas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Enemies {
    DUMMY("Человек",
            "Манекен",
            1,
            25,
            100),
    HEAVYDUMMY("Машина",
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

    public static void spendEnemyPoints(QuestConstructor quest, EnemyHumanCreator enemyHuman, EnemyMachineCreator enemyMachine){

        int index;
        List<Enemies> filteredEnemies = filterEnemies(quest);
        int enemyPointsRemains = quest.getQuestEnemyPoints();
        System.out.println("Количество очков противника для распределения: " + enemyPointsRemains);
        int minEnemyPoints = Arrays.stream(Enemies.values())
                .min(Comparator.comparingInt(Enemies::getEnemyPoints))
                .get()
                .getEnemyPoints();
        System.out.println("Минимальная стоимость противника из выборки: " + minEnemyPoints + " очков");

        while (enemyPointsRemains >= minEnemyPoints){

            index = Formulas.randomNumber.nextInt(filteredEnemies.size());
            System.out.println("Выбран индекс листа " + index + ". Противник под этим индексом: " + filteredEnemies.get(index).getEnemyName());

            if (enemyPointsRemains >= filteredEnemies.get(index).getEnemyPoints()){
                enemyPointsRemains -= filteredEnemies.get(index).getEnemyPoints();
                System.out.println("Стоимость противника " + filteredEnemies.get(index).getEnemyName() +
                        " списана в размере: " + filteredEnemies.get(index).getEnemyPoints() +
                        " очков. Остаток: " + enemyPointsRemains + " очков");

                switch (filteredEnemies.get(index).getEnemyType()){
                    case "Человек":
                        setEnemyHumanParameters(quest, enemyHuman, filteredEnemies.get(index));
                        enemyHuman.setLeftHand("Плоть");
                        enemyHuman.setRightHand("Плоть");
                        enemyHuman.setLeftLeg("Плоть");
                        enemyHuman.setRightLeg("Плоть");
                        enemyHuman.setHead("Плоть");
                        enemyHuman.setBody("Плоть");

                    case "Машина":
                        setEnemyMachineParameters(quest, enemyMachine, filteredEnemies.get(index));
                    default:
                        System.out.println("Выбрано неведомое нечто");
                }

            }
            else{
                System.out.println("На противника " + filteredEnemies.get(index).getEnemyName() + " очков не хватает.");
                filteredEnemies.remove(index);
                System.out.println("Удалили его из списка");
            }
        }
        System.out.println("Все очки противников растрачены. Остаток: " + enemyPointsRemains);

    }

    public static void setEnemyHumanParameters(QuestConstructor quest, EnemyHumanCreator enemyHuman, Enemies enemyUnit) { // Заполняем Pojo противника человека


//todo Заполнить параметры противника человека И/ИЛИ киборга
        enemyHuman.setEnemyHumanName(enemyUnit.getEnemyName()); // передаём наименование противника
        System.out.println("Наименование противника человека " + enemyHuman.getEnemyHumanName() + " передано");
        enemyHuman.setEnemyHumanPoints(enemyUnit.getEnemyPoints()); // передаём стоимость противника в ОП
        System.out.println("Количество очков противника человека " + enemyHuman.getEnemyHumanPoints() + " передано");


    }
    //todo Заполнить параметры противника машины
    public static void setEnemyMachineParameters(QuestConstructor quest, EnemyMachineCreator enemyMachine, Enemies enemyUnit) {    // Заполняем Pojo противника Машины

    }


}
