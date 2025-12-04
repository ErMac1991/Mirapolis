import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterHelper extends Unit {
    //todo внести переменные энергитический лимит и текущей энергии
    String userLogin; // логин игрока
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String leftHand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightHand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftLeg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightLeg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    int inventiveness; // находчивость
    int luck; // удача
    int fame; // известность
    int mentalHealth; // ментальное здоровье по 100 балльной шкале
    int inventivenessMod; // модификатор находчивости
    int luckMod; // модификатор удачи
    int fameMod; // модификатор известности
    int mentalHealthMod; // модификатор ментального здоровья
    String[] bag = new String[4]; // Массив сумки на 4 места
    List<String> storageList = new ArrayList<>();
    String storage;
    String quest;


    public CharacterHelper() {
    }

    // Констируктор для зоздания стартового персонажа
    public CharacterHelper(String userLogin) {

        this.userLogin = userLogin;
        this.level = 1;
        this.leftHand = "Плоть";
        this.rightHand = "Плоть";
        this.leftLeg = "Плоть";
        this.rightLeg = "Плоть";
        this.body = "Плоть";
        this.head = "Плоть";
        this.endurance = 5;
        this.attentiveness = 5;
        this.reaction = 5;
        this.strength = 5;
        this.inventiveness = 5;
        this.luck = 5;
        this.fame = 5;
        this.mentalHealth = 90;
        this.enduranceMod = 90;
        this.attentivenessMod = 0;
        this.reactionMod = 0;
        this.strengthMod = 0;
        this.inventivenessMod = 0;
        this.luckMod = 0;
        this.fameMod = 0;
        this.mentalHealthMod = 0;
        this.firstBagPlace = "Пусто";
        this.secondBagPlace = "Пусто";
        this.thirdBagPlace = "Пусто";
        this.fourthBagPlace = "Пусто";
        this.storage = "Пусто";
        this.quest = null;


    }


    // ГЕТТЕРЫ И СЕТТЕРЫ

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getInventiveness() {
        return inventiveness;
    }

    public void setInventiveness(int inventiveness) {
        this.inventiveness = inventiveness;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(int mentalHealth) {
        this.mentalHealth = mentalHealth;
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

    public int getInventivenessMod() {
        return inventivenessMod;
    }

    public void setInventivenessMod(int inventivenessMod) {
        this.inventivenessMod = inventivenessMod;
    }

    public int getLuckMod() {
        return luckMod;
    }

    public void setLuckMod(int luckMod) {
        this.luckMod = luckMod;
    }

    public int getFameMod() {
        return fameMod;
    }

    public void setFameMod(int fameMod) {
        this.fameMod = fameMod;
    }

    public int getMentalHealthMod() {
        return mentalHealthMod;
    }

    public void setMentalHealthMod(int mentalHealthMod) {
        this.mentalHealthMod = mentalHealthMod;
    }

    public String[] getBag() {
        return bag;
    }

    public void setBag(String[] bag) {
        this.bag = bag;
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

    public List<String> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<String> storageList) {
        this.storageList = storageList;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }


    public static void createCharacter(String userLogin) throws IOException {

        CharacterHelper newCharacter = new CharacterHelper(userLogin);

        if (Checks.isFileExist("Персонажи", newCharacter.userLogin, "Персонаж.txt")) {
            System.out.println("Персонаж с логином " + newCharacter.userLogin + " уже существует.");
            return;
        }
        FileManager.createCharacterFile(newCharacter.userLogin);
        FileManager.fillPojoToJsonFile(newCharacter);
        System.out.println("Персонаж с логином " + newCharacter.userLogin + " создан");


    }



    public static CharacterHelper chooseCharacter(String userLogin, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        System.out.println(userLogin);
        if (!Checks.isFileExist("Персонажи", userLogin, "Персонаж.txt")) {
            System.out.println("При выборе персонажа файл персонажа " + userLogin + " не найден");
            return null;
        }
        System.out.println("При выборе персонажа файл персонажа " + userLogin + " найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt")));
        character = FileManager.parseStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt")), objectMapper, character);
        System.out.println("Выбран персонаж " + character.userLogin + ". Его квест:  " + character.quest);

        return character;
    }

    public static CharacterHelper updateCharacterPojo(CharacterHelper character, CharacterHelper charactersChanges) throws IOException {

        character = charactersChanges;
        System.out.println(character.getQuest());
        return character;
    }

    //todo метод присвоения класса персонажу в зависимости от отношения лидирующего стата (или пары статов) к остальным статам


}

