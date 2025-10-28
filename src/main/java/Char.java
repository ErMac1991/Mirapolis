import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Char {
    String login; // логин игрока
    int level; // уровень игрока. Высчитывается исходя из статов
    /*
    String head; // Голова. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String body; // Туловище. Указывается "Плоть", если родное или восстановленное ИЛИ модель протеза
    String lefthand; // Левая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String righthand; // Правая рука. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String leftleg; // Левая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    String rightleg; // Правая нога. Указывается "Плоть", если родная или восстановленная ИЛИ модель протеза
    */
    List<String> bodyParts = new ArrayList<>(6); // массив частей тела: Голова, Туловище, Левая рука, Правая рука, Левая нога, Правая нога
    /*
    int endurance; // выносливость
    int attentiveness; // внимательность
    int reaction; // реакция
    int strength; // сила
    int inventiveness; // находчивость
    int luck; // удача
    int fame; // известность
    int mentalHealth; // ментальное здоровье по 100 балльной шкале
    */
    List<Integer> charStats = new ArrayList<>(8); // Массив статов
    /*int enduranceMod; // модификатор выносливости
    int attentivenessMod; // модификатор выносливости
    int reactionMod; // модификатор реакции
    int strengthMod; // модификатор силы
    int inventivenessMod; // модификатор находчивости
    int luckMod; // модификатор удачи
    int fameMod; // модификатор известности
    int mentalHealthMod; // модификатор ментального здоровья

     */
    List<Integer> charMods = new ArrayList<>(8); // Массив статов
    String[] bag = new String[4]; // Массив сумки на 4 места
    List<String> storage = new ArrayList<>();
    String quest;
}
