import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CharacterHelper {

    String userLogin; // логин игрока
    int level; // уровень игрока. Высчитывается исходя из статов
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String lefthand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String righthand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftleg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightleg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
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
    List<String> storage = new ArrayList<>();
    String quest;
    String key;

    // ГЕТТЕРЫ И СЕТТЕРЫ

    public String getUserLogin() {return userLogin;}
    public void setUserLogin(String userLogin) {this.userLogin = userLogin;}

    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public String getHead() {return head;}
    public void setHead(String head) {this.head = head;}

    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}

    public String getLefthand() {return lefthand;}
    public void setLefthand(String lefthand) {this.lefthand = lefthand;}

    public String getRighthand() {return righthand;}
    public void setRighthand(String righthand) {this.righthand = righthand;}

    public String getLeftleg() {return leftleg;}
    public void setLeftleg(String leftleg) {this.leftleg = leftleg;}

    public String getRightleg() {return rightleg;}
    public void setRightleg(String rightleg) {this.rightleg = rightleg;}

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

    public List<String> getStorage() {return storage;}
    public void setStorage(List<String> storage) {this.storage = storage;}

    public String getQuest() {return quest;}
    public void setQuest(String quest) {this.quest = quest;}



    public static void createCharacterFile(String userLogin) throws IOException { // создаёт текстовый файл для хранения джейсона персонажа

            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));
    }

    public static void fillNewCharacterFile(String userLogin) throws IOException { //заполняем текстовый файл джейсоном персонажа

        Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\" +
                        userLogin + ".txt"),
                "{\"userData\":{" +
                        "\"nickname\":\""+ userLogin +"\"," +
                        "\"level\":1," +
                        "\"body\"{:" +
                        "\"lefthand\":\"Плоть\"," +
                        "\"righthand\":\"Плоть\"," +
                        "\"leftleg\":\"Плоть\"," +
                        "\"rightleg\": \"Плоть\"," +
                        "\"body\": \"Плоть\", " +
                        "\"head\": \"Плоть\"}," +
                        "\"stats\":{" +
                        "\"endurance\":5," +
                        "\"attentiveness\":5," +
                        "\"reaction\":5," +
                        "\"strength\":5," +
                        "\"inventiveness\":5," +
                        "\"luck\":5," +
                        "\"fame\":0," +
                        "\"mentalhealth\":95}," +
                        "\"mods\":{" +
                        "\"endurance\":0," +
                        "\"attentiveness\":0," +
                        "\"reaction\":0," +
                        "\"strength\":0," +
                        "\"inventiveness\":0," +
                        "\"luck\":0," +
                        "\"fame\":0," +
                        "\"mentalhealth\":0}}," +
                        "\"inventory\":{" +
                        "\"bag\":" +
                        "{\"place1\":\"Пусто\"," +
                        "\"place2\":\"Пусто\"," +
                        "\"place3\":\"Пусто\"," +
                        "\"place4\":\"Пусто\"}," +
                        "\"storage\":[]}," +
                        "\"quest\":null}");
    }

    public static void updateCharacter(String userLogin, String updateData, ObjectMapper objectMapper) throws IOException {

        String charJson = String.valueOf(Files.readAllLines(Paths.get("file.txt"))); // Переделать с учётом входных данных
        ParseJson.parseCharacterJsonFromString(charJson, objectMapper);
        //charJson.readValue();
    }

    public void isStringValueMatched (String userLogin, ObjectMapper objectMapper, String key, String expectedResult) throws IOException {
        String stringToJson = Files.readString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));
        CharacterHelper parsedCharacterJson = ParseJson.parseCharacterJsonFromString(stringToJson, objectMapper);
        this.key = key;
        //parsedCharacterJson.getKey(); // как поставить на вход необходимый ключ для получения значения?
        // Создать расширение класса ObjectMapper или посмотреть методы JSONtoPOJO()
    }

}

