package com.example.programm_8.Commands;

import com.example.programm_8.Server.Module;
import com.example.programm_8.Utility.CollectionManager;

import java.io.IOException;
/** печать MPAA RATINGS элементов коллекции в порядке убывания */
public class PrintFieldDescendingMpaaRating extends AbstractCommand {

    /** поле менеджер коллекции */
    private CollectionManager collectionManager;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     */
    public PrintFieldDescendingMpaaRating(String name, String description) {
        super(name, description);
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Команда печатает MPAA RATINGS элементов коллекции в порядке убывания
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() {
        Module.addMessage(collectionManager.printDescendingMpaaRatings());
        return true;
    }
}
