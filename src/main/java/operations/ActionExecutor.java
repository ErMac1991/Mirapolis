package operations;

import constructors.CharacterCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class ActionExecutor {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ActionExecutor.class);

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



    public static void executeActions(File actionsQueueFile, ObjectMapper objectMapper, CharacterCreator character) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        while ((reader.readLine()) != null){
            firstLine = reader.readLine();
            logger.info("Подтянута строка изменения персонажа из файла ActionsQueue: " + firstLine);

        }
    }


    public static void executeActions(String lineOfChanges, ObjectMapper objectMapper, CharacterCreator character) throws IOException {



    }

}
