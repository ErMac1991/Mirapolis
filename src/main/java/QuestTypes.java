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
    boolean canBeOpenSpace; // Возможно ли прохождение всего задания под открытым небом
    boolean canBeMultiPlayer; // Возможно ли совместное прохождение
    static List<QuestTypes> filteredQuestTypes;
    static int index; // индекс случайного элемента листа


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
    public boolean isCanBeOpenSpace() {
        return canBeOpenSpace;
    }
    public boolean isCanBeMultiPlayer() {
        return canBeMultiPlayer;
    }

    QuestTypes(String questName, String questTask, int questMinLevel, int questMaxLevel, boolean canBeOpenSpace, boolean canBeMultiPlayer) {
        this.questName = questName;
        this.questTask = questTask;
        this.questMinLevel = questMinLevel;
        this.questMaxLevel = questMaxLevel;
        this.canBeOpenSpace = canBeOpenSpace;
        this.canBeMultiPlayer = canBeMultiPlayer;
    }

    public static void chooseQuestType(QuestConstructor quest){ // выбирает подходящий тип для сгенерированного квеста

        filteredQuestTypes = Arrays.stream(QuestTypes.values())
                .filter(QuestTypes -> QuestTypes.getQuestMinLevel() <= quest.getQuestLevel()) // фильтрует типы квеста, подходящие по уровню
                .filter(QuestTypes -> QuestTypes.getQuestMaxLevel() >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих типов квестов: " + filteredQuestTypes);

        if (filteredQuestTypes.size() == 0) {
            System.out.println("Ни один из типов квестов не подходит для уровня: " + quest.getQuestLevel());
            return;
        }

            index = Formulas.randomNumber.nextInt(filteredQuestTypes.size());


        quest.setQuestName(filteredQuestTypes.get(index).getQuestName()); // передаём название квеста
        System.out.println("Название квеста " + quest.getQuestName() + " передано");
        quest.setQuestTask(filteredQuestTypes.get(index).getQuestTask()); // передаём описание задачи
        System.out.println("Описание задачи " + quest.getQuestTask() + " передано");

        if(filteredQuestTypes.get(index).isCanBeOpenSpace()){ // передаём статус Квест под открытым небом
            quest.setQuestOpenSpace(Formulas.getProbableBoolean(5));
        } else quest.setQuestOpenSpace(false);
        System.out.println("статус \"Квест под открытым небом\": " + quest.isQuestOpenSpace() + " передан");

        if(filteredQuestTypes.get(index).isCanBeMultiPlayer()){ // передаём статус Многопользовательский квест
            quest.setQuestMultiPlayer(Formulas.getProbableBoolean(0)); // ПОКА МНОГОПОЛЬЗОВАТЕЛЬСКИЕ КВЕСТЫ НЕ РАЗРАБАТЫВАЮТСЯ
        } else quest.setQuestMultiPlayer(false);
        System.out.println("статус \"Многопользовательский квест\": " + quest.isQuestMultiPlayer() + " передан");
    }


}
