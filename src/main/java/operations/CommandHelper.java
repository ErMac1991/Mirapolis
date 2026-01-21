package operations;

import constructors.CharacterCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandHelper {


    List<String> storage = new ArrayList<>();

    public static String commandShaperFromArgsToString(String[] args){
        String command = ""; // команда полученная через аргументы
        // Проверяем, что есть хотя бы один аргумент и формируем общую команду
        if (!(args.length > 0)) {
            System.out.println("Передана пустая команда (не найдены агрументы)");
            return null;
        }

        System.out.println("Переданные агрументы: " + args);

            for (int i = 0; i < args.length; i++) {
                System.out.println("Аргумент " + i + " = " + args[i]);

                if (i == args.length - 1){
                    command += args[i];
                }
                else {
                    command += args[i] + "\n";
                }
            }
            System.out.println("Сформированная команда: " + command);
            return command;

    }

    public static String getLineOfChangesFromFile(File actionsQueueFile) throws IOException {

        String lineOfChanges = null;

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        if (reader.read() == -1) {
            reader.close();
            return "Полученная строка изменений пуста";
        }
        try{
            lineOfChanges = reader.readLine();
        }
        catch (Exception e){
            System.out.println("В файле " + actionsQueueFile.getName() + " отсутствует 1я строка из файла: ");
            return e.getMessage();
        }

            System.out.println("Считываем 1ю строку из файла " + actionsQueueFile.getName() + ": " + lineOfChanges);

            reader.close();
            return lineOfChanges;


    }


    public void commandsSwitcher(CharacterCreator character, String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist("Персонажи/" + character.getUserLogin() + "/Персонаж.txt") == false){
                    CharacterCreator.createCharacter(character.getUserLogin());
                }


        }
    }
}
