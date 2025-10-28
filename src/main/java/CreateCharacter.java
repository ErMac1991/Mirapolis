import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateCharacter {


    public void createCharFile(String userLogin){
        try {
            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\" + userLogin + ".txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCharObject(){
        Char newChar = new Char();
    }
}

/*
Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\" + userLogin + ".txt"),
        "{\"userData\":{" +
        "\"nickname\":\""+ userLogin +"\"," +
        "\"level\":1," +
        "\"body\"{:" +
        "\"lefthand\":\"Плоть\"," +
        "\"righthand\":\"Плоть\"," +
        "\"leftleg\":\"Плоть\"," +
        "\"rightleg\": \"Плоть\"," +
        "\"body\": \"Плоть\"}," +
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

 */
