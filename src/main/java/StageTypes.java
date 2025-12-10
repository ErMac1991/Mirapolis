import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StageTypes {

    ABSTRACTROOM(
            "Комната",
            2,
            8,
            false
    ),
    ABSTRACTSTREET(
            "Улица",
            1,
            6,
            true
    )
    ;
    String stageName;
    int minStageSize;
    int maxStageSize;
    boolean isOpenSpace;

    public String getStageName() {
        return stageName;
    }
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    public int getMinStageSize() {
        return minStageSize;
    }
    public void setMinStageSize(int minStageSize) {
        this.minStageSize = minStageSize;
    }
    public int getMaxStageSize() {
        return maxStageSize;
    }
    public void setMaxStageSize(int maxStageSize) {
        this.maxStageSize = maxStageSize;
    }
    public boolean isOpenSpace() {
        return isOpenSpace;
    }
    public void setOpenSpace(boolean openSpace) {
        isOpenSpace = openSpace;
    }

    StageTypes(String stageName, int minStageSize, int maxStageSize, boolean isOpenSpace) {
        this.stageName = stageName;
        this.minStageSize = minStageSize;
        this.maxStageSize = maxStageSize;
        this.isOpenSpace = isOpenSpace;
    }

    public static void chooseStageType(QuestConstructor quest, StageConstructor stage){ // выбирает подходящий тип для сгенерированного квеста
        //todo придумать как рассчитывать и генерировать структуру квестов по этапам и как заполнять этапы помещениями


        List<StageTypes>  filteredStageTypes = Arrays.stream(StageTypes.values())
                .filter(StageTypes -> StageTypes.getMinStageSize() <= stage.getStageSize()) // фильтрует типы квеста, подходящие по уровню
                .filter(StageTypes -> StageTypes.getMaxStageSize() >= stage.getStageSize())
                .collect(Collectors.toList()); // Собираем в список
        System.out.println("Список подходящих типов этапов: " + filteredStageTypes);

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
}
