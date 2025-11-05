import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {
    final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");

    public static boolean isFileExist(String fileName, String category){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + fileName + "\\" + fileName + ".txt"));
    }

    public void isSystemUpdated(){

        while (true) {
            if (actionsQueueFile.exists()) {
                System.out.println("Файл найден!");
                //break; // Выходим из цикла, если файл найден
            } else {
                System.out.println("Файл не найден, ждем 5 секунд...");
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



}
