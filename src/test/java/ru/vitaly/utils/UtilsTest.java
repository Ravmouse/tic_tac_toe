package ru.vitaly.utils;

import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vitaly Vasilyev, date: 27.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class UtilsTest {
    @Test
    public void getRequestStringTest() throws IOException {
        char[] buf = "Test".toCharArray();
        BufferedReader reader = new BufferedReader(new CharArrayReader(buf));
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getReader()).thenReturn(reader);

        String result = Utils.getRequestString(request);
        assertEquals(result, "Test");
    }
}