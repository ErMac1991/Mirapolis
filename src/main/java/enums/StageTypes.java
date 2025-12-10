package enums;

import constructors.QuestConstructor;
import constructors.StageConstructor;

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

    public static void chooseStageType(QuestConstructor quest, StageConstructor stage){ // выбирает подходящий тип для сгенерированного квеста
        //todo придумать как рассчитывать и генерировать структуру квестов по этапам и как заполнять этапы помещениями


    }


}

