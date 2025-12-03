import java.util.List;

public enum StageObjects {

     AUTO("Автомобиль",
             1,
             25,
             7,
             8,
             55,
             80,
             "Лутабельно,Заперто,Убежище,Составной,Механизм,Электроника,Укрытие"),
    KPP("Пост КПП",
            1,
            18,
            8,
            8,
            41,
            68,
            "Недвижимо,Лутабельно,Заперто,Убежище,Составной,Улица,Управляет,Укрытие"),
    RECEPTIONTABLE("Стойка ресепшн",
            7,
            22,
            6,
            7,
            23,
            44,
            "Лутабельно,Управляет,Холл,Укрытие"),
    COUCH("Диван",
            1,
            30,
            5,
            7,
            13,
            39,
            "Офис,Холл,Укрытие"),
    DISPLAY("Экран",
            10,
            30,
            3,
            4,
            10,
            15,
            "Офис,Холл,Настенный,Электроника,Шум"),
    INFOSTAND("Информационный стенд",
            3,
            22,
            3,
            5,
            5,
            8,
            "Офис,Холл,Укрытие"),
    SERVERSTAND("Серверный шкаф",
            12,
            30,
            4,
            7,
            45,
            61,
            "Офис,Укрытие,Электроника,Информация,Квестовый"),
    TERMINAL("Терминал",
            4,
            30,
            4,
            7,
            47,
            53,
            "Офис,Холл,Цех,Укрытие,Электроника,Информация,Квестовый,Управляет,Заперто"),
    OFFICETABLE("Офисный стол",
            7,
            22,
            6,
            7,
            23,
            66,
            "Лутабельно,Офис,Укрытие")

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
