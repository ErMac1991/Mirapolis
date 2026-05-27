package dataBaseOperations;

import constructors.CharacterCreator;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import static java.sql.Types.NULL;

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
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection(DatabaseConnector.URL, DatabaseConnector.USER, DatabaseConnector.PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Установка параметров
            pstmt.setString(1, character.getUserLogin());
            pstmt.setInt(6, character.getLevel());
            pstmt.setInt(7, character.getEndurance());
            pstmt.setInt(8, character.getAttentiveness());
            pstmt.setInt(9, character.getReaction());
            pstmt.setInt(10, character.getStrength());
            pstmt.setInt(11, character.getEnduranceMod());
            pstmt.setInt(12, character.getAttentivenessMod());
            pstmt.setInt(13, character.getReactionMod());
            pstmt.setInt(14, character.getStrengthMod());
            pstmt.setInt(15, character.getJawsBalance());
            pstmt.setInt(16, NULL);
            pstmt.setInt(17, NULL);
            pstmt.setInt(18, character.getInventiveness());
            pstmt.setInt(19, character.getLuck());
            pstmt.setInt(20, character.getMentalHealth());
            pstmt.setInt(21, character.getFameMassive());
            pstmt.setInt(22, character.getFameArmCorp());
            pstmt.setInt(23, character.getInventivenessMod());
            pstmt.setInt(24, character.getLuckMod());
            pstmt.setInt(25, character.getMentalHealthMod());
            pstmt.setInt(26, character.getFameMassiveMod());
            pstmt.setInt(27, character.getFameArmCorpMod());


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


