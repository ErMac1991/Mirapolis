import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class ActionExecutor {

    int lineNumber = 1; // Номер строки для чтения (1-based)
    static String firstLine;
    int intValue;
    List<String> actionArray;
    String[] changeModule = new String[2];

    public static boolean isValueInt(String stringValue) {
        try {
            Integer.parseInt(stringValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void executeActions(File actionsQueueFile, ObjectMapper objectMapper) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        while ((reader.readLine()) != null){
            firstLine = reader.readLine();
            System.out.println("Подтянута строка из файла ActionsQueue: "+firstLine);
            ParseJson.parseCharacterJsonFromString(firstLine,objectMapper);

        }



    }

}
