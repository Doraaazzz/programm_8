package com.example.programm_8.Commands;

/**
 * интерфейс с объявленными методами для всех комманд
 */
public interface Command {
    /** получение имени команды */
    String getName();

    /** получение описания команды */
    String getDescription();

    /** исполнение команды */
    boolean exec();
}
