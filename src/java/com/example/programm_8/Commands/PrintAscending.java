package com.example.programm_8.Commands;

import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;

/** команда печати коллекции по возрастанию */
public class PrintAscending extends AbstractCommand {
    /** поле менеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public PrintAscending(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Команда печатает коллекцию по возрастанию
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.printAscendingCollection());
        return true;
    }
}
