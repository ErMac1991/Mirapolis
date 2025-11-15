import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Starter {
    ObjectMapper objectMapper = new ObjectMapper();
    CharacterHelper character = new CharacterHelper();
    CharacterHelper charactersChanges = new CharacterHelper();
    final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");
    String updateData; // Строка изменений

    public void start() throws IOException {

        if(Checks.isSystemUpdated(actionsQueueFile)){
            updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
            switch (updateData.split("\"")[1]) { // тип изменяемого субъекта: персонаж/противник/квест
                case "character":
                    charactersChanges = ParseJson.parseCharacterStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
                    if((updateData.split("\"")[4]).equals(charactersChanges.getUserLogin())){
                        System.out.println("Логин игрока из файла: " + updateData.split("\"")[3] + " совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                        CharacterHelper.chooseCharacter(charactersChanges.userLogin,objectMapper,character);// Переключение на изменяемого персонажа
                        CharacterHelper.updateCharacterPojo(character, charactersChanges);//Внесение изменений в Pojo персонажа слиянием с объектом изменений
                        // Перенос данных из Pojo персонажа в Json файл персонажа
                        //
                    }
                    else{
                        System.out.println("Логин игрока из файла: " + updateData.split("\"")[3] + " не совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                    }

                    // Метод, стирающий верхнюю строку изменений и удаляющий файл изменений в случае их отутствия

                    // Очищение переменных
                    charactersChanges = null;
                    character = null;
                    updateData = null;
                    break;

                default:
                    System.out.println("Тип изменяемого субъекта: " + updateData.split("\"")[1] + " не распознан!");
            }
        }
    }
}
