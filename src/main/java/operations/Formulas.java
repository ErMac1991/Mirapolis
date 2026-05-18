package operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import constructors.CharacterCreator;
import constructors.EnemyHumanCreator;
import constructors.QuestConstructor;
import constructors.StageConstructor;
import enums.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Formulas {

    public static Random randomNumber = new Random();

// УНИВЕРСАЛЬНЫЕ ВЫЧИСЛЕНИЯ

    public static boolean getProbableBoolean(int probabilityPercent) {
        if (!Checks.isRightPercent(probabilityPercent)) {
            logger.info("Ошибка проверки на правильное указание процента");
            return false;
        }

        if ((randomNumber.nextInt(100) + 1) <= probabilityPercent) {
            return true;
        }

        return false;
    }

    public static List<String> deleteDuplicates(List<String> list){

        list = list.stream()
                .distinct() // Удаляет дубликаты, сохраняя порядок
                .collect(Collectors.toList());

        return list;
    }

// РАСЧЕТЫ ПЕРСОНАЖА

    public static int calculateLevelFromStats(CharacterCreator character) {

        int characterLevel;
        //todo адаптировать формулу получения уровня из статов под класс персонажа
        characterLevel = (character.getAttentiveness() + character.getAttentivenessMod() +
                character.getEndurance() + character.getEnduranceMod() +
                character.getStrength() + character.getStrengthMod() +
                character.getReaction() + character.getReactionMod()) / 4;
        return characterLevel;
    }

// РАСЧЕТЫ КВЕСТОВ

    public static void calculateQuestParameters(QuestConstructor quest) {

        Formulas.getQuestValues(quest);
        questPricesCalculate(quest);

        QuestTypes.chooseQuestType(quest);


    }

    public static void getQuestValues(QuestConstructor quest) { // выбирает подходящий тип для сгенерированного квеста
        QuestValuesVariants[] questValuesVariants = QuestValuesVariants.values();
        int questDifficultyRatio = quest.getQuestLevel() / 3;
        if (questDifficultyRatio > 9) {
            questDifficultyRatio = 9;
        }
        quest.setDifficultyRatio(questDifficultyRatio); // коэффициент сложности
        logger.info("Для квеста уровнем: " + quest.getQuestLevel() + " коэффициент сложности: " + quest.getDifficultyRatio());
        quest.setQuestDifficulty(questValuesVariants[quest.getDifficultyRatio()].getQuestDifficulty());// наименование сложности
        logger.info("Квест уровня " + quest.getQuestLevel() + " попадает в раздел: " + quest.getQuestDifficulty());
        quest.setStagesInQuest(Formulas.randomNumber.nextInt( // количество этапов в квесте,
                questValuesVariants[quest.getDifficultyRatio()].getRandomValue()) +
                questValuesVariants[quest.getDifficultyRatio()].getConstantValue());
        logger.info("Количество этапов в квесте: " + quest.getStagesInQuest());
        quest.setKeyStageNumber(defineKeyStage(quest, questValuesVariants));
    }

    public static void questPricesCalculate(QuestConstructor quest) {
        quest.setDeposit(100 + quest.getDifficultyRatio() * 100); // цена за взятие квеста
        logger.info("Стоимость за взятие квеста: " + quest.getDeposit());

        quest.setRoyalty(quest.getDeposit() +
                quest.getStagesInQuest() * 50 +
                quest.getDifficultyRatio() * 50 +
                randomNumber.nextInt(51) * quest.getDifficultyRatio());// вознаграждение за прохождение квеста
        logger.info("Награда за выполнение квеста: " + quest.getRoyalty());

    }

    public static String[] calculateOpenSpace(QuestConstructor quest) {
        int stagesInQuest = quest.getStagesInQuest();
        String[] spaceStructure = new String[stagesInQuest];
        int introOpenSpaces;
        int stageCounter;

        if (quest.isQuestOpenSpace()){
            logger.info("Квест под открытым небом");
            for (int i = 0; i < stagesInQuest; i++){
                spaceStructure[i] = "Улица";
            }
            return spaceStructure;
        }

        stageCounter = 0;
        introOpenSpaces = Formulas.randomNumber.nextInt(stagesInQuest/3 + 1);
        for (int i = 0; i < introOpenSpaces; i++){
            spaceStructure[i] = "Улица";
            stageCounter++;
            logger.info("Добавлена интро-улица");
        }
        logger.info("Структура квеста " + spaceStructure);

        if (stagesInQuest <= 6){
            logger.info("Размер квеста " + stagesInQuest + "<= 6");
            for (int i = stageCounter; i < stagesInQuest; i++){
                spaceStructure[i] = "Здание";
            }
            if (stagesInQuest >= 5 && Formulas.getProbableBoolean(5)){
                spaceStructure[stagesInQuest-1] = "Задний двор";
                logger.info("Сгенерирован задний двор");
            }
            logger.info("Структура квеста " + spaceStructure);
            return spaceStructure;
        }

        for (int i = stageCounter; i < stageCounter + 3; i++){
            spaceStructure[i] = "Здание";
        }
        stageCounter += 3;

        for ( ; stageCounter < stagesInQuest - 3; stageCounter++){
            if (Formulas.getProbableBoolean(10)) {
                spaceStructure[stageCounter] = "Внутренний двор";
                logger.info("Сгенерирован внутренний двор");
                logger.info("Структура квеста " + spaceStructure);

                for (int k = stageCounter +1 ; k < stagesInQuest; k++){
                    spaceStructure[k] = "Здание";
                    stageCounter++;
                }
                logger.info("Структура квеста " + spaceStructure);

                if (stagesInQuest > stageCounter && Formulas.getProbableBoolean(5)){
                    spaceStructure[stagesInQuest-1] = "Задний двор";
                    logger.info("Сгенерирован задний двор");
                }
                logger.info("Структура квеста " + spaceStructure);
                return spaceStructure;

            }
            else{
                spaceStructure[stageCounter] = "Здание";
                logger.info("Структура квеста " + spaceStructure);
            }


        }

        for ( ; stageCounter < stagesInQuest; stageCounter++){
            spaceStructure[stageCounter] = "Здание";
        }

        if (Formulas.getProbableBoolean(5)){
            spaceStructure[stagesInQuest-1] = "Задний двор";
            logger.info("Сгенерирован задний двор");
        }

        logger.info("Структура квеста " + spaceStructure);
        return spaceStructure;

    }

    public static List<String> getVacantQuestsList(ObjectMapper objectMapper, QuestConstructor quest, CharacterCreator character){
        //todo дописать метод возвращения списка доступных персонажу вакантных квестов
        List<String> vacantQuests = new ArrayList<>();
        int characterLevel = 0;
        String questFileName;
        int questFilesCounter;
        int questID;
        List<Path> vacantQuestsList = new ArrayList<>();
        Path directoryPath = Paths.get("F:/Проекты/Стримы/Mirapolis/Квесты/Пул"); // Путь к папке

        try {
            characterLevel = character.getLevel();
        } catch (NullPointerException e) {
            logger.info("Не подтянулся уровень персонажа");
            e.printStackTrace();
        }
        logger.info("Уровень персонажа: " + characterLevel);

        try (Stream<Path> filesStream = Files.list(directoryPath)) {

            vacantQuestsList = filesStream.filter(Files::isRegularFile).collect(Collectors.toList());

            }
        catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Лист файлов: " + vacantQuestsList);
        questFilesCounter = vacantQuestsList.size();

        for (int i = 0; i < questFilesCounter; i++){
            logger.info("Выбранный файл: " + vacantQuestsList.get(i));
            questFileName = String.valueOf(vacantQuestsList.get(i).getFileName());
            questID = Integer.parseInt(questFileName.substring(0, questFileName.lastIndexOf('.')));
            quest.setQuestID(questID);

            try {
                quest = QuestConstructor.chooseQuest(objectMapper, quest);
            } catch (IOException e) {
                logger.info("Ошибка ввода-вывода");
                e.printStackTrace();
            }
            if (Checks.isNumberValid(characterLevel, quest.getQuestLevel() - 3, quest.getQuestLevel() + 3)){
                logger.info("Квест с ID: " + quest.getQuestID() + " подходит персонажу");
            vacantQuests.add(quest.getQuestName() + " (" + quest.getQuestLevel() + " ур.) ID: " + quest.getQuestID());
            }
        }

        if (vacantQuests.size() == 0){
            logger.info("Для персонажа нет подходящих квестов");
        }

        return vacantQuests;
    }


// РАСЧЕТЫ ЭТАПОВ

    public static int[][] calculateStagesPoints(QuestConstructor quest, String[] stageTypeStructure) {
        int[][] stagesPoints;
        int[] enemiesPoints = Formulas.distributeEnemiesPoints(quest,stageTypeStructure);
        int[] systemsPoints = Formulas.distributeSystemsPoints(quest,stageTypeStructure);
        int[] objectsPoints = Formulas.distributeObjectsPoints(quest,stageTypeStructure);

        stagesPoints = new int[][]{enemiesPoints, systemsPoints, objectsPoints};
        return stagesPoints;
    }

    public static int calculateStageSize(StageLocations chosenLocation) {
        int stageSize;
        stageSize = Formulas.randomNumber.nextInt(chosenLocation.getMaxStageSize() - chosenLocation.getMinStageSize()+1) +
                chosenLocation.getMinStageSize();
        return stageSize;
    }

    public static int defineKeyStage(QuestConstructor quest, QuestValuesVariants[] questValuesVariants) {
        logger.info("Этапов в квесте: " + quest.getStagesInQuest());
        int keyStageNumber = Formulas.randomNumber.nextInt( // порядковый номер ключевого этапа,
                quest.getStagesInQuest() - questValuesVariants[quest.getDifficultyRatio()].getMinKeyStage() + 1) +
                questValuesVariants[quest.getDifficultyRatio()].getMinKeyStage();
        return keyStageNumber;

    }

    public static String[][] calculateStagesStructure(QuestConstructor quest, String[][] stagesStructure){
        int introStages;
        String[] typeStructure = new String[quest.getStagesInQuest()];
        String[] spaceStructure = Formulas.calculateOpenSpace(quest);

        for(int i = 0; i < stagesStructure.length; i++){
            typeStructure[i] = "";
        }

        introStages = Formulas.randomNumber.nextInt(QuestValuesVariants.values()[quest.getDifficultyRatio()].getMaxIntroStage() + 1);

        for(int i = 0; i <= introStages; i++){
            typeStructure[i] = "Интро";
        }

        if (quest.isKeyObject()){
            typeStructure[quest.getKeyStageNumber()] = "Ключевой";
            for (int i = quest.getKeyStageNumber() + 1; i < stagesStructure.length; i++){
                typeStructure[quest.getKeyStageNumber()] = "Аутро";
            }
        }
        logger.info("Типовая структура квеста: " + typeStructure);
        logger.info("Пространственная структура квеста: " + spaceStructure);

        stagesStructure = new String[][]{typeStructure, spaceStructure};
        logger.info("Двухмерный массив структуры квеста: " + stagesStructure);

        return stagesStructure;
    }

    public static String calculateStageType(CharacterCreator character, StageConstructor stage){
        String stageType = "Спокойный";
        return stageType;
    }


// РАСЧЕТЫ СОПЕРНИКОВ

    public static List<Integer> calculateStatsFromLevel(EnemyHumanCreator enemyHuman) {

        int statsPointsLeft = enemyHuman.getLevel() * 4;
        List<Integer> statsMassive = new ArrayList<>();
        logger.info("Доступно очков статов: " + statsPointsLeft);
        int minStatsPoints = statsPointsLeft / 10;
        for (int i = 0; i < 4; i++) { // раскидываем минимальные значения
            statsMassive.add(minStatsPoints);
            statsPointsLeft -= minStatsPoints;
            logger.info("Добавлено минимальное значение " + statsMassive.get(i) + " в позицию листа " + i + ". Остаток очков: " + statsPointsLeft);
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

    public static int calculateEnemiesPoints(QuestConstructor quest) { // вычисляем количество очков противников для квеста

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
            logger.info("Индекс выбранного предмета в отфильрованном листе " + filteredItemNumber +
                    ". Соответствующий предмет: " + filteredItems.get(filteredItemNumber) + " " + filteredItems.get(filteredItemNumber).getItemName());
            if (filteredItems.get(filteredItemNumber).equals(Items.JAWS)) {
                logger.info("Зачисляем джос");
                if (Formulas.getProbableBoolean(filteredItems.get(filteredItemNumber).getGenerationChance())) {
                    logger.info("Зачисление джос прошло проверку вариации " + filteredItems.get(filteredItemNumber).getGenerationChance() + "%");
                    jawsToAdd = calculateJaws(enemyHuman);

                    if (jawsToAdd * filteredItems.get(filteredItemNumber).getItemPoints()> itemsPointsLeft) {
                        jawsToAdd = itemsPointsLeft / filteredItems.get(filteredItemNumber).getItemPoints();
                    }
                    itemsPointsLeft -= jawsToAdd * filteredItems.get(filteredItemNumber).getItemPoints();
                    jawsToAccount += jawsToAdd;
                    logger.info("Всего джос: " + jawsToAccount + ". Лимит для этого противника: " + jawsLimit);
                }

                if (jawsToAccount > jawsLimit) {
                    itemsPointsLeft += (jawsToAccount - jawsLimit) * filteredItems.get(filteredItemNumber).getItemPoints();
                    jawsToAccount = jawsLimit;
                    logger.info("Всего джос: " + jawsToAccount + " достигло максимума");
                    filteredItems.remove(filteredItemNumber);
                }
            }
            else {

                if (filteredItems.get(filteredItemNumber).getItemPoints() > itemsPointsLeft) { // проверка на то что очков хватает
                    logger.info("Количество нераспределённых очков предметов: " + itemsPointsLeft +
                            ". Предмет " + filteredItems.get(filteredItemNumber).getItemName() + " стоимостью " +
                            filteredItems.get(filteredItemNumber).getItemPoints() + " не по карману");
                    filteredItems.remove(filteredItemNumber);
                }
                else {

                    if (getProbableBoolean(filteredItems.get(filteredItemNumber).getGenerationChance())) {
                        logger.info("Вероятность " + filteredItems.get(filteredItemNumber).getGenerationChance() + " сыграла. " +
                                "В выборку попал предмет: " + filteredItems.get(filteredItemNumber) +
                                ". Количество нераспределённых очков предметов: " + itemsPointsLeft);
                        //todo перенести проверку содержания предмета в листе в отдельный метод в Checks
                        if (enemyItems.contains(filteredItems.get(filteredItemNumber).getItemName())){ // проверка на добавление в стек
                            logger.info("В листе уже имеется предмет " + filteredItems.get(filteredItemNumber).getItemName() +
                                    ". Закидываем его в стек. Массив: " + enemyItems);

                            List<String> changedEnemyItems = formItemStacks(enemyItems, filteredItems.get(filteredItemNumber).getItemName());

                            if(enemyItems.equals(changedEnemyItems)){
                                logger.info("Массивы равны, значит enemyItems полон, предмет некуда добавить. Удаляем этот предмет из filteredItems");
                                filteredItems.remove(filteredItemNumber);
                            }
                            else{
                                itemsPointsLeft -= filteredItems.get(filteredItemNumber).getItemPoints();
                                i++;
                            }
                            logger.info("Массив после внесения предмета в стек: " + enemyItems +
                                    ". Количество нераспределённых очков предметов: " + itemsPointsLeft);
                        }
                        else if (enemyItems.size() < 4){
                            enemyItems.set(bagPlaceID, filteredItems.get(filteredItemNumber).getItemName());
                            i++;
                            bagPlaceID++;

                            if (filteredItems.get(filteredItemNumber).isUnique()) {
                                logger.info("Предмет " + filteredItems.get(filteredItemNumber) + " является уникальным");
                                filteredItems.remove(filteredItemNumber);
                                logger.info("Предмет удалён из списка отфильтрованных предметов");

                            }
                        }
                        else{
                            logger.info("EnemyItems полон, предмет некуда добавить. Удаляем этот предмет из filteredItems");
                            filteredItems.remove(filteredItemNumber);
                        }


                    }


                }

            }
        }
        enemyHuman.setJaws(jawsToAccount);

        logger.info("Джос перечислено: " + enemyHuman.getJaws() +
                ". Лист выборки предметов: " + enemyItems +
                ". Количество нераспределённых очков предметов: " + itemsPointsLeft);

        return enemyItems;
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

    public static int calculateItemsPoints(EnemyHumanCreator enemyHuman){
        int itemsPoints = Formulas.randomNumber.nextInt(enemyHuman.getLevel() / 2 ) * 10 + 10;
        itemsPoints += Formulas.randomNumber.nextInt(21) - 10;

        return itemsPoints;
    }

    public static int[] distributeEnemiesPoints(QuestConstructor quest, String[] stageStructure) { // вычисляем количество очков противников для квеста
        int questEnemiesPoints = quest.getQuestEnemyPoints();
        int pointsLeft = questEnemiesPoints;
        int[] enemiesPoints = new int[quest.getStagesInQuest()];
        int stagePoints;


        for (int i = 0; i < stageStructure.length; i++){
            switch (stageStructure[i]){
                case "Интро":
                    stagePoints = (questEnemiesPoints / stageStructure.length * (Formulas.randomNumber.nextInt(5)+3)) / 10;
                    enemiesPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Ключевой":
                    stagePoints = (questEnemiesPoints / stageStructure.length * (Formulas.randomNumber.nextInt(4)+5)) / 10;
                    enemiesPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Аутро":
                    stagePoints = (questEnemiesPoints / stageStructure.length * (Formulas.randomNumber.nextInt(2)+1)) / 10;
                    enemiesPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "":
                    stagePoints = questEnemiesPoints / stageStructure.length;
                    enemiesPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                default:
                    logger.info("Не распознан тип этапа");
                    enemiesPoints[i] = 0;
            }
            logger.info("На этап " + (stageStructure[i]+1) +
                    " начислено " + enemiesPoints[i] +
                    " очков противника. Осталось очков: " + pointsLeft);
        }

        logger.info("Начисленные очки противника поэтапно: " + enemiesPoints +
                ". Осталось очков: " + pointsLeft);

        for (int i = pointsLeft; i>0; i--){
            enemiesPoints[Formulas.randomNumber.nextInt(quest.getStagesInQuest())] ++;
            pointsLeft--;
        }
        logger.info("Начисленные очки противника поэтапно: " + enemiesPoints +
                ". Осталось очков: " + pointsLeft);

        return enemiesPoints;
    }

    public static int buyStageEnemies(CharacterCreator character, QuestConstructor quest, StageConstructor stage, EnemyHumanCreator enemy){
        List<Enemies> chosenEnemies = new ArrayList<>();
        int enemiesPointsLeft = stage.getStageEnemiesPoints();
        List<Enemies> sortedEnemies = Enemies.filterEnemies(quest);
        Enemies chosen;

        while (sortedEnemies.size()>0){
            int sortedEnemyNumber = Formulas.randomNumber.nextInt(sortedEnemies.size());
            chosen = sortedEnemies.get(sortedEnemyNumber);

            if (chosen.getEnemyPoints() > enemiesPointsLeft){
                logger.info("На противника " + chosen.getEnemyName() + " не хватает очков. Удаляем его из подборки");
                sortedEnemies.remove(sortedEnemyNumber);
            }
            else{
                enemiesPointsLeft -= chosen.getEnemyPoints();
                logger.info("Противник " + chosen.getEnemyName() + " приобретён на этап. Остаток очков противника: " + enemiesPointsLeft);
                chosenEnemies.add(chosen);
                logger.info("Список нанятых: " + chosenEnemies);
            }
        }

        for (int i = 0 ; i < chosenEnemies.size() ; i++){

            try {
                enemy.setEnemyHumanID(FileManager.getID(enemy.getEnemyIDCounterFile()));
                logger.info("ID противника: " + enemy.getEnemyHumanID());
                EnemyHumanCreator.createNewEnemyHuman(quest, stage, enemy, chosenEnemies.get(i));
                //todo прописать создание объекта противника

            } catch (IOException e) {
                logger.info("Произошла ошибка при создании противника" + enemy.getEnemyHumanID());
                e.printStackTrace();
            }

            try {
                FileManager.fillPojoToJsonFile(character, quest, stage, enemy);
            } catch (IOException e) {
                logger.info("Произошла ошибка в создании файла противника");
                e.printStackTrace();
            }
        }

        return enemiesPointsLeft;
    }

// РАСЧЕТЫ СИСТЕМ ОХРАНЫ

    public static int calculateSystemsPoints(QuestConstructor quest) { // вычисляем количество очков систем охраны для квеста
        //todo Переписать коэффициенты в формулах для систем охраны
        int questSystemsPoints = quest.getQuestLevel() * 150 +
                quest.getDifficultyRatio() * (100 + randomNumber.nextInt(51)) +
                quest.getStagesInQuest() * 50;
        return questSystemsPoints;

    }

    public static int[] distributeSystemsPoints(QuestConstructor quest, String[] stageStructure) { // вычисляем количество очков систем охраны для квеста
    int[] systemsPoints = new int[quest.getStagesInQuest()];
        int questSystemsPoints = quest.getQuestSystemsPoints();
        int pointsLeft = questSystemsPoints;
        int stagePoints;


        for (int i = 0; i < stageStructure.length; i++){
            switch (stageStructure[i]){
                case "Интро":
                    stagePoints = (questSystemsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(6)+5)) / 10;
                    systemsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Ключевой":
                    stagePoints = (questSystemsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(3)+8)) / 10;
                    systemsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Аутро":
                    stagePoints = (questSystemsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(1)+1)) / 10;
                    systemsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "":
                    stagePoints = (questSystemsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(8))) / 10;;
                    systemsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                default:
                    logger.info("Не распознан тип этапа");
                    systemsPoints[i] = 0;
            }
            logger.info("На этап " + (stageStructure[i]+1) +
                    " начислено " + systemsPoints[i] +
                    " очков систем охраны. Осталось очков: " + pointsLeft);
        }

        logger.info("Начисленные очки систем охраны поэтапно: " + systemsPoints +
                ". Осталось очков: " + pointsLeft);

        for (int i = pointsLeft; i>0; i--){
            systemsPoints[Formulas.randomNumber.nextInt(quest.getStagesInQuest())] ++;
            pointsLeft--;
        }
        logger.info("Начисленные очки систем охраны поэтапно: " + systemsPoints +
                ". Осталось очков: " + pointsLeft);

    return systemsPoints;
}

