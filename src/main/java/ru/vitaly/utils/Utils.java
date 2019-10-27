package ru.vitaly.utils;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
//import java.util.Optional;
//import java.util.stream.Stream;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Utils {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    public static String getRequestString(final HttpServletRequest req) {
        String result = null;
        try (final BufferedReader reader = req.getReader()) {
            result = reader.readLine();
            System.out.println("From getRequestString method: " + result);
        } catch (IOException io) {
            LOG.debug(io);
        }
        return result;
    }

//    private static String retrieve(String req, String key) {
//        final String[] array = req.split(",");
//        final Optional<String> value = Stream.of(array).filter(a -> a.contains(key)).findFirst();
//        return value.toString();
//    }

//    public static String getString(String request, String key) {
//        final String tmp = retrieve(request, key);
//        return tmp.substring(tmp.indexOf(":") + 2, tmp.lastIndexOf("\""));
//    }

//    public static int getInt(String request, String key) {
//        final String tmp = retrieve(request, key);
//        final String result = tmp.substring(tmp.indexOf(":") + 1, tmp.indexOf(":") + 2);
//        return Integer.parseInt(result);
//    }

    /**
     * Выкидывается исключение и, ввиду того, что этот метод должен запускаться из другого класса, получается имя
     * класса, из которого был вызван данный метод.
     * @return имя класса в виде строки, из которого был выполнен этот метод.
     */
    public static String getNameOfTheClass() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            return re.getStackTrace()[1].getClassName();
        }
    }
}