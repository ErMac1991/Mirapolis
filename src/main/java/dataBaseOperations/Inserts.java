package dataBaseOperations;

import constructors.CharacterCreator;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Inserts {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Inserts.class);

    DatabaseConnector connector = new DatabaseConnector();

    public void insertNewCharacter(CharacterCreator character) {
        String sql = "INSERT INTO users (user_login, " +
                "level, " +
                "endurance, " +
                "attentiveness, " +
                "reaction," +
                "strength, " +
                "endurance_mod, " +
                "attentiveness_mod, " +
                "reaction_mod, " +
                "strength_mod, " +
                "jaws_balance, " +
                "quest_simple_id, " +
                "quest_special_id, " +
                "inventiveness, " +
                "luck, " +
                "mental_health, " +
                "fame_massive, " +
                "fame_armcorp, " +
                "inventiveness_mod, " +
                "luck_mod, " +
                "mental_health_mod, " +
                "fame_massive_mod, " +
                "fame_armcorp_mod) " +
                "VALUES (" + character.getUserLogin() + ", "  +
                character.getLevel() + ", "  +
                character.getEndurance() + ", "  +
                character.getAttentiveness() + ", "  +
                character.getReaction() + ", " +
                character.getStrength() + ", "  +
                character.getEnduranceMod() + ", "  +
                character.getAttentivenessMod() + ", "  +
                character.getReactionMod() + ", "  +
                character.getStrengthMod() + ", "  +
                character.getJawsBalance() + ", "  +
                "NULL, "  +
                "NULL, "  +
                character.getInventiveness() + ", "  +
                character.getLuck() + ", "  +
                character.getMentalHealth() + ", "  +
                character.getFameMassive() + ", "  +
                character.getFameArmCorp() + ", "  +
                character.getInventivenessMod() + ", "  +
                character.getLuckMod() + ", "  +
                character.getMentalHealthMod() + ", "  +
                character.getFameMassiveMod() + ", "  +
                character.getFameArmCorpMod() + ")";

        try (
                Connection conn = DriverManager.getConnection(DatabaseConnector.URL, DatabaseConnector.USER, DatabaseConnector.PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Установка параметров
            /*pstmt.setString(1, "Иван");
            pstmt.setString(2, "ivan@example.com");
            pstmt.setInt(3, 25);*/

            // Выполнение запроса
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Строка успешно добавлена!");
            }

        } catch (
                SQLException e) {
            logger.info("Ошибка при добавлении строки: " + e.getMessage());
        }
    }
}


