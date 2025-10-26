import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Char {
    String login; // логин игрока
    int level; // уровень игрока
    String lefthand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String righthand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftleg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightleg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    List<String> bodyParts = new ArrayList<>(List.of(
            lefthand, righthand,leftleg,rightleg, body, head));// массив частей тела.
    int endurance; // выносливость
    int attentiveness; // внимательность
    int reaction; // реакция
    int strength; // сила
    int inventiveness; // находчивость
    int luck; // удача
    int fame; // известность
    int mentalhealth; // ментальное здоровье по 100 балльной шкале
    List<String> charStats = new ArrayList<>(List.of(
            endurance, attentiveness, reaction, strength, inventiveness, luck, fame, mentalhealth)); // Массив статов
    int enduranceMod; // модификатор выносливости
    int attentivenessMod; // модификатор выносливости
    int reactionMod; // модификатор реакции
    int strengthMod; // модификатор силы
    int inventivenessMod; // модификатор находчивости
    int luckMod; // модификатор удачи
    int fameMod; // модификатор известности
    int mentalhealthMod; // модификатор ментального здоровья
    String[] bag = new String[4]; // Массив сумки
    String place1;
    String place2;
    String place3;
    String place4;
    List<String> storage = new ArrayList<>();
    String quest;
}
