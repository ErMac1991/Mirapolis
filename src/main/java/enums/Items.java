package enums;

public enum Items {
    JAWS("Джос",
            1,
            1,
            30,
            "jawsBalance",
            0),
    THING("Вещь",
            10,
            1,
            30,
            "bagPlace",
            5)
;
    String itemName;
    int itemPoints;
    int itemMinLevel;
    int itemMaxLevel;
    String changeCategory;
    int stackLimit;

    Items(String itemName, int itemPoints, int itemMinLevel, int itemMaxLevel, String changeCategory, int stackLimit) {
        this.itemName = itemName;
        this.itemPoints = itemPoints;
        this.itemMinLevel = itemMinLevel;
        this.itemMaxLevel = itemMaxLevel;
        this.changeCategory = changeCategory;
        this.stackLimit = stackLimit;
    }
}
