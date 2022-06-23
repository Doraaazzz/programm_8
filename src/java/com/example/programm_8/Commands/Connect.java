package com.example.programm_8.Commands;

import com.example.programm_8.Data.User;
import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;
import com.example.programm_8.exceptions.DatabaseAuthorizationException;


public class Connect extends AbstractCommand {

    private User user;
    private CollectionManager collectionManager;

    /**
     * конструктор
     *
     * @param name        имя команды
     * @param description описание команды
     */
    public Connect(String name, String description, User user) {
        super(name, description);
        this.user = user;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean exec() {
        try {
            Module.addMessage(collectionManager.auth(user));
            return true;
        } catch (DatabaseAuthorizationException e) {
            Module.addMessage(e.getMessage());
            return false;
        }
    }
}
