package com.example.programm_8.Commands;

import java.io.Serializable;

/**
 * Абстрактный класс, от которого наследуются все команды
 */
public abstract class AbstractCommand implements Command, Serializable {
    /** Поле имя */
    private final String name;
    /** Поле описание */
    private final String description;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public AbstractCommand(String name, String description){
        this.name=name;
        this.description=description;
    }

    /**
     * функция получения значения поля {@link AbstractCommand#name}
     * @return возвращает имя команды
     */
    public String getName(){
        return name;
    }

    /**
     * функция получения значения поля {@link AbstractCommand#description}
     * @return возращает описание команды
     */
    public String getDescription(){
        return description;
    }
}
