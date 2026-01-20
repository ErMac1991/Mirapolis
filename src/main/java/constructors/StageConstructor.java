package constructors;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.StageTypes;
import operations.Checks;
import operations.FileManager;
import operations.Formulas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StageConstructor {
    String stageName; // название территории этапа
    int stageSize; // размер этапа
    String stageType; // тип этапа: спокойный, экшн, засада и т.д.
    int stageNumber; // порядковый номер текущего этапа
    boolean isStageOpenSpace; // этап под открытым небом или в помещении
    boolean isKeyStage; // содержит ли этап ключевой интерактивный объект

    List<String> objectsOnStage; // объекты на этапе
    int stageObjectsPoints;
    List<String> enemiesOnStage; // противники на этапе
    int stageEnemiesPoints;
    List<String> systemsOnStage; // системы охраны на этапе
    int stageSystemsPoints;
    List<String> itemsOnStage; // системы охраны на этапе
    int stageItemsPoints;

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
    public boolean isStageOpenSpace() {
        return isStageOpenSpace;
    }
    public void setStageOpenSpace(boolean stageOpenSpace) {
        this.isStageOpenSpace = stageOpenSpace;
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
    public List<String> getItemsOnStage() {
        return itemsOnStage;
    }
    public void setItemsOnStage(List<String> itemsOnStage) {
        this.itemsOnStage = itemsOnStage;
    }
    public int getStageItemsPoints() {
        return stageItemsPoints;
    }
    public void setStageItemsPoints(int stageItemsPoints) {
        this.stageItemsPoints = stageItemsPoints;
    }
    public boolean isKeyStage() {
        return isKeyStage;
    }
    public void setKeyStage(boolean keyStage) {
        isKeyStage = keyStage;
    }

    public static void chooseStage(ObjectMapper objectMapper, CharacterCreator character, QuestConstructor quest, StageConstructor stage) throws IOException {
        System.out.println(quest.getQuestID());
        if (!Checks.isFileExist("Персонажи\\" + character.getUserLogin() + "\\Квесты\\Q" + quest.getQuestID() + "\\S" + stage.getStageNumber() + "\\StageData.txt")) {
            System.out.println("При выборе этапа " + stage.getStageNumber() + " файл этапа квеста: StageData.txt не найден");
            return;
        }
        System.out.println("При выборе этапа " + stage.getStageNumber() + " файл этапа квеста:  StageData.txt найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\stage" + stage.getStageNumber() + "\\StageData.txt")));
        stage = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\stage" + stage.getStageNumber() + "\\StageData.txt")),
                objectMapper, stage);
        System.out.println("Выбран этап " + stage.getStageNumber() + " квеста " + quest.getQuestID());
    }

    public  static void generateAllStages(QuestConstructor quest, CharacterCreator character, StageConstructor stage) throws IOException {
        String[][] stagesStructure = new String[2][quest.getStagesInQuest()];
        stagesStructure = Formulas.calculateQuestStructure(quest, stagesStructure);

        for(int i = 1; i <= quest.getStagesInQuest(); i++){ // цикл по созданию этапов

            // не удалять
            stage.setStageNumber(i);
            stage.setKeyStage(false);
            stage.setObjectsOnStage(null);
            stage.setStageObjectsPoints(0);
            stage.setEnemiesOnStage(null);
            stage.setStageEnemiesPoints(0);
            stage.setSystemsOnStage(null);
            stage.setStageSystemsPoints(0);

            StageTypes.chooseLocation(quest, stage, stagesStructure[1][i], stagesStructure[2][i]);
            stage.setStageType("TestStageType"); // вычислять исходя из присутствия соперников, известности игрока, сложности

            // todo создать метод расчёта параметров этапа
            Formulas.calculateStageParameters(quest, stage);
            FileManager.fillPojoToJsonFile(quest, character, stage);
            System.out.println("Этап " + i + " сгенерирован");
        }
    }
}
