package ru.vitaly.model;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 20.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class GameTest {
    @Test
    public void getWinnersAsStringTest() {
        Game game = Game.getInstance();
        String str = game.getWinnersAsString();

        assertThat("012345678036147258048246", is(str));
    }
}