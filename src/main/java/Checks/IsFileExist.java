package Checks;

import java.nio.file.Files;
import java.nio.file.Path;

public class IsFileExist {

    public boolean isFileExist(String fileName){
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + fileName + "\\" + fileName + ".txt"));
    }

}
