import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHelper {
    String command; // команда полученная через аргументы

    List<String> storage = new ArrayList<>();

    public String commandShaper(String[] args){
        command="";
        // Проверяем, что есть хотя бы один аргумент и формируем общую команду
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                command += args[0] + " ";
            }
            return command.substring(0, command.length() - 1);
        }
        return "Передана пустая команда (не найдены агрументы)";
    }

    public static String getLineOfChangesFromFile(File actionsQueueFile) throws IOException {

        String lineOfChanges = null;

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        if ((reader.readLine()) != null) {
            lineOfChanges = reader.readLine();
            System.out.println("Подтянута строка изменения персонажа из файла ActionsQueue: " + lineOfChanges);
            return lineOfChanges;
        }
        else {
            return "Полученная строка изменений пуста";
        }
    }

    public static void switchSubjectType(String lineOfChanges, ObjectMapper objectMapper, Object subject){ // возвращает изменяемый субъект: персонаж/противник/квест.
        // РЕАЛИЗОВАТЬ СВИТЧЕР ТИПА ИЗМЕНЯЕМОГО СУБЪЕКТА. СОЗДАТЬ ПЕРЕОПРЕДЕЛЯЕМЫЙ МЕТОД С РАЗНЫМИ ВХОДНЫМИ ОБЪЕКТАМИ
        switch (lineOfChanges.split("\" ")[1]) { // тип изменяемого субъекта: персонаж/противник/квест
            case "character":
                CharacterHelper.changeCharacter(lineOfChanges.split("\" ")[4],objectMapper,subject);
                ActionExecutor.executeActions(lineOfChanges, objectMapper, subject);
                break;
        }
    }

    public void commandsSwitcher(String userLogin,String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist("Персонажи", userLogin,"Персонаж") == false){
                    CharacterHelper.createCharacterFile(userLogin);
                    CharacterHelper.fillNewCharacterFile(userLogin);
                }


        }
    }
}
