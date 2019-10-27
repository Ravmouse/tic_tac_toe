package ru.vitaly.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Board {
    /**
     * Кол-во клеток по одной стороне.
     */
    private final int side;
    /**
     * Список ячеек.
     */
    private final List<Cell> cells = new ArrayList<>();

    /**
     * Конструктор.
     * @param side количество ячеек для одной из сторон.
     */
    public Board(int side) {
        this.side = side;
        int area = (int) Math.pow(side, 2);
        for (int i = 0; i < area; i++) {
            cells.add(new Cell(i));
        }
    }

    /**
     * Изменяет вид ячейки на определенной позиции.
     * @param pos позиция.
     * @param symbol символ.
     */
    public void changeCell(int pos, String symbol) {
        cells.get(pos).changeView(symbol);
    }

    /**
     * @return количество ячеек по одной стороне.
     */
    public int getSide() {
        return side;
    }

    /**
     * @return список ячеек.
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Изменяет вид всех ячеек на "." - очищает их.
     */
    public void clear() {
        cells.forEach(c -> c.changeView("."));
    }

    /**
     * Отображает в консоли все ячейки.
     */
    public void draw() {
        int k = 0;
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print(cells.get(k++));
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return this.cells.toString();
    }
}