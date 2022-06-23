package com.example.programm_8.Commands;

import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;

/** команда вывода информации о коллекции */
public class Show extends AbstractCommand{
    /** поле менеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public Show(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     *Метод вызывает у Менеджера коллекции вывод в консоль информации о коллекции.
     * @return Возвращает True при выполнении
     * @throws IOException
     * @see CollectionManager#printCollection()
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.printCollection());
        return true;
    }
}
