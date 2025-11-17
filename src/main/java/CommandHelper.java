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
                    command += args[i] + " ";
                }
            }
            //command.replace("} {","}\n{");
            System.out.println("Сформированная команда: " + command);
            return command;
            //return command.substring(0, command.length() - 1);
        }
        else {
            System.out.println("Передана пустая команда (не найдены агрументы)");
            return null;
        }
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


    public void commandsSwitcher(String userLogin,String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist("Персонажи", userLogin,"Персонаж") == false){
                    CharacterHelper.createCharacter(userLogin);
                }


        }
    }
}
