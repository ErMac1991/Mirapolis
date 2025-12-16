package constructors;

import enums.Enemies;
import operations.FileManager;
import operations.Formulas;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static constructors.EnemyMachineCreator.setEnemyMachineParameters;
import static enums.Enemies.filterEnemies;

public class EnemyHumanCreator extends Unit {
    int enemyHumanID;
    String enemyHumanName; // наименование противника
    int enemyHumanPoints; // количество очков противника
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String leftHand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightHand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftLeg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightLeg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    final File EnemyIDCounterFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\Системные файлы\\EnemiesCounter.txt");

    // ГЕТТЕРЫ И СЕТТЕРЫ

    public int getEnemyHumanID() {
        return enemyHumanID;
    }
    public void setEnemyHumanID(int enemyHumanID) {
        this.enemyHumanID = enemyHumanID;
    }
    public String getEnemyHumanName() {
        return enemyHumanName;
    }
    public void setEnemyHumanName(String enemyHumanName) {
        this.enemyHumanName = enemyHumanName;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getEnemyHumanPoints() {
        return enemyHumanPoints;
    }
    public void setEnemyHumanPoints(int enemyHumanPoints) {
        this.enemyHumanPoints = enemyHumanPoints;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getLeftHand() {
        return leftHand;
    }
    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
    }
    public String getRightHand() {
        return rightHand;
    }
    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
    }
    public String getLeftLeg() {
        return leftLeg;
    }
    public void setLeftLeg(String leftLeg) {
        this.leftLeg = leftLeg;
    }
    public String getRightLeg() {
        return rightLeg;
    }
    public void setRightLeg(String rightLeg) {
        this.rightLeg = rightLeg;
    }
    public int getEndurance() {
        return endurance;
    }
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
    public int getAttentiveness() {
        return attentiveness;
    }
    public void setAttentiveness(int attentiveness) {
        this.attentiveness = attentiveness;
    }
    public int getReaction() {
        return reaction;
    }
    public void setReaction(int reaction) {
        this.reaction = reaction;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getEnduranceMod() {
        return enduranceMod;
    }
    public void setEnduranceMod(int enduranceMod) {
        this.enduranceMod = enduranceMod;
    }
    public int getAttentivenessMod() {
        return attentivenessMod;
    }
    public void setAttentivenessMod(int attentivenessMod) {
        this.attentivenessMod = attentivenessMod;
    }
    public int getReactionMod() {
        return reactionMod;
    }
    public void setReactionMod(int reactionMod) {
        this.reactionMod = reactionMod;
    }
    public int getStrengthMod() {
        return strengthMod;
    }
    public void setStrengthMod(int strengthMod) {
        this.strengthMod = strengthMod;
    }
    public String getFirstBagPlace() {
        return firstBagPlace;
    }
    public void setFirstBagPlace(String firstBagPlace) {
        this.firstBagPlace = firstBagPlace;
    }
    public String getSecondBagPlace() {
        return secondBagPlace;
    }
    public void setSecondBagPlace(String secondBagPlace) {
        this.secondBagPlace = secondBagPlace;
    }
    public String getThirdBagPlace() {
        return thirdBagPlace;
    }
    public void setThirdBagPlace(String thirdBagPlace) {
        this.thirdBagPlace = thirdBagPlace;
    }
    public String getFourthBagPlace() {
        return fourthBagPlace;
    }
    public void setFourthBagPlace(String fourthBagPlace) {
        this.fourthBagPlace = fourthBagPlace;
    }
    public File getEnemyIDCounterFile() {
        return EnemyIDCounterFile;
    }

    public void createNewEnemyHuman(CharacterCreator character, QuestConstructor quest, StageConstructor stage, EnemyHumanCreator enemyHuman) throws IOException {
        enemyHuman.setEnemyHumanID(FileManager.getID(enemyHuman.getEnemyIDCounterFile()));

        FileManager.fillPojoToJsonFile(character, quest, stage, enemyHuman);

    }

    // набираем противников на квестовые очки противников
    public static void spendEnemyPoints(QuestConstructor quest, EnemyHumanCreator enemyHuman, EnemyMachineCreator enemyMachine){

        int index;
        List<Enemies> filteredEnemies = filterEnemies(quest);
        int enemyPointsRemains = quest.getQuestEnemyPoints();
        System.out.println("Количество очков противника для распределения: " + enemyPointsRemains);
        int minEnemyPoints = Arrays.stream(Enemies.values())
                .min(Comparator.comparingInt(Enemies::getEnemyPoints))
                .get()
                .getEnemyPoints();
        System.out.println("Минимальная стоимость противника из выборки: " + minEnemyPoints + " очков");

        while (enemyPointsRemains >= minEnemyPoints){

            index = Formulas.randomNumber.nextInt(filteredEnemies.size());
            System.out.println("Выбран индекс листа " + index + ". Противник под этим индексом: " + filteredEnemies.get(index).getEnemyName());

            if (enemyPointsRemains >= filteredEnemies.get(index).getEnemyPoints()){
                enemyPointsRemains -= filteredEnemies.get(index).getEnemyPoints();
                System.out.println("Стоимость противника " + filteredEnemies.get(index).getEnemyName() +
                        " списана в размере: " + filteredEnemies.get(index).getEnemyPoints() +
                        " очков. Остаток: " + enemyPointsRemains + " очков");

                switch (filteredEnemies.get(index).getEnemyType()){
                    case "Человек":
                        setEnemyHumanParameters(quest, enemyHuman, filteredEnemies.get(index));
                        enemyHuman.setLeftHand("Плоть");
                        enemyHuman.setRightHand("Плоть");
                        enemyHuman.setLeftLeg("Плоть");
                        enemyHuman.setRightLeg("Плоть");
                        enemyHuman.setHead("Плоть");
                        enemyHuman.setBody("Плоть");

                    case "Машина":
                        setEnemyMachineParameters(quest, enemyMachine, filteredEnemies.get(index));
                    default:
                        System.out.println("Выбрано неведомое нечто");
                }

            }
            else{
                System.out.println("На противника " + filteredEnemies.get(index).getEnemyName() + " очков не хватает.");
                filteredEnemies.remove(index);
                System.out.println("Удалили его из списка");
            }
        }
        System.out.println("Все очки противников растрачены. Остаток: " + enemyPointsRemains);

    }

    public static void setEnemyHumanParameters(QuestConstructor quest, EnemyHumanCreator enemyHuman, Enemies enemyUnit) { // Заполняем Pojo противника человека

        List<Integer> statsToDistribute = Formulas.countStatsFromLevel(enemyHuman);
        int counter;

//todo Заполнить параметры противника человека И/ИЛИ киборга
        enemyHuman.setEnemyHumanName(enemyUnit.getEnemyName()); // передаём наименование противника
        System.out.println("Наименование противника человека " + enemyHuman.getEnemyHumanName() + " передано");
        enemyHuman.setEnemyHumanPoints(enemyUnit.getEnemyPoints()); // передаём стоимость противника в ОП
        System.out.println("Количество очков противника человека " + enemyHuman.getEnemyHumanPoints() + " передано");

        enemyHuman.setLevel(Formulas.calculateEnemyLevel(quest, enemyHuman));

            counter = Formulas.randomNumber.nextInt(statsToDistribute.size());
            enemyHuman.setAttentiveness(statsToDistribute.get(counter));
            statsToDistribute.remove(counter);
        System.out.println("Внимательность противника человека " + enemyHuman.getAttentiveness() + " передано");

            counter = Formulas.randomNumber.nextInt(statsToDistribute.size());
            enemyHuman.setReaction(statsToDistribute.get(counter));
            statsToDistribute.remove(counter);
        System.out.println("Реакция противника человека " + enemyHuman.getAttentiveness() + " передано");

            counter = Formulas.randomNumber.nextInt(statsToDistribute.size());
            enemyHuman.setEndurance(statsToDistribute.get(counter));
            statsToDistribute.remove(counter);
        System.out.println("Выносливость противника человека " + enemyHuman.getAttentiveness() + " передано");

            counter = Formulas.randomNumber.nextInt(statsToDistribute.size());
            enemyHuman.setStrength(statsToDistribute.get(counter));
            statsToDistribute.remove(counter);
        System.out.println("Сила противника человека " + enemyHuman.getAttentiveness() + " передано");





    }

}
