package com.example.programm_8.Commands;

import com.example.programm_8.Utility.Chronicler;
import com.example.programm_8.Utility.CommandManager;

import java.io.IOException;

/** команда истории  */
public class History extends AbstractCommand{
    /** поле летописец */
    private final Chronicler chronicler;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     * @param chronicler летописец
     */
    public History(String name, String description, Chronicler chronicler) {
        super(name, description);
        this.chronicler=chronicler;
    }

    /**
     * Команда выводит последние 13 команд
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() {
        String[] history = chronicler.getHistory();
        CommandManager.ans =("Вывожу последние 13 команд:")+ "\n";
        for (String s : history){
            if(!(s==null)){
                CommandManager.ans+=(s)+ "\n";
            }
        }
        return true;
    }
}
