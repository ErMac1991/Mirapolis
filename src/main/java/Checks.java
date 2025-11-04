import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {

        public static boolean isFileExist(String fileName, String category){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + category + "\\" + fileName + "\\" + fileName + ".txt"));
    }

}