// РАСЧЕТЫ ИНТЕРАКТИВНЫХ ОБЪЕКТОВ
public static int calculateObjectsPoints(QuestConstructor quest) { // вычисляем количество очков интерактивных объектов для квеста
    //todo Переписать формулы для объектов

    int questObjectsPoints = quest.getQuestLevel() * 150 +
            quest.getDifficultyRatio() * (100 + randomNumber.nextInt(51)) +
            quest.getStagesInQuest() * 50;
    return questObjectsPoints;

}


    public static int[] distributeObjectsPoints(QuestConstructor quest, String[] stageStructure) { // вычисляем количество очков интерактивных объектов для квеста
        int[] objectsPoints = new int[quest.getStagesInQuest()];
        int questObjectsPoints = quest.getQuestObjectsPoints();
        int pointsLeft = questObjectsPoints;
        int stagePoints;

        for (int i = 0; i < stageStructure.length; i++){
            switch (stageStructure[i]){
                case "Интро":
                    stagePoints = (questObjectsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(8)+5)) / 10;
                    objectsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Ключевой":
                    stagePoints = (questObjectsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(3)+2)) / 10;
                    objectsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "Аутро":
                    stagePoints = (questObjectsPoints / stageStructure.length * (Formulas.randomNumber.nextInt(7)+4)) / 10;
                    objectsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                case "":
                    stagePoints = questObjectsPoints / stageStructure.length;
                    objectsPoints[i] = stagePoints;
                    pointsLeft -= stagePoints;
                    break;
                default:
                    logger.info("Не распознан тип этапа");
                    objectsPoints[i] = 0;
            }
            logger.info("На этап " + (stageStructure[i]+1) +
                    " начислено " + objectsPoints[i] +
                    " очков интерактивных объектов. Осталось очков: " + pointsLeft);
        }

        logger.info("Начисленные очки интерактивных объектов поэтапно: " + objectsPoints +
                ". Осталось очков: " + pointsLeft);

        for (int i = pointsLeft; i>0; i--){
            objectsPoints[Formulas.randomNumber.nextInt(quest.getStagesInQuest())] ++;
            pointsLeft--;
        }
        logger.info("Начисленные очки интерактивных объектов поэтапно: " + objectsPoints +
                ". Осталось очков: " + pointsLeft);

        return objectsPoints;
    }


// РАСЧЕТЫ ПРЕДМЕТОВ

    public static List<String> formItemStacks(List<String> items, String newItem) {

        int changedItemIndex = -1;

        for (int k = 0; k < items.size(); k++) { // нахождение последнего стека предмета в массиве
            if (items.get(k).contains(newItem)) {
                changedItemIndex = k;
            }
        }

        if (Checks.isItemFullStack(items.get(changedItemIndex))) {
            if (items.size() == 4){
                logger.info("Последний стек предмета полон, мест под новый стек нет");
                return items;
            }

            items.set(items.size(), newItem);
            logger.info("Создан новый стек предмета. Массив предметов: " + items);

            return items;
        }

        logger.info("Найден неполный стек предмета, пополняем его на предмет. Массив предметов до добавления: " + items);
        items.set(changedItemIndex, addItemToStack(items.get(changedItemIndex)));
        logger.info("Стек предмета пополнен. Массив предметов после добавления: " + items);

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

}


