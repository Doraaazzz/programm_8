package com.example.programm_8.Data;

import com.example.programm_8.exceptions.IncorrectData;

import java.io.Serializable;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null

    /**
     * конструктор
     * @param name имя человека
     * @param height рост человека
     * @param eyeColor цвет глаз
     * @param hairColor цвет волос
     */
    public Person(String name, Double height, Color eyeColor, Color hairColor) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    /**
     * конструктор без параметров
     */
    public Person() {

    }

    /**
     * метод получает значение поля {@link Person#name}
     * @return возращает имя
     */
    public String getName() {
        return name;
    }

    /**
     * метод получает значение поля {@link Person#height}
     * @return возвращает рост
     */
    public Double getHeight() {
        return height;
    }
    /**
     * метод получает значение поля {@link Person#eyeColor}
     * @return возвращает цвет глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }
    /**
     * метод получает значение поля {@link Person#hairColor}
     * @return возвращает цвет волос
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * метод устанавливает значение поля {@link Person#name }
     * @param name имя
     * @throws IncorrectData
     */
    public void setName(String name) throws IncorrectData {
        if (name.equals("") || name == null) {
            throw new IncorrectData("Incorrect name");
        } else {
            this.name = name;
        }
    }

    /**
     * метод устанавливает значение поля {@link Person#height }
     * @param height рост
     * @throws IncorrectData
     */
    public void setHeight(Double height) throws IncorrectData {
        if (height == null || !(height > 0)) {
            throw new IncorrectData("Height is null or <=0");
        } else {
            this.height = height;
        }
    }

    /**
     * метод устанавливает значение поля {@link Person#eyeColor }
     * @param eyeColor цвет глаз
     * @throws IncorrectData
     */
    public void setEyeColor(Color eyeColor) throws IncorrectData {
        if(eyeColor == null){
            throw new IncorrectData("eyeColor is null");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    /**
     * метод устанавливает значение поля {@link Person#hairColor }
     * @param hairColor цвет волос
     * @throws IncorrectData
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * метод строкового представления объекта
     * @return возвращает строковое представление объекта
     */
    @Override
    public String toString(){
        return "Имя оператора: "+getName()+". \nРост оператора: "+getHeight()+". \nЦвет глаз оператора: "+
                getEyeColor()+". \nЦвет волос оператора: "+getHairColor()+".";
    }
}
