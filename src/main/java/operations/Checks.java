package operations;

import constructors.CharacterCreator;
import constructors.EnemyHumanCreator;
import enums.Items;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Checks {


    public static boolean isFileExist(String endOfPathFile){
        boolean isFileExist;
        Path filePath = Paths.get("F:/Проекты/Стримы/Mirapolis/" + endOfPathFile);
        isFileExist = Files.exists(filePath);
        return isFileExist;
    }

    public static boolean isSystemUpdated(File actionsQueueFile) throws IOException {

        while (true) {
            if (actionsQueueFile.exists()) {
                System.out.println("Файл обновлений найден!");

                return true;

            } else {
                System.out.println("Файл не найден, ждем 2 секунды...");
                }

            try {
                Thread.sleep(2000); // Ждем 2000 миллисекунд (2 секундs)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем прерванный статус потока
                System.err.println("Процесс проверки прерван: " + e.getMessage());
                break;
            }
        }
        return false;
    }

    public static boolean isRightPercent(int percent){
        if (percent >= 0 && percent <=100){return true;}
        System.out.println("Ошибка во введённом значении процента: " + percent);
        return false;
    }

    public static boolean isEmptyPlace(EnemyHumanCreator enemy){

        if(enemy.getFirstBagPlace().equals("Пусто") ||
                enemy.getSecondBagPlace().equals("Пусто") ||
                enemy.getThirdBagPlace().equals("Пусто") ||
                enemy.getFourthBagPlace().equals("Пусто")){
            return true;
        }
        return false;
    }

    public static boolean isEmptyPlace(CharacterCreator character) {
        boolean isEmptyPlace = false;
        if (character.getFirstBagPlace().equals("Пусто") ||
                character.getSecondBagPlace().equals("Пусто") ||
                character.getThirdBagPlace().equals("Пусто") ||
                character.getFourthBagPlace().equals("Пусто")) {
            isEmptyPlace = true;
        }
        return isEmptyPlace;
    }

    public static boolean isItemFullStack(String itemStack){
        if(!Character.isDigit(itemStack.charAt(0))){
            return false;
        }

        int firstSpaceIndex = itemStack.indexOf(' ');
        int counter = Integer.parseInt((itemStack.substring(0, firstSpaceIndex))) + 1;
        String itemName = itemStack.substring(firstSpaceIndex+1);

        if (counter == Items.valueOf(itemName).getStackLimit()){
            return true;
        }
        return false;
    }
}
