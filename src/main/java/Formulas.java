import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Formulas {

    static Random randomNumber = new Random();

    public static void calculateQuestValues(QuestConstructor quest) {

        //Оптимизация метода
        QuestValuesVariants.getQuestValues(quest);
        questPricesCalculate(quest);


        /*
        if (quest.getQuestLevel() >= 1 && quest.getQuestLevel() <= 3) {
            quest.setQuestDifficulty("Для новичков"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(0); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(2) + 2);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 4 && quest.getQuestLevel() <= 6) {
            quest.setQuestDifficulty("Лёгкий"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(1); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(3) + 2);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 7 && quest.getQuestLevel() <= 9) {
            quest.setQuestDifficulty("Лёгкий"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(2); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(4) + 2);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 10 && quest.getQuestLevel() <= 12) {
            quest.setQuestDifficulty("Умеренный"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(3); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(4) + 3);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 13 && quest.getQuestLevel() <= 15) {
            quest.setQuestDifficulty("Умеренный"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(4); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(5) + 3);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 16 && quest.getQuestLevel() <= 18) {
            quest.setQuestDifficulty("Сложный"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(5); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(4) + 4);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 19 && quest.getQuestLevel() <= 21) {
            quest.setQuestDifficulty("Сложный"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(6); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(4) + 4);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 22 && quest.getQuestLevel() <= 24) {
            quest.setQuestDifficulty("Тяжёлый"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(7); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(3) + 5);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 25 && quest.getQuestLevel() <= 27) {
            quest.setQuestDifficulty("Тяжёлый"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(8); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(3) + 6);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else if (quest.getQuestLevel() >= 28 && quest.getQuestLevel() <= 30) {
            quest.setQuestDifficulty("Хардкорный"); // Относим квест к категории сложности
            System.out.println("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
            quest.setDifficultyRatio(9); // коэффициент сложности
            quest.setStagesInQuest(randomNumber.nextInt(2) + 7);  // количество этапов в квесте,
            System.out.println("Количество этапов в квесте: " + quest.getStagesInQuest());
            questPricesCalculate(quest);
        } else {
            System.out.println("В уровне квеста записано что то непонятное: " + quest.getQuestLevel());
        }*/

        QuestTypes.chooseQuestType(quest);


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

    public static void countLevelFromStats(CharacterHelper character){
        //todo адаптировать формулу получения уровня из статов под класс персонажа
        character.setLevel((character.getAttentiveness()+character.getAttentivenessMod() +
                            character.getEndurance()+character.getEnduranceMod() +
                            character.getStrength()+character.getStrengthMod() +
                            character.getReaction()+character.getReactionMod()) / 4);



    }
        //todo придумать формулу получения числа соперников из уровня квеста

}


