import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {


    public static boolean isFileExist(String category, String subCategory, String  fileName){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + subCategory + "\\" + fileName + ".txt"));
    }

    public static void isSystemUpdated(ObjectMapper objectMapper, CharacterHelper character) throws IOException {

        final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");

        while (true) {
            if (actionsQueueFile.exists()) {
                System.out.println("Файл найден!");
                ActionExecutor.executeActions(actionsQueueFile, objectMapper, character); // исключить вложенный метод
                //break; // Выходим из цикла, если файл найден
            } else {
                System.out.println("Файл не найден, ждем 5 секунд...");
                }

            try {
                Thread.sleep(2000); // Ждем 2000 миллисекунд (2 секундs)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем прерванный статус потока
                System.err.println("Процесс проверки прерван: " + e.getMessage());
                break;
            }
        }
    }



}
