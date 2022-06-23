package com.example.programm_8.Commands;

import com.example.programm_8.Data.Movie;
import com.example.programm_8.Data.User;
import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;
import com.example.programm_8.Utility.Inquiry;
import java.io.IOException;

/**
 * команда обновления элемента по id
 */
public class UpdateById extends AbstractCommand {

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
     * @param description описание коллекции
     */
    public UpdateById(String name, String description) {
        super(name, description);
    }

    public void setArgument(Movie argument) {
        this.argument = argument;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setUser(User user ){
        this.user=user;
    }

    /**
     * Метод конвертирует аргумент в номер id элемента, который нужно обновить. Если номер введён неверно или в коллекции нет элемента с таким id, запрашивает повторный ввод.
     * Запрашивает у пользователя данные обновлённого объекта, если он хочет ввести их вручную, или заполняет автоматически случайными значениями.
     * Вызывает у пользователя замену элемента по номеру ID.
     *
     * @return Возвращает True при выполнении.
     * @throws IOException
     * @see Inquiry#askID()
     * @see CollectionManager#replaceElementByID(Movie, Integer)
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.replaceElementByID(argument, argument.getId()));
        return true;
    }
}
