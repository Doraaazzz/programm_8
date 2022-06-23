package com.example.programm_8.Commands;

import com.example.programm_8.Data.Movie;
import com.example.programm_8.Data.User;
import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;
import com.example.programm_8.Utility.Inquiry;

import java.io.IOException;

/**
 * команда добавления в коллекцию
 */
public class Add extends AbstractCommand {

    /**
     * поле менеджер коллекции
     */
    private CollectionManager collectionManager;
    private Movie argument;
    private User user;

    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public Add(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(Movie argument) {
        this.argument = argument;
    }



    /**
     * Метод создаёт объект типа Movie, спрашивает у пользователя, хочет ли он ввести данные самостоятельно или заполнить случайными значениями.
     * Запрашивает данные, если пользователь решает ввести самостоятельно
     *
     * @return Возвращает True при выполнении.
     * @throws IOException
     * @see Inquiry
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.addElement(argument));
        return true;
    }
}
