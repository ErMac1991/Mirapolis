import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {

    public void isBusy(String charLogin) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = Files.readString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + charLogin + "\\Персонаж.txt"));

    }
    public static boolean isFileExist(String fileName, String category){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + fileName + "\\" + fileName + ".txt"));
    }

}
