import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CreateCharacter {

    String charJson;
    String login; // логин игрока
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


    public void createCharFile(String userLogin){ // создаёт текстовый файл для хранения джейсона персонажа
        try {
            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\" + userLogin + ".txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCharObject() throws IOException { //заполняем текстовый файл джейсоном персонажа

        Character newCharacter = new Character();
        charJson = "{\"userData\":{" +
                "\"nickname\":\""+ newCharacter.login +"\"," +
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
                "\"quest\":null}";

        Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + newCharacter.login + "\\" +
                        newCharacter.login + ".txt"), charJson);
    }
}

