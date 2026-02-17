package enums;

public enum Systems {
    TESTSYSTEM("Тестовая система охраны", 1, 30, 1, 10),
    CAMERA("Камера наблюдения", 7, 30, 7, 35),
    TURRET("Турель", 16, 30,45, 72)
    ;

    String systemName;
    int systemMinLevel;
    int systemMaxLevel;
    int systemMinHardness;
    int systemMaxHardness;

    Systems(String systemName, int systemMinLevel, int systemMaxLevel, int systemMinHardness, int systemMaxHardness) {
        this.systemName = systemName;
        this.systemMinLevel = systemMinLevel;
        this.systemMaxLevel = systemMaxLevel;
        this.systemMinHardness = systemMinHardness;
        this.systemMaxHardness = systemMaxHardness;
    }
}
