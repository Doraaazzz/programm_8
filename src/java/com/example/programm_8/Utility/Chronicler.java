package com.example.programm_8.Utility;

/**  класс летописца истории */
public class Chronicler {
    /** поле история, массив стрингов */
    private String[] history;

    /**
     * конструктор без параметров
     */
    public Chronicler() {
        this.history = new String[13];
    }

    /**
     * метод возвращает мзначение поля {@link Chronicler#history}
     * @return возвращает массив последних 13 команд
     */
    public String[] getHistory() {
        return history;
    }

    /**
     * метод добавления команды в конец массива history
     * @param newCommand команда для добавления
     */
    public  void addCommandInHistory(String newCommand) {
        for (int i = 0; i < 12; i++) {
            history[i] = history[i+1];
        }
        history[12] = newCommand;
    }
}
