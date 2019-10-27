package ru.vitaly.model;

/**
 * @author Vitaly Vasilyev, date: 17.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Cell {
    /**
     * Позиция.
     */
    private final int position;
    /**
     * Вид.
     */
    private String view = ".";

    /**
     * Конструктор.
     * @param position позиция.
     */
    public Cell(int position) {
        this.position = position;
    }

    /**
     * @return позицию ячейки.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param view строка с новым отображением.
     */
    public void changeView(String view) {
        this.view = view;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return view;
    }
}