package ru.vitaly.controller;

import org.apache.log4j.Logger;
import ru.vitaly.model.Game;
import ru.vitaly.utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static ru.vitaly.utils.Utils.getRequestString;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class InitServlet extends HttpServlet {
    /**
     * Экземпляр класса Game.
     */
    private static final Game GAME = Game.getInstance();
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info(System.lineSeparator() + "InitServlet");
        final String symbol = getRequestString(req);
        LOG.info("InitServlet: player symbol = " + symbol);
        GAME.init(symbol);
    }
}