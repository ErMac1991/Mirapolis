import java.util.Random;

public abstract class Formulas {

    static Random randomNumber = new Random();

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

    public static void countLevelFromStats(CharacterHelper character){
        //todo адаптировать формулу получения уровня из статов под класс персонажа
        character.setLevel((character.getAttentiveness()+character.getAttentivenessMod() +
                            character.getEndurance()+character.getEnduranceMod() +
                            character.getStrength()+character.getStrengthMod() +
                            character.getReaction()+character.getReactionMod()) / 4);
    }

    public static void calculateStageParameters(QuestConstructor quest, StageConstructor stage){


    }


    public static void countNumberOfEnemies(QuestConstructor quest){ // вычисляем количество очков противников для квеста

        quest.setEnemyPoints(quest.getQuestLevel()*150 +
                quest.getDifficultyRatio()*(100 + randomNumber.nextInt(51)) +
                quest.getStagesInQuest()*50);

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


