import java.util.List;

public enum StageObjects {

     AUTO("Автомобиль",
             1,
             25,
             7,
             8,
             55,
             80,
             "Лутабельно,Заперто,СпрятатьсяВнутри,Составной,Механизм,Электроника"),
    KPP("Пост КПП",
            1,
            18,
            8,
            8,
            41,
            68,
            "Недвижимо,Лутабельно,Заперто,Спрятаться внутри,Составной,Под небом,Управляет"),
    RECEPTIONTABLE("Стойка ресепшн",
            7,
            22,
            6,
            7,
            23,
            44,
            "Лутабельно,Управляет"),

    //todo реализовать enum тегов с возможными взаимодействиями
    ;
    String objectName; // Название интерактивного объекта
    int objectMinLevel; // Минимальный уровень квеста для внесение туда интерактивного объекта
    int objectMaxLevel; // Максимальный уровень квеста для внесение туда интерактивного объекта
    int objectMinSize; // Минимальный размер интерактивного объекта
    int objectMaxSize; // Максимальный размер интерактивного объекта
    int objectMinHardness; // Минимальная прочность интерактивного объекта (максимум 100)
    int objectMaxHardness; // Максимальная прочность интерактивного объекта (максимум 100)
    String tags; // Теги
    static List<StageObjects> filteredStageObjects;
    static int index; // индекс случайного элемента листа

    StageObjects(String objectName, int objectMinLevel, int objectMaxLevel, int objectMinSize, int objectMaxSize, int objectMinHardness, int objectMaxHardness, String tags) {
        this.objectName = objectName;
        this.objectMinLevel = objectMinLevel;
        this.objectMaxLevel = objectMaxLevel;
        this.objectMinSize = objectMinSize;
        this.objectMaxSize = objectMaxSize;
        this.objectMinHardness = objectMinHardness;
        this.objectMaxHardness = objectMaxHardness;
        this.tags = tags;
    }
}
