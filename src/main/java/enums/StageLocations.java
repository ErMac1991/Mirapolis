package enums;

import constructors.QuestConstructor;
import constructors.StageConstructor;
import operations.Formulas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StageLocations {

    ABSTRACTROOM(
            "Комната",
            2,
            8,
            false,
            false,
            0
    ),
    ABSTRACTHALL(
            "Холл",
            4,
            8,
            false,
            true,
            0
    ),
    ABSTRACTSTREET(
            "Улица",
            1,
            6,
            true,
            true,
            0
    )

    ;
    String stageName;
    int minStageSize;
    int maxStageSize;
    boolean isOpenSpace;
    boolean isCanBeIntro; // может быть входнолй локацией
    int minDifficultyRatio;


    public String getStageName() {
        return stageName;
    }
    public int getMinStageSize() {
        return minStageSize;
    }
    public int getMaxStageSize() {
        return maxStageSize;
    }
    public boolean isOpenSpace() {
        return isOpenSpace;
    }
    public boolean isCanBeIntro() {
        return isCanBeIntro;
    }
    public int getMinDifficultyRatio() {
        return minDifficultyRatio;
    }

    StageLocations(String stageName, int minStageSize, int maxStageSize, boolean isOpenSpace, boolean isCanBeIntro, int minDifficultyRatio) {
        this.stageName = stageName;
        this.minStageSize = minStageSize;
        this.maxStageSize = maxStageSize;
        this.isOpenSpace = isOpenSpace;
        this.isCanBeIntro = isCanBeIntro;
        this.minDifficultyRatio = minDifficultyRatio;
    }

    public static void chooseLocation(QuestConstructor quest, StageConstructor stage, String stageStatus, String spaceStatus){ // выбирает подходящий тип для сгенерированного квеста
        //todo придумать как рассчитывать и генерировать структуру квестов по этапам и как заполнять этапы помещениями

        Stream<StageLocations> filteredStageTypes = Arrays.stream(StageLocations.values());
        List<StageLocations> filteredStageLocationsList;

        StageLocations chosenLocation;

        filteredStageTypes.filter(StageLocations -> StageLocations.getMinDifficultyRatio() <= quest.getDifficultyRatio());

        switch (stageStatus){
            case "Интро":
                filteredStageTypes.filter(StageLocations -> StageLocations.isCanBeIntro());
                break;
            case "Ключевой":
                stage.setKeyStage(true);
                break;

            default:
                logger.info("Обычный или неизвестный этап");
                break;
        }

        switch (spaceStatus){
            case "Улица":
                filteredStageTypes.filter(StageLocations -> StageLocations.isOpenSpace());
                break;
            case "Здание":
                filteredStageTypes.filter(StageLocations -> !StageLocations.isOpenSpace());
                break;

            default:
                logger.info("Неизвестный статус пространства");
                break;
        }



        filteredStageLocationsList = filteredStageTypes.collect(Collectors.toList()); // Собираем в список
        logger.info("Список подходящих локаций: " + filteredStageLocationsList);

        chosenLocation = filteredStageLocationsList.get(Formulas.randomNumber.nextInt(filteredStageLocationsList.size()));
        logger.info("Выбранная локация: " + chosenLocation);

        stage.setStageName(chosenLocation.getStageName());
        stage.setStageSize(Formulas.calculateStageSize(chosenLocation));
        logger.info("Назначена локация: " + stage.getStageName() +
                " за номером: " + stage.getStageNumber() +
                " и размером: " + stage.getStageSize());

    }


}

