import java.util.List;

public class StageConstructor {
    String stageName; // название территории этапа
    int stageSize; // размер этапа
    String stageType; // тип этапа: спокойный, экшн, засада и т.д.
    int stageNumber; // порядковый номер текущего этапа
    boolean stageIsOpenSpace; // этап под открытым небом или в помещении
    List<String> objectsOnStage; // объекты на этапе
    int stageObjectsPoints;
    List<String> enemiesOnStage; // противники на этапе
    int stageEnemiesPoints;
    List<String> systemsOnStage; // системы охраны на этапе
    int stageSystemsPoints;

    public String getStageName() {
        return stageName;
    }
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    public int getStageSize() {
        return stageSize;
    }
    public void setStageSize(int stageSize) {
        this.stageSize = stageSize;
    }
    public String getStageType() {
        return stageType;
    }
    public void setStageType(String stageType) {
        this.stageType = stageType;
    }
    public int getStageNumber() {
        return stageNumber;
    }
    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
    public boolean isStageIsOpenSpace() {
        return stageIsOpenSpace;
    }
    public void setStageIsOpenSpace(boolean stageIsOpenSpace) {
        this.stageIsOpenSpace = stageIsOpenSpace;
    }
    public List<String> getObjectsOnStage() {
        return objectsOnStage;
    }
    public void setObjectsOnStage(List<String> objectsOnStage) {
        this.objectsOnStage = objectsOnStage;
    }
    public List<String> getEnemiesOnStage() {
        return enemiesOnStage;
    }
    public void setEnemiesOnStage(List<String> enemiesOnStage) {
        this.enemiesOnStage = enemiesOnStage;
    }
    public List<String> getSystemsOnStage() {
        return systemsOnStage;
    }
    public void setSystemsOnStage(List<String> systemsOnStage) {
        this.systemsOnStage = systemsOnStage;
    }
    public int getStageObjectsPoints() {
        return stageObjectsPoints;
    }
    public void setStageObjectsPoints(int stageObjectsPoints) {
        this.stageObjectsPoints = stageObjectsPoints;
    }
    public int getStageEnemiesPoints() {
        return stageEnemiesPoints;
    }
    public void setStageEnemiesPoints(int stageEnemiesPoints) {
        this.stageEnemiesPoints = stageEnemiesPoints;
    }
    public int getStageSystemsPoints() {
        return stageSystemsPoints;
    }
    public void setStageSystemsPoints(int stageSystemsPoints) {
        this.stageSystemsPoints = stageSystemsPoints;
    }

    public static void generateStageOfQuest(QuestConstructor quest){


    }


}
