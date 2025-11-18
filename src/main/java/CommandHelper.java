import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHelper {


    List<String> storage = new ArrayList<>();

    public static String commandShaperFromArgsToString(String[] args){
        String command = ""; // команда полученная через аргументы
        // Проверяем, что есть хотя бы один аргумент и формируем общую команду
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("Армумент " + i + " = " + args[i]);

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
        else {
            System.out.println("Передана пустая команда (не найдены агрументы)");
            return null;
        }
    }

    public static String getLineOfChangesFromFile(File actionsQueueFile) throws IOException {

        String lineOfChanges = null;

        BufferedReader reader = new BufferedReader(new FileReader(actionsQueueFile.getPath()));

        /*if (reader.read() == -1) {
            reader.close();
            return "Полученная строка изменений пуста";
        }*/
        try{
            lineOfChanges = reader.readLine();
        }
        catch (Exception e){
            System.out.println("В файле " + actionsQueueFile.getName() + " отсутствует 1я строка из файла: ");
            return e.getMessage();
        };

            System.out.println("Считываем 1ю строку из файла: " + lineOfChanges);
            System.out.println("Подтянута строка изменения персонажа из файла ActionsQueue: " + lineOfChanges);
            reader.close();
            return lineOfChanges;


    }


    public void commandsSwitcher(String userLogin,String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist("Персонажи", userLogin,"Персонаж.txt") == false){
                    CharacterHelper.createCharacter(userLogin);
                }


        }
    }
}
