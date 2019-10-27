package ru.vitaly.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AI {
    /**
     * Поле.
     */
    private final Board board;
    /**
     * Выигрышные комбинации.
     */
    private final List<List<Integer>> winners;

    /**
     * @param board поле.
     */
    public AI(Board board) {
        this.board = board;
        winners = construct();
    }

    /**
     * @return список со списками целочисленных значений - выигрышные комбинации.
     * 1. Внеш.цикл продолжается на величину [(длина поля * 2) + 2]. Если 3, то 8 раз. Если 5, то 12 раз и т.д.
     * 2. Внутр.цикл продолжается на величину длины поля (3 раза, 5 раз и т.д.).
     * 3. Счетчик i также используется для подсчета 3 промежутков:
     * 4. когда будут перебраны все горизонтали в кол-ве длины поля (value нужно увеличивать на 1)
     * 5. когда будут перебраны все вертикали в кол-ве длины поля (value нужно увеличивать на величину side)
     * 6. когда будут перебраны 2 диагонали (в одном случает value нужно увеличивать на side + 1),
     * в другом - на side - 1.
     */
    private List<List<Integer>> construct() {
        int count = 0;
        int value = 0;
        int side = board.getSide();
        int adder = 0;
        int tmp = 0;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> inner = new ArrayList<>();
        for (int i = 0; i < (side * 2) + 2; i++) { //1.
            while (count < side) { //2.
                if (i < side) { //3.
                    inner.add(value++);
                } else if (i >= side && i < side * 2) {
                    if (count == 0) {
                        value = tmp++;
                    }
                    inner.add(value);
                    value += side; //5.
                } else {
                    inner.add(value);
                    value += adder; //6.
                }
                count++;
            }
            result.add(new ArrayList<>(inner));
            inner.clear();
            count = 0;
            value = (i == side - 1) ? 0 : value;
            if (i == side * 2 - 1) {
                value = 0;
                adder = side + 1;
            } else if (i == side * 2) {
                value = side - 1;
                adder = side - 1;
            }
        }
        return result;
    }

    /**
     * @param symbol символ.
     * @return true, если кол-во symbol среди выигрышных комбинаций равно длине поля, и false, если - нет.
     */
    public boolean isWinner(String symbol) {
        return winners
                .stream()
                .map(list -> list
                        .stream()
                        .filter(i -> symbol.equals(board.getCells().get(i).toString())).count())
                .anyMatch(val -> val == board.getSide());
    }

    /**
     * @param symbol символ.
     * @param amount количество ходов оппонента.
     * @return позицию на поле, куда нужно поставить свой символ, чтобы не дать возможность оппоненту.
     * 1. Просмотреть все целые значения каждого списка из winners.
     * 2. Если amount == 2, то любые 2 значения из 3 должны иметь символы, равные symbol,
     * это и будут 2 хода оппонента, 3-й ход которого нужно занять, чтобы не дать выиграть.
     */
    public int getEnemyMove(String symbol, int amount) {
        int result = -1;
        int count = 0;
        final List<Cell> cells = board.getCells();
        for (List<Integer> list : winners) {
            for (Integer i : list) {
                if (symbol.equals(cells.get(i).toString())) {
                    count++;
                } else {
                    result = i;
                }
            }
            if (count == amount && isValidMove(result)) {
                break;
            }
            count = 0;
            result = -1;
        }
        return result;
    }

    /**
     * @return произвольное значение из списка с целочисленными значениями, полученного на основании ячеек,
     * имеющих вид ".".
     */
    public int getRandom() {
        List<Integer> list = board.getCells()
                .stream()
                .filter(cell -> ".".equals(cell.toString()))
                .map(Cell::getPosition)
                .collect(Collectors.toList());
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * @param move ход.
     * @param symbol символ.
     */
    public void makeMove(int move, String symbol) {
        board.changeCell(move, symbol);
    }

    /**
     * @param move ход, который нужно проверить.
     * @return true, если по значению move имеется незаполненная ячейка, и false, если такой ячейки нет.
     */
    public boolean isValidMove(int move) {
        return board.getCells()
                .stream()
                .anyMatch(c -> c.getPosition() == move && ".".equals(c.toString()));
    }

    /**
     * @return true, если все ячейки заняты, и false, если - нет.
     */
    public boolean isFull() {
        return board.getCells()
                .stream()
                .noneMatch(c -> ".".equals(c.toString()));
    }

    /**
     * Очистить списки с ходами и доску.
     */
    public void clear() {
        board.clear();
    }

    /**
     * Отображает в консоли все ячейки.
     */
    public void draw() {
        board.draw();
    }

    /**
     * @return список списков с выигрышными комбинациями.
     */
    public List<List<Integer>> getWinners() {
        return winners;
    }
}