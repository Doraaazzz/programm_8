package com.example.programm_8.Commands;

import com.example.programm_8.Utility.*;
import com.example.programm_8.Server.Module;

public class getTable extends AbstractCommand{

        private CollectionManager collectionManager;

        /**
         * конструктор
         *
         * @param name        имя команды
         * @param description описание команды
         */
        public getTable(String name, String description) {
            super(name, description);
        }

        public void setCollectionManager(CollectionManager collectionManager) {
            this.collectionManager = collectionManager;
        }

        @Override
        public boolean exec() {
            Module.addMessage(collectionManager.getTable());
            return true;
        }
    }
