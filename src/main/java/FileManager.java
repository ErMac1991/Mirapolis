import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    public static void createActionsQueueFile(File actionsQueueFile) throws IOException {
        actionsQueueFile.createNewFile();
        System.out.println("Создан файл с очередью действий " + actionsQueueFile.getName());

    }

    public static void fillActionsQueueFile(File actionsQueueFile, String command) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(actionsQueueFile));
        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile));


        if (!Checks.isFileExist(actionsQueueFile.getName())) {
            System.out.println("При попытке заполнения, не найден файл " + actionsQueueFile.getName());
        }
        System.out.println("Код первого символа в : " + reader.read());

        if (reader.read()!=-1) {

            writer.write("\n");
        }

        writer.write(command);
        writer.close();
        reader.close();
        System.out.println("Заполнен файл с очередью действий " + actionsQueueFile.getName());


    }


    public static CharacterHelper parseCharacterStringJsonToPojo(String stringToJson, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        character = objectMapper.readValue(stringToJson.getBytes(), CharacterHelper.class);
        return character;
    }

    public static void eraseLineFromFile(File file, int lineNumber) throws IOException { // удалить строку из файла

        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        File tempFile = File.createTempFile("TempFile", ".txt", new File("G:\\Проекты\\Стримы\\Mirapolis\\"));

        // Заполняем временный файл строками из исходного, за исключением удаляемой строки
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            if (i != lineNumber - 1) {
                writer.write(lines.get(i));
            } else {
                System.out.println("Строка " + lineNumber + " найдена и не включена во временный файл");
            }
        }
        System.out.println("Временный файл заполнен");

        writer.close();
        file.delete(); // удалить исходный файл
        tempFile.renameTo(file); // переименовываем временный файл в исходное имя файла
    }

    public static void fillPojoToJsonFile(CharacterHelper character) throws IOException { //МЕТОД ЗАПИСЫВАЮЩИЙ POJO В ФАЙЛ ПЕРСОНАЖА
        Files.writeString(Path.of("G:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Персонаж.txt"),
                "{\"userData\":{" +
                        "\"nickname\":\"" + character.getUserLogin() + "\"," +
                        "\"level\":" + character.getLevel() + "," +
                        "\"body\"{:" +
                        "\"lefthand\":\"" + character.getLeftHand() + "\"," +
                        "\"righthand\":\"" + character.getRightHand() + "\"," +
                        "\"leftleg\":\"" + character.getLeftLeg() + "\"," +
                        "\"rightleg\": \"" + character.getRightLeg() + "\"," +
                        "\"body\": \"" + character.getLeftHand() + "\", " +
                        "\"head\": \"" + character.getHead() + "\"}," +
                        "\"stats\":{" +
                        "\"endurance\":" + character.getEndurance() + "," +
                        "\"attentiveness\":" + character.getAttentiveness() + "," +
                        "\"reaction\":" + character.getReaction() + "," +
                        "\"strength\":" + character.getStrength() + "," +
                        "\"inventiveness\":" + character.getInventiveness() + "," +
                        "\"luck\":" + character.getLuck() + "," +
                        "\"fame\":" + character.getFame() + "," +
                        "\"mentalhealth\":" + character.getMentalHealth() + "}," +
                        "\"mods\":{" +
                        "\"endurance\":" + character.getEnduranceMod() + "," +
                        "\"attentiveness\":" + character.getAttentivenessMod() + "," +
                        "\"reaction\":" + character.getReactionMod() + "," +
                        "\"strength\":" + character.getStrengthMod() + "," +
                        "\"inventiveness\":" + character.getInventivenessMod() + "," +
                        "\"luck\":" + character.getLuckMod() + "," +
                        "\"fame\":" + character.getFameMod() + "," +
                        "\"mentalhealth\":" + character.getMentalHealthMod() + "}}," +
                        "\"inventory\":{" +
                        "\"bag\":" +
                        "{\"place1\":\"" + character.getBag()[0] + "\"," +
                        "\"place2\":\"" + character.getBag()[1] + "\"," +
                        "\"place3\":\"" + character.getBag()[2] + "\"," +
                        "\"place4\":\"" + character.getBag()[3] + "\"}," +
                        "\"storage\":[" + character.getStorage() + "]}," + // Проверить как записывается
                        "\"quest\":" + character.getQuest() + "}");


    }


}
