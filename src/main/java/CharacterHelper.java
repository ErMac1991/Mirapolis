import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterHelper {
    //Todo СОЗДАТЬ РОДИТЕЛЬСКИЙ КЛАСС И НАСЛЫДОВАТЬ ОТ НЕГО КЛАССЫ ПЕРСОНАЖА, ПРОТИВНИКА, РОБОТА и т.д.


    String subject; // субъект применения действия
    String userLogin; // логин игрока
    int level; // уровень игрока. Высчитывается исходя из статов
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String leftHand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightHand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftLeg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightLeg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    int endurance; // выносливость
    int attentiveness; // внимательность
    int reaction; // реакция
    int strength; // сила
    int inventiveness; // находчивость
    int luck; // удача
    int fame; // известность
    int mentalHealth; // ментальное здоровье по 100 балльной шкале
    int enduranceMod; // модификатор выносливости
    int attentivenessMod; // модификатор выносливости
    int reactionMod; // модификатор реакции
    int strengthMod; // модификатор силы
    int inventivenessMod; // модификатор находчивости
    int luckMod; // модификатор удачи
    int fameMod; // модификатор известности
    int mentalHealthMod; // модификатор ментального здоровья
    String[] bag = new String[4]; // Массив сумки на 4 места
    String firstBagPlace; // Первое место в сумке
    String secondBagPlace; // Первое место в сумке
    String thirdBagPlace; // Первое место в сумке
    String fourthBagPlace; // Первое место в сумке
    List<String> storageList = new ArrayList<>();
    String storage;
    String quest;
    String key;

    // ГЕТТЕРЫ И СЕТТЕРЫ

    public String getSubject() {return subject;}
    public void setSubject(String subject) {this.subject = subject;}

    public String getUserLogin() {return userLogin;}
    public void setUserLogin(String userLogin) {this.userLogin = userLogin;}

    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public String getHead() {return head;}
    public void setHead(String head) {this.head = head;}

    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

    public String getLeftHand() {return leftHand;}
    public void setLeftHand(String leftHand) {this.leftHand = leftHand;}

    public String getRightHand() {return rightHand;}
    public void setRightHand(String rightHand) {this.rightHand = rightHand;}

    public String getLeftLeg() {return leftLeg;}
    public void setLeftLeg(String leftLeg) {this.leftLeg = leftLeg;}

    public String getRightLeg() {return rightLeg;}
    public void setRightLeg(String rightLeg) {this.rightLeg = rightLeg;}

    public int getEndurance() {return endurance;}
    public void setEndurance(int endurance) {this.endurance = endurance;}

    public int getAttentiveness() {return attentiveness;}
    public void setAttentiveness(int attentiveness) {this.attentiveness = attentiveness;}

    public int getReaction() {return reaction;}
    public void setReaction(int reaction) {this.reaction = reaction;}

    public int getStrength() {return strength;}
    public void setStrength(int strength) {this.strength = strength;}

    public int getInventiveness() {return inventiveness;}
    public void setInventiveness(int inventiveness) {this.inventiveness = inventiveness;}

    public int getLuck() {return luck;}
    public void setLuck(int luck) {this.luck = luck;}

    public int getFame() {return fame;}
    public void setFame(int fame) {this.fame = fame;}

    public int getMentalHealth() {return mentalHealth;}
    public void setMentalHealth(int mentalHealth) {this.mentalHealth = mentalHealth;}

    public int getEnduranceMod() {return enduranceMod;}
    public void setEnduranceMod(int enduranceMod) {this.enduranceMod = enduranceMod;}

    public int getAttentivenessMod() {return attentivenessMod;}
    public void setAttentivenessMod(int attentivenessMod) {this.attentivenessMod = attentivenessMod;}

    public int getReactionMod() {return reactionMod;}
    public void setReactionMod(int reactionMod) {this.reactionMod = reactionMod;}

    public int getStrengthMod() {return strengthMod;}
    public void setStrengthMod(int strengthMod) {this.strengthMod = strengthMod;}

    public int getInventivenessMod() {return inventivenessMod;}
    public void setInventivenessMod(int inventivenessMod) {this.inventivenessMod = inventivenessMod;}

    public int getLuckMod() {return luckMod;}
    public void setLuckMod(int luckMod) {this.luckMod = luckMod;}

    public int getFameMod() {return fameMod;}
    public void setFameMod(int fameMod) {this.fameMod = fameMod;}

    public int getMentalHealthMod() {return mentalHealthMod;}
    public void setMentalHealthMod(int mentalHealthMod) {this.mentalHealthMod = mentalHealthMod;}

    public String[] getBag() {return bag;}
    public void setBag(String[] bag) {this.bag = bag;}

    public String getFirstBagPlace() {return firstBagPlace;}
    public void setFirstBagPlace(String firstBagPlace) {this.firstBagPlace = firstBagPlace;}

    public String getSecondBagPlace() {return secondBagPlace;}
    public void setSecondBagPlace(String secondBagPlace) {this.secondBagPlace = secondBagPlace;}

    public String getThirdBagPlace() {return thirdBagPlace;}
    public void setThirdBagPlace(String thirdBagPlace) {this.thirdBagPlace = thirdBagPlace;}

    public String getFourthBagPlace() {return fourthBagPlace;}
    public void setFourthBagPlace(String fourthBagPlace) {this.fourthBagPlace = fourthBagPlace;}

    public List<String> getStorageList() {return storageList;}
    public void setStorageList(List<String> storageList) {this.storageList = storageList;}

    public String getStorage() {return storage;}
    public void setStorage(String storage) {this.storage = storage;}

    public String getQuest() {return quest;}
    public void setQuest(String quest) {this.quest = quest;}

    public static void createCharacter(String userLogin) throws IOException {

        if (Checks.isFileExist("Персонажи", userLogin, "Персонаж.txt")){
            System.out.println("Персонаж с логином " + userLogin + " уже существует.");
            return;
        }
        createCharacterFile(userLogin);
        fillNewCharacterFile(userLogin);
        System.out.println("Персонаж с логином " + userLogin + " создан");


    }

    private static void createCharacterFile(String userLogin) throws IOException { // создаёт и заполняет текстовый файл для хранения джейсона персонажа

            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));
    }

    private static void fillNewCharacterFile(String userLogin) throws IOException { //заполняем текстовый файл джейсоном персонажа ПОД ПЕРЕНОС В ФАЙЛМАНАГЕР
    // Todo Объединить в один метод по заполнению файла персонажа
        Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"),
                "{\"userLogin\":\""+ userLogin +"\"," +
                        "\"level\":1," +
                        "\"leftHand\":\"Плоть\"," +
                        "\"rightHand\":\"Плоть\"," +
                        "\"leftLeg\":\"Плоть\"," +
                        "\"rightLeg\":\"Плоть\"," +
                        "\"body\":\"Плоть\", " +
                        "\"head\":\"Плоть\"," +
                        "\"endurance\":5," +
                        "\"attentiveness\":5," +
                        "\"reaction\":5," +
                        "\"strength\":5," +
                        "\"inventiveness\":5," +
                        "\"luck\":5," +
                        "\"fame\":0," +
                        "\"mentalHealth\":95," +
                        "\"enduranceMod\":0," +
                        "\"attentivenessMod\":0," +
                        "\"reactionMod\":0," +
                        "\"strengthMod\":0," +
                        "\"inventivenessMod\":0," +
                        "\"luckMod\":0," +
                        "\"fameMod\":0," +
                        "\"mentalHealthMod\":0," +
                        "\"firstBagPlace\":\"Пусто\"," +
                        "\"secondBagPlace\":\"Пусто\"," +
                        "\"thirdBagPlace\":\"Пусто\"," +
                        "\"fourthBagPlace\":\"Пусто\"," +
                        "\"storage\":\"Пусто\"," +
                        "\"quest\":null}");
    }

    public static CharacterHelper chooseCharacter(String userLogin, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        System.out.println(userLogin);
        if (!Checks.isFileExist("Персонажи", userLogin, "Персонаж.txt")){
            System.out.println("При выборе персонажа файл персонажа " + userLogin + " не найден");
            return null;
        }
        System.out.println("При выборе персонажа файл персонажа " + userLogin + " найден");
        System.out.println("Передаём на десериализацию:" + Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt")));
        character = FileManager.parseCharacterStringJsonToPojo(Files.readString(Paths.get(
                "F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt")), objectMapper, character);
        System.out.println("Выбран персонаж " + character.userLogin + ". Его квест:  " + character.quest);

        return character;
    }

    public static CharacterHelper updateCharacterPojo(CharacterHelper character, CharacterHelper charactersChanges) throws IOException {
        //Todo ПРОПИСАТЬ ИЗМЕНЕНИЕ ВСЕХ ПОЛЕЙ ПЕСОНАЖА. ПОКА МЕНЯЕМ ТОЛЬКО КВЕСТ
        character.setQuest(charactersChanges.getQuest());
        System.out.println(character.getQuest());
        return character;
    }

    public void isStringValueMatched (String userLogin, ObjectMapper objectMapper, String key, String expectedResult) throws IOException {
        String stringToJson = Files.readString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));

        this.key = key;
        //parsedCharacterJson.getKey(); // как поставить на вход необходимый ключ для получения значения?
        // Создать расширение класса ObjectMapper или посмотреть методы JSONtoPOJO()
    }

}

