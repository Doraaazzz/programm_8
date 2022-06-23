package com.example.programm_8.Commands;

import com.example.programm_8.Data.Movie;
import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;

/** команда получения первого элемента коллекции */
public class Head extends AbstractCommand{
    /** поле менеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public Head(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Команда выводит сообщение о том, что колекция пуста, или первый элемент коллекции
     * @return True после выполнения
     * @throws IOException
     */
    @Override
    public boolean exec() {
        Movie head = collectionManager.getHead();
        if (head == null){
            Module.addMessage("Коллекция пуста.");
        } else {
            Module.addMessage(collectionManager.getHead().toString());
        }
        return true;
    }
}
