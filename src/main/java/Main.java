import constructors.CharacterCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import operations.CommandHelper;
import operations.Starter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterCreator character = new CharacterCreator();
        CharacterCreator charactersChanges = new CharacterCreator();
        final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");

        String updateData; // Строка изменений

        //Процессы создания персонажа и взятия им квеста через консоль
        Starter starter = new Starter();
        starter.constructActionsQueue(starter.getActionsQueueFile(), CommandHelper.commandShaperFromArgsToString(args));
        starter.updateGameData();
    }

}
