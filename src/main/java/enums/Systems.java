package enums;

public enum Systems {
    CAMERA("Камера наблюдения", 7, 30, 7, 35)
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
