package ru.vitaly.model;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 20.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AITest {
    @Test
    public void isValidMoveTest() {
        Board board = new Board(3);
        AI ai = new AI(board);
//        board.changeCell(0, "X");
        board.changeCell(1, "X");
        board.changeCell(2, "X");
        board.changeCell(3, "X");
        board.changeCell(4, "O");
        board.changeCell(5, "O");
        board.changeCell(6, "O");
        board.changeCell(7, "O");
        board.changeCell(8, "O");
        assertThat(ai.isValidMove(0), is(true));
    }

    @Test
    public void isFullTest() {
        Board board = new Board(3);
        AI ai = new AI(board);
        board.changeCell(0, "X");
        board.changeCell(1, "X");
        board.changeCell(2, "X");
        board.changeCell(3, "X");
        board.changeCell(4, "O");
        board.changeCell(5, "O");
        board.changeCell(6, "O");
        board.changeCell(7, "O");
        board.changeCell(8, "O");
        assertThat(ai.isFull(), is(true));
    }

    @Test
    public void getEnemyMoveTest() {
        Board board = new Board(3);
        AI ai = new AI(board);
        board.changeCell(4, "X");
        board.changeCell(8, "X");
        assertThat(ai.getEnemyMove("X", 2), is(0));

        board.changeCell(6, "O");
        board.changeCell(8, "O");
        assertThat(ai.getEnemyMove("O", 2), is(7));
    }

    @Test
    public void isWinnerTest() {
        Board board = new Board(3);
        AI ai = new AI(board);
        board.changeCell(0, "X");
        board.changeCell(4, "X");
        board.changeCell(8, "X");
        assertThat(ai.isWinner("X"), is(true));

        board.changeCell(2, "O");
        board.changeCell(4, "O");
        board.changeCell(6, "O");
        assertThat(ai.isWinner("O"), is(true));
    }

    @Test
    public void constructTest() {
        Board board = new Board(3);
        AI ai = new AI(board);

    }
}