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
        QuestValuesVariants[] questValuesVariants= QuestValuesVariants.values();
        quest.setDifficultyRatio(quest.getQuestLevel()/3); // коэффициент сложности
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

    public static boolean getProbableBoolean(int probabilityPercent){
        if(!Checks.isRightPercent(probabilityPercent)){
            System.out.println("Ошибка проверки на правильное указание процента");
            return false;
        }

        if ((randomNumber.nextInt(100) + 1) <= probabilityPercent) {
            return true;
        }

        return false;
    }

    public static void calculateLevelFromStats(CharacterCreator character){
        //todo адаптировать формулу получения уровня из статов под класс персонажа
        character.setLevel((character.getAttentiveness()+character.getAttentivenessMod() +
                            character.getEndurance()+character.getEnduranceMod() +
                            character.getStrength()+character.getStrengthMod() +
                            character.getReaction()+character.getReactionMod()) / 4);
    }

    public static List<Integer> countStatsFromLevel(EnemyHumanCreator enemyHuman){

       int statsPointsLeft = enemyHuman.getLevel()*4;
       List<Integer> statsMassive = new ArrayList<>();
       System.out.println("Доступно очков статов: " + statsPointsLeft);
        int minStatsPoints = statsPointsLeft/10;
        for (int i = 0; i < 4; i++){ // раскидываем минимальные значения
            statsMassive.add(minStatsPoints);
            statsPointsLeft -= minStatsPoints;
            System.out.println("Добавлено минимальное значение " + statsMassive.get(i) + " в позицию листа " + i + ". Остаток очков: " + statsPointsLeft);
        }

            while (statsPointsLeft !=0){
                int number = Formulas.randomNumber.nextInt(4);
                statsMassive.set(number, statsMassive.get(number)+1);
                statsPointsLeft--;
            }
        return statsMassive;
    }

    public static int calculateEnemyLevel(QuestConstructor quest, EnemyHumanCreator enemy){
        int level = quest.getQuestLevel() +
                Formulas.randomNumber.nextInt(quest.getDifficultyRatio()*3/2+3)
                -quest.getDifficultyRatio()-1;
        if (level < 1) level = 1;
        if (level > 30) level = 30;
        enemy.getStrength();
        return level;
    }

    public static void countEnemiesPoints(QuestConstructor quest){ // вычисляем количество очков противников для квеста

        quest.setQuestEnemyPoints(quest.getQuestLevel()*150 +
                quest.getDifficultyRatio()*(100 + randomNumber.nextInt(51)) +
                quest.getStagesInQuest()*50);

    }

    public static void calculateEnemyItems(QuestConstructor quest, EnemyHumanCreator enemyHuman){

    // todo додумать распределение предметов
        List<Items> filteredItems = Items.filterItems(quest, enemyHuman);
        int itemsCount = calculateNumberOfItems(enemyHuman);
        List<String> itemsToTake = new ArrayList<>(itemsCount);
        int jawsLimit = 40 + enemyHuman.getLevel()*35;
        int jaws = 0;

        for (int i = 0; i < itemsCount; ){
            int k = Formulas.randomNumber.nextInt(filteredItems.size());
            if (filteredItems.get(k).getItemName().equals("Джос")){
                jaws += calculateJaws(enemyHuman);
                if (jaws > jawsLimit) {
                    jaws = jawsLimit;
                    filteredItems.remove(k);
                }
            }
            else {
                itemsToTake.set(i, "");
                if (getProbableBoolean(filteredItems.get(k).getGenerationChance())) {
                    itemsToTake.set(i, filteredItems.get(k).getItemName());

                }
                // реализовать объединение предметов в стеки

                if (filteredItems.get(k).isUnique()) {
                    filteredItems.remove(k);
                }
                i++;
            }
        }
        enemyHuman.setJaws(jaws);
        itemsToTake.removeAll(Arrays.asList(""));
    }

    public static int calculateJaws(EnemyHumanCreator enemyHuman){
        return (20 + enemyHuman.getLevel()*15 - Formulas.randomNumber.nextInt(enemyHuman.getLevel()*2 + 11));

    }

    public static int calculateNumberOfItems(EnemyHumanCreator enemyHuman){
        int itemsCount = Formulas.randomNumber.nextInt(enemyHuman.getLevel()/5 + 1);
        itemsCount += Formulas.randomNumber.nextInt(3)-1;
        return  itemsCount;
    }

    public static void calculateStageParameters(QuestConstructor quest, StageConstructor stage){


    }




    //todo придумать формулу получения числа соперников на этапе
    public static void countStageEnemiesPoints (QuestConstructor quest, StageConstructor stage){ // вычисляем количество очков противников для квеста

        // Вписать в очки противников квеста. Вычислять из размера этапа, порядкового номера этапа, и условия является ли этап ключевым
        stage.setStageEnemiesPoints(0);

    }

    public static void distributeEnemiesPoints (QuestConstructor quest){ // вычисляем количество очков противников для квеста
// Сначала создать файлы всех этапов квеста, содержащие размер этапа, порядковый номера этапа, и условие является ли этап ключевым
        int[] enemiesPoints = new int[quest.getStagesInQuest()];
    }



    //todo придумать формулу вычисляющую ключевой этап квеста
    public void countKeyStage(QuestConstructor quest){


    }


}


