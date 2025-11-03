import java.io.IOException;

public class CommandsSwitcher {
    public void commandsSwitcher(String userLogin,String command) throws IOException {
        switch (command) {
            case "ТЕСТ":
                System.out.println("Команда из бота принята и обработана");
            case "Создать персонажа":
                if (Checks.isFileExist(userLogin,"Персонажи") == false){
                    CreateCharacter.createCharacterFile(userLogin);
                    CreateCharacter.fillCharacterFile(userLogin);
                }


        }
    }
}
