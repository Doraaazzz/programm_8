package com.example.programm_8.exceptions;

/** класс ошибки некорректных данных */
public class IncorrectData extends Exception{
    public IncorrectData(String errorMessage){
        super(errorMessage);
    }
}
