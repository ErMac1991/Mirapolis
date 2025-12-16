package constructors;

import operations.FileManager;

import java.io.File;
import java.io.IOException;

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

}
