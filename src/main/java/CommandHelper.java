import java.io.IOException;

public class CommandHelper {
    String command;

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

    public void commandsSwitcher(String userLogin,String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist(userLogin,"Персонажи") == false){
                    CharacterHelper.createCharacterFile(userLogin);
                    CharacterHelper.fillCharacterFile(userLogin);
                }


        }
    }
}
