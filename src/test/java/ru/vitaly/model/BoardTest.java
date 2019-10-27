package ru.vitaly.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Vitaly Vasilyev, date: 20.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class BoardTest {
    @Test
    public void clearTest() {
        Board board = new Board(3);
        board.changeCell(0, "Q");
        board.changeCell(8, "P");
        board.clear();
        assertEquals(board.toString(), "[., ., ., ., ., ., ., ., .]");
    }
}