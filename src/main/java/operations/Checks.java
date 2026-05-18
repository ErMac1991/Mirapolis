package operations;

import constructors.CharacterCreator;
import constructors.EnemyHumanCreator;
import enums.Items;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Checks {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Checks.class);



    public static boolean isFileExist(String endOfPathFile){
        boolean isFileExist;
        Path filePath = Paths.get("F:/Проекты/Стримы/Mirapolis/" + endOfPathFile);
        isFileExist = Files.exists(filePath);
        return isFileExist;
    }

    public static boolean isFileEmpty(File file){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(file.getPath()));
        } catch (IOException e) {
            logger.info("Ошибка ввода-вывода при чтения из файла");
            e.printStackTrace();
        }

        if (lines.isEmpty()) {
            return true;
        }

        logger.info("Файл " + file.getName() + " не пустой и содержит: " + lines);
        return false;
    }

    public static boolean isSystemUpdated(File actionsQueueFile) throws IOException {

        while (true) {
            if (actionsQueueFile.exists()) {
                logger.info("Файл обновлений найден!");
                return true;
            }
            logger.info("Файл не найден, ждем 2 секунды...");
            try {
                Thread.sleep(2000); // Ждем 2000 миллисекунд (2 секунды)
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
        logger.info("Ошибка во введённом значении процента: " + percent);
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

    public static boolean hasDuplicates(List<String> list){

        Set<String> set = new HashSet<>();
        for (String element : list) {
            if (!set.add(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberValid(int number, int lowLimit, int highLimit){
       if(number >= lowLimit && number <= highLimit){
           return true;
       }
        return false;
    }

}
