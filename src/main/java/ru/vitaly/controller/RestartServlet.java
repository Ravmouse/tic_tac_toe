package ru.vitaly.controller;

import ru.vitaly.model.Game;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class RestartServlet extends MainServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GAME.restart();
    }
}