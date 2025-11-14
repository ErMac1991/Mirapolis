import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterHelper character = new CharacterHelper();
        CharacterHelper charactersChanges = new CharacterHelper();
        final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");

        String updateData; // Строка изменений

        if(Checks.isSystemUpdated(actionsQueueFile)){
            updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
            // ЗДЕСЬ ДОЛЖЕН ЗАПУСКАТЬСЯ МЕТОД, ОПРЕДЕЛЯЮЩИЙ ТИП ИЗМЕНЯЕМОГО СУБЪЕКТА (пока по умолчанию меняем персонажа)
            // МЕТОД БУДЕТ ЗАПУСКАТЬ ActionExecutor.executeAction И ДАВАТЬ НА ВХОД СООТВЕТСТВУЮЩИЕ ДАННЫЕ
            charactersChanges = ParseJson.parseCharacterStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
            CharacterHelper.changeCharacter(charactersChanges.userLogin,objectMapper,character);// Переключиться на изменяемого персонажа
            CharacterHelper.updateCharacter(character, objectMapper, charactersChanges);//Вносим изменения в персонажа
        };




    }
}
