package enums;

import java.util.Arrays;
import java.util.List;

public enum Items {
    JAWS("Джос",
            1,
            1,
            30,
            Arrays.asList("Джос + 1"),
            0),
    THING("Вещь",
            10,
            1,
            30,
            Arrays.asList(),
            5)
;
    String itemName;
    int itemPoints;
    int itemMinLevel;
    int itemMaxLevel;
    List<String> changeMods;
    int stackLimit;

    Items(String itemName, int itemPoints, int itemMinLevel, int itemMaxLevel, List<String> changeMods, int stackLimit) {
        this.itemName = itemName;
        this.itemPoints = itemPoints;
        this.itemMinLevel = itemMinLevel;
        this.itemMaxLevel = itemMaxLevel;
        this.changeMods = changeMods;
        this.stackLimit = stackLimit;
    }
}
