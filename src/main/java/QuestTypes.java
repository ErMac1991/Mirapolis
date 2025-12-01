import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuestTypes { // список видов квестов

    DATAHUNT ("Охота за информацией",
            "Выкрасть с закрытого объекта искомые данные",
            5,
            30,
            false,
            true),
    CLEARAREA("Зачистка",
            "Очистить объект от охраны и отключить охранные системы",
            1,
            10,
            true,
            true)
    /*CHASE("Погоня",
            "Уйти от преследования",
            8,
            15,
            true,
            false),

    ESCAPE("Побег",
            "Один из наших людей угодил за решётку, необходимо обеспечить ему побег",
            12,
            25,
            false,
            false),*/
    ;


    String questName; // Название задания
     String questTask; // Описание квестовой задачи
    int questMinLevel; // Минимальный уровень квеста для взятия
    int questMaxLevel; // Максимальный уровень квеста для взятия
    boolean isOpenSpace; // Возможно ли прохождение всго задания под открытым небом
    boolean isMultiPlayer; // Возможно ли совместное прохождение
    List<QuestTypes> filteredQuestTypes = new ArrayList<>();

    public String getQuestName() {
        return questName;
    }
    public String getQuestTask() {
        return questTask;
    }
    public int getQuestMinLevel() {
        return questMinLevel;
    }
    public int getQuestMaxLevel() {
        return questMaxLevel;
    }
    public boolean isOpenSpace() {
        return isOpenSpace;
    }
    public boolean isMultiPlayer() {
        return isMultiPlayer;
    }

    QuestTypes(String questName, String questTask, int questMinLevel, int questMaxLevel, boolean isOpenSpace, boolean isMultiPlayer) {
        this.questName = questName;
        this.questTask = questTask;
        this.questMinLevel = questMinLevel;
        this.questMaxLevel = questMaxLevel;
        this.isOpenSpace = isOpenSpace;
        this.isMultiPlayer = isMultiPlayer;
    }

    public void chooseQuestType(QuestConstructor quest){
        filteredQuestTypes = null;
        if (quest.getQuestLevel() >= QuestTypes.)


    }
}
