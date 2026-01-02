package operations;

import constructors.CharacterCreator;
import constructors.EnemyHumanCreator;
import constructors.QuestConstructor;
import constructors.StageConstructor;
import enums.Items;
import enums.QuestTypes;
import enums.QuestValuesVariants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Formulas {

    public static Random randomNumber = new Random();

    public static void calculateQuestParameters(QuestConstructor quest) {

        Formulas.getQuestValues(quest);
        questPricesCalculate(quest);

        QuestTypes.chooseQuestType(quest);


    }

    public static void getQuestValues(QuestConstructor quest) { // выбирает подходящий тип для сгенерированного квеста
        QuestValuesVariants[] questValuesVariants = QuestValuesVariants.values();
        quest.setDifficultyRatio(quest.getQuestLevel() / 3); // коэффициент сложности
        System.out.println("Для квеста уровнем: " + quest.getQuestLevel() + " коэффициент сложности: " + quest.getDifficultyRatio());
        quest.setQuestDifficulty(questValuesVariants[quest.getDifficultyRatio()].getQuestDifficulty());// наименование сложности
        System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
        quest.setStagesInQuest(Formulas.randomNumber.nextInt( // количество этапов в квесте,
                questValuesVariants[quest.getDifficultyRatio()].getRandomValue()) +
                questValuesVariants[quest.getDifficultyRatio()].getConstantValue());
        System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
        quest.setKeyStageNumber(Formulas.randomNumber.nextInt( // порядковый номер ключевого этапа,
                quest.getStagesInQuest() - questValuesVariants[quest.getDifficultyRatio()].getMinKeyStage() + 1) +
                questValuesVariants[quest.getDifficultyRatio()].getMinKeyStage());
    }

    public static void questPricesCalculate(QuestConstructor quest) {
        quest.setDeposit(100 + quest.getDifficultyRatio() * 100); // цена за взятие квеста
        System.out.println("Стоимость за взятие квеста: " + quest.getDeposit());

        quest.setRoyalty(quest.getDeposit() +
                quest.getStagesInQuest() * 50 +
                quest.getDifficultyRatio() * 50 +
                randomNumber.nextInt(51) * quest.getDifficultyRatio());// вознаграждение за прохождение квеста
        System.out.println("Награда за выполнение квеста: " + quest.getRoyalty());

    }

    public static boolean getProbableBoolean(int probabilityPercent) {
        if (!Checks.isRightPercent(probabilityPercent)) {
            System.out.println("Ошибка проверки на правильное указание процента");
            return false;
        }

        if ((randomNumber.nextInt(100) + 1) <= probabilityPercent) {
            return true;
        }

        return false;
    }

    public static void calculateLevelFromStats(CharacterCreator character) {
        //todo адаптировать формулу получения уровня из статов под класс персонажа
        character.setLevel((character.getAttentiveness() + character.getAttentivenessMod() +
                character.getEndurance() + character.getEnduranceMod() +
                character.getStrength() + character.getStrengthMod() +
                character.getReaction() + character.getReactionMod()) / 4);
    }

    public static List<Integer> countStatsFromLevel(EnemyHumanCreator enemyHuman) {

        int statsPointsLeft = enemyHuman.getLevel() * 4;
        List<Integer> statsMassive = new ArrayList<>();
        System.out.println("Доступно очков статов: " + statsPointsLeft);
        int minStatsPoints = statsPointsLeft / 10;
        for (int i = 0; i < 4; i++) { // раскидываем минимальные значения
            statsMassive.add(minStatsPoints);
            statsPointsLeft -= minStatsPoints;
            System.out.println("Добавлено минимальное значение " + statsMassive.get(i) + " в позицию листа " + i + ". Остаток очков: " + statsPointsLeft);
        }

        while (statsPointsLeft != 0) {
            int number = Formulas.randomNumber.nextInt(4);
            statsMassive.set(number, statsMassive.get(number) + 1);
            statsPointsLeft--;
        }
        return statsMassive;
    }

    public static int calculateEnemyLevel(QuestConstructor quest, EnemyHumanCreator enemy) {
        int level = quest.getQuestLevel() +
                Formulas.randomNumber.nextInt(quest.getDifficultyRatio() * 3 / 2 + 3)
                - quest.getDifficultyRatio() - 1;
        if (level < 1) level = 1;
        if (level > 30) level = 30;
        return level;
    }

    public static int countEnemiesPoints(QuestConstructor quest) { // вычисляем количество очков противников для квеста

         int questEnemiesPoints = quest.getQuestLevel() * 150 +
                quest.getDifficultyRatio() * (100 + randomNumber.nextInt(51)) +
                quest.getStagesInQuest() * 50;
        return questEnemiesPoints;

    }

    public static List<String> calculateEnemyItems(QuestConstructor quest, EnemyHumanCreator enemyHuman) {

        List<Items> filteredItems = Items.filterItems(quest, enemyHuman);
        int itemsCount = calculateNumberOfItems(enemyHuman);
        List<String> enemyItems = new ArrayList<>();
        int jawsLimit = 40 + enemyHuman.getLevel() * 35;
        int jawsToAdd = 0;
        int jawsToAccount = 0;
        int itemsPointsLeft = enemyHuman.getItemsPoints();
        int i = 0;
        int bagPlaceID = 0;

        while (i < itemsCount && filteredItems.size()>0) {
            int filteredItemNumber = Formulas.randomNumber.nextInt(filteredItems.size());
            System.out.println("Индекс выбранного предмета в отфильрованном листе " + filteredItemNumber +
                    ". Соответствующий предмет: " + filteredItems.get(filteredItemNumber) + " " + filteredItems.get(filteredItemNumber).getItemName());
            if (filteredItems.get(filteredItemNumber).equals(Items.JAWS)) {
                System.out.println("Зачисляем джос");
                if (Formulas.getProbableBoolean(filteredItems.get(filteredItemNumber).getGenerationChance())) {
                    System.out.println("Зачисление джос прошло проверку вариации " + filteredItems.get(filteredItemNumber).getGenerationChance() + "%");
                    jawsToAdd = calculateJaws(enemyHuman);

                    if (jawsToAdd * filteredItems.get(filteredItemNumber).getItemPoints()> itemsPointsLeft) {
                        jawsToAdd = itemsPointsLeft / filteredItems.get(filteredItemNumber).getItemPoints();
                    }
                    itemsPointsLeft -= jawsToAdd * filteredItems.get(filteredItemNumber).getItemPoints();
                    jawsToAccount += jawsToAdd;
                    System.out.println("Всего джос: " + jawsToAccount + ". Лимит для этого противника: " + jawsLimit);
                }

                if (jawsToAccount > jawsLimit) {
                    itemsPointsLeft += (jawsToAccount - jawsLimit) * filteredItems.get(filteredItemNumber).getItemPoints();
                    jawsToAccount = jawsLimit;
                    System.out.println("Всего джос: " + jawsToAccount + " достигло максимума");
                    filteredItems.remove(filteredItemNumber);
                }
            }
            else {

                if (filteredItems.get(filteredItemNumber).getItemPoints() > itemsPointsLeft) { // проверка на то что очков хватает
                    System.out.println("Количество нераспределённых очков предметов: " + itemsPointsLeft +
                            ". Предмет " + filteredItems.get(filteredItemNumber).getItemName() + " стоимостью " +
                            filteredItems.get(filteredItemNumber).getItemPoints() + " не по карману");
                    filteredItems.remove(filteredItemNumber);
                }
                else {

                    if (getProbableBoolean(filteredItems.get(filteredItemNumber).getGenerationChance())) {
                        System.out.println("Вероятность " + filteredItems.get(filteredItemNumber).getGenerationChance() + " сыграла. " +
                                "В выборку попал предмет: " + filteredItems.get(filteredItemNumber) +
                                ". Количество нераспределённых очков предметов: " + itemsPointsLeft);
                        //todo перенести проверку содержания предмета в листе в отдельный метод в Checks
                        if (enemyItems.contains(filteredItems.get(filteredItemNumber).getItemName())){ // проверка на добавление в стек
                            System.out.println("В листе уже имеется предмет " + filteredItems.get(filteredItemNumber).getItemName() +
                                    ". Закидываем его в стек. Массив: " + enemyItems);

                            List<String> changedEnemyItems = formItemStacks(enemyItems, filteredItems.get(filteredItemNumber).getItemName());

                            if(enemyItems.equals(changedEnemyItems)){
                                System.out.println("Массивы равны, значит enemyItems полон, предмет некуда добавить. Удаляем этот предмет из filteredItems");
                                filteredItems.remove(filteredItemNumber);
                            }
                            else{
                                itemsPointsLeft -= filteredItems.get(filteredItemNumber).getItemPoints();
                                i++;
                            }
                            System.out.println("Массив после внесения предмета в стек: " + enemyItems +
                                    ". Количество нераспределённых очков предметов: " + itemsPointsLeft);
                        }
                        else if (enemyItems.size() < 4){
                        enemyItems.set(bagPlaceID, filteredItems.get(filteredItemNumber).getItemName());
                        i++;
                        bagPlaceID++;

                            if (filteredItems.get(filteredItemNumber).isUnique()) {
                                System.out.println("Предмет " + filteredItems.get(filteredItemNumber) + " является уникальным");
                                filteredItems.remove(filteredItemNumber);
                                System.out.println("Предмет удалён из списка отфильтрованных предметов");

                            }
                        }
                        else{
                            System.out.println("EnemyItems полон, предмет некуда добавить. Удаляем этот предмет из filteredItems");
                            filteredItems.remove(filteredItemNumber);
                        }


                    }


                }

            }
        }
        enemyHuman.setJaws(jawsToAccount);

        System.out.println("Джос перечислено: " + enemyHuman.getJaws() +
                ". Лист выборки предметов: " + enemyItems +
                ". Количество нераспределённых очков предметов: " + itemsPointsLeft);

        return enemyItems;
    }

    // реализовать объединение предметов в стеки
    public static List<String> formItemStacks(List<String> items, String newItem) {

        int changedItemIndex = -1;

        for (int k = 0; k < items.size(); k++) { // нахождение последнего стека предмета в массиве
            if (items.get(k).contains(newItem)) {
                changedItemIndex = k;
            }
        }

        if (Checks.isItemFullStack(items.get(changedItemIndex))) {
            if (items.size() == 4){
                System.out.println("Последний стек предмета полон, мест под новый стек нет");
                return items;
            }

            items.set(items.size(), newItem);
            System.out.println("Создан новый стек предмета. Массив предметов: " + items);

            return items;
        }

        System.out.println("Найден неполный стек предмета, пополняем его на предмет. Массив предметов до добавления: " + items);
        items.set(changedItemIndex, addItemToStack(items.get(changedItemIndex)));
        System.out.println("Стек предмета пополнен. Массив предметов после добавления: " + items);

        return items;
    }

    public static String addItemToStack(String itemStack) {

        if (!Character.isDigit(itemStack.charAt(0))) {
            return ("2 " + itemStack);
        }

        int firstSpaceIndex = itemStack.indexOf(' ');
        int counter = Integer.parseInt((itemStack.substring(0, firstSpaceIndex))) + 1;
        String itemName = itemStack.substring(firstSpaceIndex + 1);

        return (counter + " " + itemName);
    }

    public static int calculateJaws(EnemyHumanCreator enemyHuman) { // рассчитываем количество джос из выпавшего предмета
        return (20 + enemyHuman.getLevel() * 15 - Formulas.randomNumber.nextInt(enemyHuman.getLevel() * 2 + 11));

    }

    public static int calculateNumberOfItems(EnemyHumanCreator enemyHuman) {
        int itemsCount = Formulas.randomNumber.nextInt(enemyHuman.getLevel()/5 + 1) + enemyHuman.getLevel() / 8;
        itemsCount += Formulas.randomNumber.nextInt(3) - 1;

        if (itemsCount < 0) {
            itemsCount = 0;
        }
        return itemsCount;
    }

    public static void calculateStageParameters(QuestConstructor quest, StageConstructor stage) {


    }


    //todo придумать формулу получения числа соперников на этапе
    public static void calculateStageEnemiesPoints(QuestConstructor quest, StageConstructor stage) { // вычисляем количество очков противников для этапа

        // Вписать в очки противников квеста. Вычислять из размера этапа, порядкового номера этапа, и условия является ли этап ключевым
        stage.setStageEnemiesPoints(0);

    }

    public static int calculateItemsPoints(EnemyHumanCreator enemyHuman){
        int itemsPoints = Formulas.randomNumber.nextInt(enemyHuman.getLevel() / 2 ) * 10 + 10;
        itemsPoints += Formulas.randomNumber.nextInt(21) - 10;

        return itemsPoints;
    }

    public static void distributeEnemiesPoints(QuestConstructor quest) { // вычисляем количество очков противников для квеста
// Сначала создать файлы всех этапов квеста, содержащие размер этапа, порядковый номера этапа, и условие является ли этап ключевым
        int[] enemiesPoints = new int[quest.getStagesInQuest()];
    }


    //todo придумать формулу вычисляющую ключевой этап квеста
    public void countKeyStage(QuestConstructor quest) {


    }


}


