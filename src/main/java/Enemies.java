public enum Enemies {
    DUMMY("Манекен",
            1,
            30)
    ;

    String enemyName;
    int enemyMinLevel;
    int enemyMaxLevel;

    public String getEnemyName() {
        return enemyName;
    }
    public int getEnemyMinLevel() {
        return enemyMinLevel;
    }
    public int getEnemyMaxLevel() {
        return enemyMaxLevel;
    }


    Enemies(String enemyName, int enemyMinLevel, int enemyMaxLevel) {
        this.enemyName = enemyName;
        this.enemyMinLevel = enemyMinLevel;
        this.enemyMaxLevel = enemyMaxLevel;
    }


}
