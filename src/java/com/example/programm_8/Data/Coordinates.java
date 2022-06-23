package com.example.programm_8.Data;

import com.example.programm_8.exceptions.IncorrectData;

import java.io.Serializable;

/** координаты для элемента коллекции */
public class Coordinates implements Serializable {
    private long x;
    private double y;

    /**
     * конструктор
     * @param x координата х
     * @param y координата у
     */
    public Coordinates(long x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * конструктор без параметров
     */
    public Coordinates() {
    }

    /**
     * метод устанавливает значение поля {@link Coordinates#x}
     * @param x координата х
     * @throws IncorrectData
     */
    public void setX(Long x) throws IncorrectData {
        if (x == null) {
            throw new IncorrectData("По смыслу координата не может быть null");
        } else {
            this.x = x;
        }
    }
    /**
     * метод устанавливает значение поля {@link Coordinates#y}
     * @param y координата y
     * @throws IncorrectData
     */
    public void setY(Double y) throws IncorrectData {
        if (y == null) {
            throw new IncorrectData("По смыслу координата не может быть null");
        } else {
            this.y = y;
        }
    }

    /**
     * Метод получения значения поля {@link Coordinates#x}
     * @return  Возращает координату х
     */
    public long getX() {
        return x;
    }

    /**
     * Метод получения значения поля {@link Coordinates#y}
     * @return  Возращает координату y
     */
    public double getY() {
        return y;
    }

    /**
     * Метод строкого представления объекта*
     * @return возвращает сроковое представление объекта
     */
    @Override
    public String toString(){
        return "Координаты: "+getX()+"(x) ,"+getY()+"(y) .\n";
    }
}
