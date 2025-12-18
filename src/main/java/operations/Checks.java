package operations;

import constructors.CharacterCreator;
import constructors.EnemyHumanCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Checks {


    public static boolean isFileExist(String endOfPathFile){
        //if (!isFileExist(category){}
        return Files.exists(Path.of("F:\\Проекты\\Стримы\\Mirapolis\\" + endOfPathFile));
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


}
