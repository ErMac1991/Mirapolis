package constructors;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Enemies;
import enums.StageLocations;
import enums.StageObjects;
import enums.Systems;
import operations.Checks;
import operations.FileManager;
import operations.Formulas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StageConstructor {
    String stageName; // название территории этапа
    int stageSize; // размер этапа
    String stageType; // тип этапа: спокойный, экшн, засада и т.д.
    int stageNumber; // порядковый номер текущего этапа
    boolean isStageOpenSpace; // этап под открытым небом или в помещении
    boolean isKeyStage; // содержит ли этап ключевой интерактивный объект

    int stageObjectsPoints;
    int stageEnemiesPoints;
    int stageSystemsPoints;
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
        if (!Checks.isFileExist("Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\" + stage.getStageNumber() + "\\StageData.txt")) {
            System.out.println("При выборе этапа " + stage.getStageNumber() + " файл этапа квеста: StageData.txt не найден");
            return;
        }
        System.out.println("При выборе этапа " + stage.getStageNumber() + " файл этапа квеста:  StageData.txt найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\" + stage.getStageNumber() + "\\StageData.txt")));
        stage = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Квесты\\" + quest.getQuestID() + "\\" + stage.getStageNumber() + "\\StageData.txt")),
                objectMapper, stage);
        System.out.println("Выбран этап " + stage.getStageNumber() + " квеста " + quest.getQuestID());
    }

    public  static void generateAllStages(ObjectMapper objectMapper, QuestConstructor quest, CharacterCreator character, StageConstructor stage) throws IOException {
        String[][] stagesStructure = new String[2][quest.getStagesInQuest()];
        stagesStructure = Formulas.calculateStagesStructure(quest, stagesStructure);
        int[][] stagesPoints = new int[3][quest.getStagesInQuest()];
        stagesPoints = Formulas.calculateStagesPoints(quest, stagesStructure[0]);

        for(int i = 0; i < quest.getStagesInQuest(); i++) { // цикл по созданию этапов c базовыми параметрами

            stage.setStageNumber(i);
            stage.setKeyStage(false);
            stage.setStageEnemiesPoints(stagesPoints[0][i]);
            stage.setStageSystemsPoints(stagesPoints[1][i]);
            stage.setStageObjectsPoints(stagesPoints[2][i]);

            StageLocations.chooseLocation(quest, stage, stagesStructure[0][i], stagesStructure[1][i]);
            FileManager.fillPojoToJsonFile(quest, character, stage);
            System.out.println("Этап " + (i+1) + " сгенерирован");
        }

        for(int i = 0; i < quest.getStagesInQuest(); i++) { // цикл по дополнению этапов оставшимися параметрами
            //todo вынести заполнение этапов в отдельный метод
            //todo создать и заполнить файлы противников, систем и объектов

            stage.setStageNumber(i+1);
            StageConstructor.chooseStage(objectMapper, character, quest, stage);

            StageConstructor.generateStagesFilling(character, quest, stage, stagesPoints); // создаём и заполняем файлы наполнения этапов квеста

            FileManager.fillPojoToJsonFile(quest, character, stage);
            System.out.println("Этап " + (i+1) + " дополнен");
        }
    }

    public  static void generateStagesFilling (CharacterCreator character, QuestConstructor quest, StageConstructor stage,
                                               EnemyHumanCreator enemy, int[][] stagesPoints){

        Formulas.buyStageEnemies(character, quest, stage, enemy);

        List<Systems> chosenSystems = new ArrayList<>();
        List<StageObjects> chosenObjects = new ArrayList<>();




        // методы закупа противников/систем/объектов на очки этапа
        // методы реализации остатков
        // создание файлов и запись

    }
}
