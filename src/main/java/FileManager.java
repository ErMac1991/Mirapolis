import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    static int questCounter;
    static final File questCounterFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\QuestsCounter.txt");

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

    public static void eraseLineFromFile(File file, int lineNumber, boolean deleteEmptyFile) throws IOException { // удалить строку из файла

        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        File tempFile = File.createTempFile("TempFile", ".txt", new File("F:\\Проекты\\Стримы\\Mirapolis\\"));

        // Заполняем временный файл строками из исходного, за исключением удаляемой строки
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

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
        System.out.println("Путь актуального файла: " + file.getPath() + " а название: " + file.getName());

        if (deleteEmptyFile == false){
            System.out.println("Файл не нужно удалять, даже если он пустой");
            return;
        }

        System.out.println("Удалить пустой файл " + file.getName());

        lines = Files.readAllLines(Path.of(file.getPath()));

        System.out.println(lines);

        if (lines.isEmpty()){
            file.delete();
            System.out.println("Пустой файл " + file.getName() + " удалён");
        }

    }

    public static void createCharacterFile(String userLogin) throws IOException { // создаёт и заполняет текстовый файл для хранения джейсона персонажа

        try {
            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));
        }
        catch (FileAlreadyExistsException e) {
            System.out.println("Создаваемый файл или папка уже существуют");
            e.printStackTrace();
        }


    }

    public static void fillPojoToJsonFile(CharacterHelper character) throws IOException { //МЕТОД ЗАПИСЫВАЮЩИЙ POJO В ФАЙЛ ПЕРСОНАЖА
        //todo перегрузить метод под входящий объект quest
        Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + character.getUserLogin() + "\\Персонаж.txt"),
                "{\"userLogin\":\"" + character.getUserLogin() + "\"," +
                        "\"level\":" + character.getLevel() + "," +
                        "\"leftHand\":\"" + character.getLeftHand() + "\"," +
                        "\"rightHand\":\"" + character.getRightHand() + "\"," +
                        "\"leftLeg\":\"" + character.getLeftLeg() + "\"," +
                        "\"rightLeg\": \"" + character.getRightLeg() + "\"," +
                        "\"body\": \"" + character.getLeftHand() + "\", " +
                        "\"head\": \"" + character.getHead() + "\"," +
                        "\"endurance\":" + character.getEndurance() + "," +
                        "\"attentiveness\":" + character.getAttentiveness() + "," +
                        "\"reaction\":" + character.getReaction() + "," +
                        "\"strength\":" + character.getStrength() + "," +
                        "\"inventiveness\":" + character.getInventiveness() + "," +
                        "\"luck\":" + character.getLuck() + "," +
                        "\"fame\":" + character.getFame() + "," +
                        "\"mentalHealth\":" + character.getMentalHealth() + "," +
                        "\"enduranceMod\":" + character.getEnduranceMod() + "," +
                        "\"attentivenessMod\":" + character.getAttentivenessMod() + "," +
                        "\"reactionMod\":" + character.getReactionMod() + "," +
                        "\"strengthMod\":" + character.getStrengthMod() + "," +
                        "\"inventivenessMod\":" + character.getInventivenessMod() + "," +
                        "\"luckMod\":" + character.getLuckMod() + "," +
                        "\"fameMod\":" + character.getFameMod() + "," +
                        "\"mentalHealthMod\":" + character.getMentalHealthMod() + "," +
                        "\"firstBagPlace\":\"" + character.getFirstBagPlace() + "\"," +
                        "\"secondBagPlace\":\"" + character.getSecondBagPlace() + "\"," +
                        "\"thirdBagPlace\":\"" + character.getThirdBagPlace() + "\"," +
                        "\"fourthBagPlace\":\"" + character.getFourthBagPlace() + "\"," +
                        "\"storage\":\"" + character.getStorage() + "\"," + // Проверить как записывается
                        "\"quest\":\"" + character.getQuest() + "\"}");


    }

    public static int getQuestID() throws IOException { // Возвращает свободный ID квеста и обновляет счётчик квестов

        if (!Checks.isFileExist("Квесты", "Пул", questCounterFile.getName())){
            System.out.println("Не найдет файл счётчика квестов!");
            return -1;
        }

        questCounter = Integer.parseInt(Files.readString(Path.of(questCounterFile.getPath())));
        //eraseLineFromFile(questCounterFile, 1, false);
        Files.writeString(Path.of(questCounterFile.getPath()), String.valueOf(questCounter + 1));

        return questCounter;

    }


}
