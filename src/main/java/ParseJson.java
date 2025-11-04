import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParseJson {
    public static CharacterHelper parseCharacterJsonFromString(String stringJson, ObjectMapper objectMapper) throws IOException {
        CharacterHelper parsedCharacterJson = objectMapper.readValue(stringJson.getBytes(), CharacterHelper.class);
        return parsedCharacterJson;
    }

}
