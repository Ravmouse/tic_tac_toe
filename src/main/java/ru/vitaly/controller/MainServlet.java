package ru.vitaly.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MainServlet extends HttpServlet {
    /**
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException исключение.
     * @throws IOException исключение.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int pcMove = (Integer) req.getAttribute("pcMove");
        try (final PrintWriter pw = resp.getWriter()) {
            pw.write(String.valueOf(pcMove));
            pw.flush();
        }
    }
}