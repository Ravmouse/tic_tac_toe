package ru.vitaly.model;

import org.apache.log4j.Logger;
import ru.vitaly.utils.Utils;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Game {
    /**
     * Ссылка на экземпляр этого класса
     */
    private static final Game INSTANCE = new Game();
    /**
     * AI.
     */
    private final AI ai;
    /**
     * Символ пользователя.
     */
    private String playerSymbol;
    /**
     * Символ "pc".
     */
    private String pcSymbol;
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Для двух потоков из двух AJAX-запросов со стороны клиента.
     */
    private final AtomicBoolean bool = new AtomicBoolean();

    /**
     * Конструктор.
     */
    private Game() {
        ai = new AI(new Board(3));
    }

    /**
     * @return экземпляр данного класса.
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * @param symbol символ пользователя.
     * По окончании работы значение bool становится true, что позволяет др. потоку продолжить свою работу.
     */
    public void init(String symbol) {
        playerSymbol = symbol;
        pcSymbol = "X".equals(symbol) ? "O" : "X";
        LOG.info("player = " + playerSymbol + ", pc = " + pcSymbol);
        bool.set(true);
    }

    /**
     * @param move ход пользователя.
     * @return ход "pc".
     */
    public int play(int move) {
        user(move);
        return computer();
    }

    /**
     * @return
     * <p>Если у "pc" нет возможности поставить свой символ в выигрыш.комбинации, то getEnemyMove()
     * возвращает -1, но после идет вызов getRandom().
     */
    public int computer() {
        while (!bool.get()) {
            byte x = 1;
        }
        int pcMove = ai.getEnemyMove(playerSymbol, 2);
        if (pcMove == -1) {
            pcMove = ai.getRandom();
        }
        ai.makeMove(pcMove, pcSymbol);
        ai.draw();
        return pcMove;
    }

    /**
     * @param move ход пользователя.
     */
    private void user(int move) {
        ai.makeMove(move, playerSymbol);
    }

    /**
     * Рестарт.
     */
    public void restart() {
        ai.clear();
    }

    /**
     * @return выигрышные комбинации в виде строки.
     */
    public String getWinnersAsString() {
        return ai.getWinners()
                .stream()
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}