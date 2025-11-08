import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterHelper character = new CharacterHelper();

        String updateData;

        Checks.isSystemUpdated(objectMapper, character);


        String incomingCommand;



    }
}
