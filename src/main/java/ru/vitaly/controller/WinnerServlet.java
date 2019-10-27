package ru.vitaly.controller;

import ru.vitaly.model.Game;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class WinnerServlet extends MainServlet {
    /**
     * Экземпляр класса Game.
     */
    private static final Game GAME = Game.getInstance();

    /**
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (final PrintWriter pw = resp.getWriter()) {
            pw.write(GAME.getWinnersAsString());
            pw.flush();
            req.getRequestURI();
        }
    }
}