import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHelper {
    String command; // команда полученная через аргументы
    String lineOfChanges;
    String changedSubject;
    List<String> storage = new ArrayList<>();

    public String commandShaper(String[] args){
        command="";
        // Проверяем, что есть хотя бы один аргумент и формируем общую команду
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                command += args[0] + " ";
            }
        }
        return command.substring(0, command.length() - 1);
    }

    public void changedSubject(File actionsQueueFile, ObjectMapper objectMapper) throws IOException { // определяет изменяемый субъект

        lineOfChanges = null;
        changedSubject = null;

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        while ((reader.readLine()) != null) {
            lineOfChanges = reader.readLine();
            System.out.println("Подтянута строка изменения персонажа из файла ActionsQueue: " + lineOfChanges);
        }

        changedSubject = lineOfChanges.split("\" ")[1];

        switch (changedSubject) {
            case "character":

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
