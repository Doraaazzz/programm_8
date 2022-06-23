package com.example.programm_8.Commands;

import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;

/**
 * комнада информации о коллекции
 */
public class Info extends AbstractCommand {
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
    public Info(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Команда выводит информацию о коллекции
     *
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.printInfo());
        return true;
    }
}
