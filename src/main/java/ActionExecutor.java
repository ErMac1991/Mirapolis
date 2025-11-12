import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class ActionExecutor {

    int lineNumber = 1; // Номер строки для чтения (1-based)
    static String firstLine;
    int intValue;
    List<String> actionArray;
    String[] changeModule = new String[2];

    public static boolean isValueInt(String stringValue) { // метод под удаление, реализовать через json
        try {
            Integer.parseInt(stringValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static void executeActions(File actionsQueueFile, ObjectMapper objectMapper, CharacterHelper character) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        while ((reader.readLine()) != null){
            firstLine = reader.readLine();
            System.out.println("Подтянута строка изменения персонажа из файла ActionsQueue: " + firstLine);
            CharacterHelper.updateCharacter(ParseJson.parseCharacterStringJsonToPojo(firstLine,objectMapper, character).getUserLogin(),
                    firstLine,
                    objectMapper);

        }



    }

}
