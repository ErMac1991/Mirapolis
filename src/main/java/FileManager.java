import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileManager {

    static int questCounter;
    static final File questCounterFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\QuestsCounter.txt");
    static String textToReplace;

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

        if (reader.read() != -1) { // Если первая строка пуста (?)

            writer.write("\n");
        }

        writer.write(command);
        writer.close();
        reader.close();
        System.out.println("Заполнен файл с очередью действий " + actionsQueueFile.getName() + " командой: " + command);


    }


    public static CharacterHelper parseCharacterStringJsonToPojo(String stringToJson, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        character = objectMapper.readValue(stringToJson.getBytes(), CharacterHelper.class);
        return character;
    }

    public static void eraseLineFromFile(File file, int lineNumber, boolean deleteEmptyFile) throws IOException { // удалить строку из файла

        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        textToReplace = "";
        File tempFile = File.createTempFile("TempFile", ".txt", new File("F:\\Проекты\\Стримы\\Mirapolis\\"));

        System.out.println("Всё, что считано с файла: " + lines);
        System.out.println("Длина массива равна: " + lines.size());

        // Заполняем временный файл строками из исходного, за исключением удаляемой строки
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        //todo переписать реализацию удаления 1й строки в файле
        for (int i = 0; i < lines.size(); i++) {
            if (i != lineNumber - 1) {
                textToReplace += lines.get(i);
                if (i != lines.size() - 1) {
                    textToReplace += "\n";
                }
            } else {
                System.out.println("Строка " + lineNumber + " найдена и не включена в строковую переменную для записи во временный файл");
            }
        }
            writer.write(textToReplace);

            System.out.println("Временный файл заполнен значением: " + textToReplace );

            writer.close();
            file.delete(); // удалить исходный файл
            tempFile.renameTo(file); // переименовываем временный файл в исходное имя файла
            System.out.println("Путь актуального файла: " + file.getPath() + " а название: " + file.getName());

            if (deleteEmptyFile == false) {
                System.out.println("Файл не нужно удалять, даже если он пустой");
                return;
            }

            System.out.println("Если файл " + file.getName() + " пустой - удалить его");

            lines = Files.readAllLines(Path.of(file.getPath()));

            System.out.println(lines);

            if (lines.isEmpty()) {
                file.delete();
                System.out.println("Пустой файл " + file.getName() + " удалён");
                return;
            }

            System.out.println("Файл " + file.getName() + " не пустой и содержит: " + lines);


    }

    public static void createCharacterFile(String userLogin) throws IOException { // создаёт и заполняет текстовый файл для хранения джейсона персонажа

        try {
            Files.createDirectory(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\"));
            Files.createFile(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + userLogin + "\\Персонаж.txt"));
        } catch (FileAlreadyExistsException e) {
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
    public static void fillPojoToJsonFile(QuestConstructor quest) throws IOException { //МЕТОД ЗАПИСЫВАЮЩИЙ POJO В ФАЙЛ свободного квеста

        Files.writeString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Квесты\\Пул\\" + quest.getQuestID() + ".txt"),
                "{\"level\":" + quest.getQuestLevel() + "," +
                        "\"questName\":\"" + quest.getQuestName() +  "\"," +
                        "\"questTask\":\"" + quest.getQuestTask() +  "\"," +
                        "\"isQuestOpenSpace\":\"" + quest.isQuestOpenSpace() +  "\"," +
                        "\"isQuestMultiPlayer\":\"" + quest.isQuestMultiPlayer() +  "\"," +
                        "\"questDifficulty\":\"" + quest.getQuestDifficulty() +  "\"," +
                        "\"difficultyRatio\":" + quest.getDifficultyRatio() +  "," +
                        "\"stagesInQuest\":" + quest.getStagesInQuest() +  "," +
                        "\"questCreationDateTime\":\"" + quest.getQuestGenerationDateTime() +  "\"," +
                        "\"deposit\":" + quest.getDeposit() +  "," +
                        "\"royalty\":" + quest.getRoyalty() + "}");
    }

    public static int getQuestID() throws IOException { // Возвращает свободный ID квеста и обновляет счётчик квестов

        if (!Checks.isFileExist("Квесты", "Пул", questCounterFile.getName())) {
            System.out.println("Не найдет файл счётчика квестов!");
            return -1;
        }

        questCounter = Integer.parseInt(Files.readString(Path.of(questCounterFile.getPath())));
        System.out.println("Идентификатор текущего квеста: " + questCounter);
        //eraseLineFromFile(questCounterFile, 1, false);
        Files.writeString(Path.of(questCounterFile.getPath()), String.valueOf(questCounter + 1));

        return questCounter;
    }




}
