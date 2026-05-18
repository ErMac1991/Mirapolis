package enums;

import constructors.QuestConstructor;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public enum Enemies {
    DUMMY("Человек",
            "Манекен",
            1,
            25,
            100),
    HEAVYDUMMY("Машина",
            "Тяжёлый манекен",
            5,
            30,
            200);

    String enemyType;
    String enemyName;
    int enemyMinLevel;
    int enemyMaxLevel;
    int enemyPoints;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Enemies.class);

    public String getEnemyName() {
        return enemyName;
    }
    public int getEnemyMinLevel() {
        return enemyMinLevel;
    }
    public int getEnemyMaxLevel() {
        return enemyMaxLevel;
    }
    public int getEnemyPoints() {
        return enemyPoints;
    }
    public String getEnemyType() {
        return enemyType;
    }
    final File EnemyCounterFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\СистемныеФайлы\\EnemiesCounter.txt");


    Enemies(String enemyType, String enemyName, int enemyMinLevel, int enemyMaxLevel, int enemyPoints) {
        this.enemyType = enemyType;
        this.enemyName = enemyName;
        this.enemyMinLevel = enemyMinLevel;
        this.enemyMaxLevel = enemyMaxLevel;
        this.enemyPoints = enemyPoints;
    }

    public File getEnemyCounterFile() {
        return EnemyCounterFile;
    }

    public static List<Enemies> filterEnemies(QuestConstructor quest) { // получаем выборку подходящих соперников
        List<Enemies> filteredEnemies = Arrays.stream(Enemies.values())
                .filter(Enemies -> Enemies.getEnemyMinLevel() <= quest.getQuestLevel()) // фильтрует типы квеста, подходящие по уровню
                .filter(Enemies -> Enemies.getEnemyMaxLevel() >= quest.getQuestLevel())
                .collect(Collectors.toList()); // Собираем в список
        logger.info("Список подходящих типов противников: " + filteredEnemies);

        if (filteredEnemies.size() == 0) {
            logger.info("Ни один из типов противников не подходит для уровня: " + quest.getQuestLevel());
            return null;
        }

        return filteredEnemies;
    }





}
