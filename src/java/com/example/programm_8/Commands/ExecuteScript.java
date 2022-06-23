package com.example.programm_8.Commands;

import com.example.programm_8.Data.User;
import com.example.programm_8.Utility.CommandManager;
import com.example.programm_8.Utility.FileManager;
import com.example.programm_8.Utility.Inquiry;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

/**
 * команда запуска скрипта
 */
public class ExecuteScript extends AbstractCommand {

    /**
     * поле менеджер комманд
     */
    private User user;
    private final CommandManager commandManager;
    private String argument;
    private boolean flag;

    /**
     * конструктор
     *
     * @param name           имя команды
     * @param description    описание коллекции
     * @param commandManager сущность, которая отвечает за распознавание и вызов выполнения всех команд.
     */
    public ExecuteScript(String name, String description, CommandManager commandManager) {
        super(name, description);
        this.commandManager = commandManager;
        flag = false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Метод читает скрипт-файл, при неверно введённом имени файла или отсутствии прав доступа запрашивает повторный ввод имени файла.
     * После прочтения файла последовательно выполняет все команды.
     *
     * @return Возвращает True при выполнении команды
     * @throws IOException
     * @see FileManager#scriptOpen(String)
     * @see Inquiry#askFilenameForExecuteScript()
     * @see CommandManager#managerWork(String)
     */
    @Override
    public boolean exec() {
        if (flag) {
            String filename = argument;
            Scanner script = null;
            try {
                script = FileManager.scriptOpen(filename);
            } catch (IOException e) {
                System.out.println("Возникла ошибка при чтении скрипта. Команда пропущена.");
                return false;
            }
            if (!commandManager.checkLoopTry(filename)) {
                commandManager.addScriptsStack(filename);
                commandManager.scriptscounterIncrement();
                commandManager.executeScriptCommand(script);
                commandManager.scriptscounterDecrement();
                return true;
            } else {
                System.out.println("Обнаружена попытка залупливания. Цикл прерван, скрипт завершён.");
                return false;
            }
        } else {
            boolean success = false;
            String filename = argument;
            Scanner script = null;
            while (!success) {
                try {
                    script = FileManager.scriptOpen(filename);
                    success = true;
                } catch (NoSuchFileException e) {
                    System.out.println("Файл не найден, повторите ввод имени файла.");
                    filename = Inquiry.askFilenameForExecuteScript();
                } catch (AccessDeniedException e) {
                    System.out.println("Недостаточно прав для доступа к файлу, повторите ввод имени файла.");
                    filename = Inquiry.askFilenameForExecuteScript();
                } catch (IOException e){
                    System.out.println("Возникла ошибка при чтении скрипта. Команда пропущена.");
                }
            }
            if (!commandManager.checkLoopTry(filename)) {
                commandManager.addScriptsStack(filename);
                commandManager.scriptscounterIncrement();
                commandManager.executeScriptCommand(script);
                commandManager.scriptscounterDecrement();
            } else {
                System.out.println("Обнаружена попытка залупливания. Цикл прерван, скрипт завершён.");
            }
            return true;
        }
    }
}
