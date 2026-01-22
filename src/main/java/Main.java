import constructors.CharacterCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import operations.CommandHelper;
import operations.Starter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Starter starter = new Starter();
        starter.constructActionsQueue(starter.getActionsQueueFile(), CommandHelper.commandShaperFromArgsToString(args));
        starter.updateGameData();
    }

}
