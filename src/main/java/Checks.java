import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {


    public static boolean isFileExist(String category, String subCategory, String  fileName){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + subCategory + "\\" + fileName + ".txt"));
    }

    public static boolean isFileExist(String category, String  fileName){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + fileName + ".txt"));
    }

    public static boolean isFileExist(String  fileName){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + fileName + ".txt"));
    }

    public static boolean isSystemUpdated(File actionsQueueFile) throws IOException {

        while (true) {
            if (actionsQueueFile.exists()) {
                System.out.println("Файл обновлений найден!");

                return true;

            } else {
                System.out.println("Файл не найден, ждем 2 секунды...");
                }

            try {
                Thread.sleep(2000); // Ждем 2000 миллисекунд (2 секундs)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем прерванный статус потока
                System.err.println("Процесс проверки прерван: " + e.getMessage());
                break;
            }
        }
        return false;
    }


}
