package Checks;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HasQuest {


    public void isBusy(String charLogin) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = Files.readString(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\Персонажи\\" + charLogin + "\\Персонаж.txt"));
        //Object obj = new JSONParser().parse(json);

        System.out.println();
    }

}
