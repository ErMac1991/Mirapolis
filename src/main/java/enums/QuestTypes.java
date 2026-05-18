package enums;

import constructors.QuestConstructor;
import operations.Formulas;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public enum QuestTypes { // список видов квестов

    DATAHUNT ("Охота за информацией",
            "Выкрасть с закрытого объекта искомые данные",
            5,
            30,
            false,
            true,
            true),
    CLEARAREA("Зачистка",
            "Очистить объект от охраны и отключить охранные системы",
            1,
            10,
            true,
            true,
            false)
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
    boolean isKeyObject; // Генерируется ли ключевой объект

    static int index; // индекс случайного элемента листа

    private static final Logger logger = (Logger) LoggerFactory.getLogger(QuestTypes.class);

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
    public boolean isKeyObject() {
        return isKeyObject;
    }

    QuestTypes(String questName, String questTask, int questMinLevel, int questMaxLevel, boolean canBeOpenSpace, boolean canBeMultiPlayer, boolean isKeyObject) {
        this.questName = questName;
        this.questTask = questTask;
        this.questMinLevel = questMinLevel;
        this.questMaxLevel = questMaxLevel;
        this.canBeOpenSpace = canBeOpenSpace;
        this.canBeMultiPlayer = canBeMultiPlayer;
        this.isKeyObject = isKeyObject;
    }

    public static void chooseQuestType(QuestConstructor quest){ // выбирает подходящий тип для сгенерированного квеста

        List<QuestTypes> filteredQuestTypes = Arrays.stream(QuestTypes.values())
                .filter(QuestTypes -> QuestTypes.getQuestMinLevel() <= quest.getQuestLevel()) // фильтрует типы квеста, подходящие по уровню
                .filter(QuestTypes -> QuestTypes.getQuestMaxLevel() >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        logger.info("Список подходящих типов квестов: " + filteredQuestTypes);

        if (filteredQuestTypes.size() == 0) {
            logger.info("Ни один из типов квестов не подходит для уровня: " + quest.getQuestLevel());
            return;
        }

            index = Formulas.randomNumber.nextInt(filteredQuestTypes.size());


        quest.setQuestName(filteredQuestTypes.get(index).getQuestName()); // передаём название квеста
        logger.info("Название квеста " + quest.getQuestName() + " передано");
        quest.setQuestTask(filteredQuestTypes.get(index).getQuestTask()); // передаём описание задачи
        logger.info("Описание задачи " + quest.getQuestTask() + " передано");

        if(filteredQuestTypes.get(index).isCanBeOpenSpace()){ // передаём статус Квест под открытым небом
            quest.setQuestOpenSpace(Formulas.getProbableBoolean(5));
        } else quest.setQuestOpenSpace(false);
        logger.info("статус \"Квест под открытым небом\": " + quest.isQuestOpenSpace() + " передан");

        if(filteredQuestTypes.get(index).isCanBeMultiPlayer()){ // передаём статус Многопользовательский квест
            quest.setQuestMultiPlayer(Formulas.getProbableBoolean(0)); // ПОКА МНОГОПОЛЬЗОВАТЕЛЬСКИЕ КВЕСТЫ НЕ РАЗРАБАТЫВАЮТСЯ
        } else quest.setQuestMultiPlayer(false);
        logger.info("статус \"Многопользовательский квест\": " + quest.isQuestMultiPlayer() + " передан");
    }


}
