import dataBaseOperations.DatabaseConnector;
import operations.CommandHelper;
import operations.Starter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseConnector connector = new DatabaseConnector();
        connector.executeQuery();
        Starter starter = new Starter();
        starter.constructActionsQueue(starter.getActionsQueueFile(), CommandHelper.commandShaperFromArgsToString(args));
        starter.updateGameData();
    }

}
