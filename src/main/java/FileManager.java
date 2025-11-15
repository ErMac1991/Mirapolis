import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    public static CharacterHelper parseCharacterStringJsonToPojo(String stringToJson, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        character = objectMapper.readValue(stringToJson.getBytes(), CharacterHelper.class);
        return character;
    }

    public static void eraseLineFromFile(File file, int lineNumber) throws IOException { // удалить строку из файла

        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        File tempFile = File.createTempFile("TempFile",".txt", new File("F:\\Проекты\\Стримы\\Mirapolis\\"));

        // Заполняем временный файл строками из исходного, за исключением удаляемой строки
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++){
            if(i != lineNumber - 1) {
                writer.write(lines.get(i));
            }
            else {
                System.out.println("Строка "+lineNumber+" найдена и не включена во временный файл");
            }
        }
        System.out.println("Временный файл заполнен");

        writer.close();
        file.delete(); // удалить исходный файл
        tempFile.renameTo(file); // переименовываем временный файл в исходное имя файла
    }

    //ПРОПИСАТЬ МЕТОД ЗАПИСЫВАЮЩИЙ POJO В ФАЙЛ ПЕРСОНАЖА

}
