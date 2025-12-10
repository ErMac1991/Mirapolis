package enums;

import constructors.QuestConstructor;
import constructors.StageConstructor;
import operations.Formulas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


        List<StageTypes>  filteredStageTypes = Arrays.stream(StageTypes.values())
                .filter(StageTypes -> StageTypes.getMinStageSize() <= stage.getStageSize()) // фильтрует типы квеста, подходящие по уровню
                .filter(StageTypes -> StageTypes.getMaxStageSize() >= stage.getStageSize())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих типов этапов: " + filteredStageTypes);

        if (filteredStageTypes.size() == 0) {
            System.out.println("Ни один из типов квестов не подходит для уровня: " + quest.getQuestLevel());
            return;
        }

        index = Formulas.randomNumber.nextInt(filteredStageTypes.size());


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
}
