import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParseJson {


    public static CharacterHelper parseCharacterJsonFromString(String stringToJson, ObjectMapper objectMapper) throws IOException {
        CharacterHelper parsedCharacterJson = objectMapper.readValue(stringToJson.getBytes(), CharacterHelper.class);
        return parsedCharacterJson;
    }

}
