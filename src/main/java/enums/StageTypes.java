package enums;

import constructors.QuestConstructor;
import constructors.StageConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StageTypes {

    ABSTRACTROOM(
            "Комната",
            2,
            8,
            false,
            false
    ),
    ABSTRACTHALL(
            "Холл",
            4,
            8,
            false,
            true
    ),
    ABSTRACTSTREET(
            "Улица",
            1,
            6,
            true,
            true
    )

    ;
    String stageName;
    int minStageSize;
    int maxStageSize;
    boolean isOpenSpace;
    boolean isCanBeIntro; // может быть входнолй локацией


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

    StageTypes(String stageName, int minStageSize, int maxStageSize, boolean isOpenSpace, boolean isCanBeIntro) {
        this.stageName = stageName;
        this.minStageSize = minStageSize;
        this.maxStageSize = maxStageSize;
        this.isOpenSpace = isOpenSpace;
        this.isCanBeIntro = isCanBeIntro;
    }

    public static void chooseStageType(QuestConstructor quest, StageConstructor stage, String stageStatus){ // выбирает подходящий тип для сгенерированного квеста
        //todo придумать как рассчитывать и генерировать структуру квестов по этапам и как заполнять этапы помещениями

        Stream<StageTypes> filteredStageTypes = Arrays.stream(StageTypes.values());

        switch (stageStatus){
            case "Интро":
                filteredStageTypes.filter(StageTypes -> StageTypes.isCanBeIntro());
                break;
            case "Ключевой":
                // Code block 2
                break;
            // ... more cases
            default:
                // Code block for no match
                break;
        }
        List<StageTypes> filteredStageTypesList = Arrays.stream(StageTypes.values())
                .filter(StageTypes -> StageTypes.getQuestMinLevel() <= quest.getQuestLevel()) // фильтрует типы квеста, подходящие по уровню
                .filter(StageTypes -> StageTypes.getQuestMaxLevel() >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих типов этапов: " + filteredStageTypes);



    }


}

