public class commandShaper {
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
}
