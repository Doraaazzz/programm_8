package com.example.programm_8.Commands;

import com.example.programm_8.Data.User;
import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;

/**
 * команда удаления первого элемента коллекции
 */
public class RemoveFirst extends AbstractCommand {

    private User user;

    /**
     * поле менеджер коллекции
     */
    private CollectionManager collectionManager;

    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public RemoveFirst(String name, String description, User user) {
        super(name, description);
        this.user = user;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Метод вызывает удаление первого элемента коллекции
     *
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#removeFirst(User)
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.removeFirst(user));
        return true;
    }
}
