import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParseJson {


    public static CharacterHelper parseCharacterStringJsonToPojo(String stringToJson, ObjectMapper objectMapper, CharacterHelper character) throws IOException {
        character = objectMapper.readValue(stringToJson.getBytes(), CharacterHelper.class);
        return character;
    }

    //ПРОПИСАТЬ МЕТОД ЗАПИСЫВАЮЩИЙ POJO В ФАЙЛ ПЕРСОНАЖА

}
