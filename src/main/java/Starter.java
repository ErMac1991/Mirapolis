import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Starter {
    ObjectMapper objectMapper = new ObjectMapper();
    CharacterHelper character = new CharacterHelper();
    CharacterHelper charactersChanges = new CharacterHelper();
    QuestConstructor quest = new QuestConstructor();
    final File actionsQueueFile = new File("F:\\Проекты\\Стримы\\Mirapolis\\ActionsQueue.txt");
    String updateData; // Строка изменений
    String typeOfSubjectFromArgs;
    String userLoginFromArgs;

    public void updateGameData(String[] args) throws IOException {

        if (args == null) {
            System.out.println("На вход стартера не получены аргументы");
            return;
        }

        if (!Checks.isFileExist(actionsQueueFile.getName())) {
            System.out.println("Файл с очередью действий не найден");
            FileManager.createActionsQueueFile(actionsQueueFile);
            FileManager.fillActionsQueueFile(actionsQueueFile, CommandHelper.commandShaperFromArgsToString(args));
            System.out.println("Файл с очередью действий " + actionsQueueFile.getName() + " создан и заполнен");
        }

        if (!Checks.isSystemUpdated(actionsQueueFile)) {
            System.out.println("Обновления игровых файлов не найдены");
        }

        updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
        typeOfSubjectFromArgs = updateData.split("\"")[3];

        switch (typeOfSubjectFromArgs) { // тип изменяемого субъекта: персонаж/противник/квест

            case "newCharacter":
                System.out.println("Тип субъекта - Новый персонаж");
                updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
                userLoginFromArgs = updateData.split("\"")[7];
                System.out.println("updateData = " + updateData);
                CharacterHelper.createCharacter(userLoginFromArgs);
                FileManager.eraseLineFromFile(actionsQueueFile, 1,true);// метод удаления верхней строки из файла очереди действий
                System.out.println("Из очереди удалено действие создания нового персонажа");
                Checks.isSystemUpdated(actionsQueueFile);


            case "character":
                System.out.println("Тип субъекта - Существующий персонаж");
                updateData = CommandHelper.getLineOfChangesFromFile(actionsQueueFile);
                System.out.println("updateData = " + updateData);
                userLoginFromArgs = updateData.split("\"")[7];
                System.out.println("userNameFromArgs = " + userLoginFromArgs);
                character = CharacterHelper.chooseCharacter(userLoginFromArgs, objectMapper, character);// Переключение на изменяемого персонажа

                charactersChanges = character;
                charactersChanges = FileManager.parseCharacterStringJsonToPojo(updateData, objectMapper, charactersChanges); // объект изменений
                if (!userLoginFromArgs.equals(charactersChanges.getUserLogin())) {
                    System.out.println("Логин игрока из файла: " + userLoginFromArgs + " не совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                    return;
                }
                System.out.println("Логин игрока из файла: " + userLoginFromArgs + " совпадает с логином из Pojo: " + charactersChanges.getUserLogin());
                System.out.println("Квест персонажа " + character.getUserLogin() + " до изменений: " + character.getQuest());
                System.out.println("Квест в изменениях: " + charactersChanges.getQuest());
                character = CharacterHelper.updateCharacterPojo(character, charactersChanges);//Внесение изменений в Pojo персонажа слиянием с объектом изменений
                System.out.println("Квест персонажа " + character.getUserLogin() + " после изменений: " + character.getQuest());
                FileManager.fillPojoToJsonFile(character);// Перенос данных из Pojo персонажа в Json файл персонажа
                FileManager.eraseLineFromFile(actionsQueueFile, 1, true);// Метод, стирающий верхнюю строку изменений и удаляющий файл изменений в случае их отутствия
                System.out.println("Удалено действие создания нового персонажа");

                // Очищение переменных
                charactersChanges = null;
                character = null;
                updateData = null;
                Checks.isSystemUpdated(actionsQueueFile);

            default:
                try {System.out.println("Тип изменяемого субъекта: " + updateData.split("\"")[1] + " не распознан!");}
                catch (NullPointerException e){
                    System.out.println("Произошла попытка получить часть несуществующей команды: " + e.getMessage());
                }

        }


    }

}
