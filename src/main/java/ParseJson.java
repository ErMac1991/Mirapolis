import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParseJson {
    public void parseCharacterJsonFromString(String stringJson, ObjectMapper objectMapper) throws IOException {
        CreateCharacter parsedCharacterJson = objectMapper.readValue(stringJson.getBytes(), CreateCharacter.class);
    }
}
