package com.example.programm_8.Commands;

import java.io.IOException;

/** команда завершения программы */
public class Exit extends AbstractCommand{

    /**
     * коструктор
     * @param name имя команды
     * @param description описание команды
     */
    public Exit(String name, String description) {
        super(name, description);
    }


    /**
     * Метод выводит сообщение о завершении программы. Сама программа завершается автоматически после выполнения цикла в методе Main с введённой командой exit.
     * @return Возвращает True после выполнения
     * @throws IOException
     */
    @Override
    public boolean exec() {
        System.out.println("Завершаю программу.");
        return true;
    }
}
