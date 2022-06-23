package com.example.programm_8.Commands;

import com.example.programm_8.Utility.CommandManager;

import java.io.IOException;

/** команда вывода имени и описания всех возможных команд */
public class Help extends AbstractCommand{

    private final Add add;
    private final Clear clear;
    private final ExecuteScript executeScript;
    private final Exit exit;
    private final Head head;
    private final History history;
    private final Info info;
    private final PrintAscending printAscending;
    private final PrintFieldDescendingMpaaRating printFieldDescendingMpaaRating;
    private final RemoveAllByGoldenPalmCount removeAllByGoldenPalmCount;
    private final RemoveById removeById;
    private final RemoveFirst removeFirst;
    private final Show show;
    private final UpdateById updateById;

    /**
     * конструктор
     * @param name имя команды
     * @param description описание команды
     * @param add добавление элемента
     * @param clear очистка коллекции
     * @param executeScript запуск скрипта
     * @param exit завершение программы
     * @param head получение первого элемента коллекции
     * @param history история
     * @param info информация о коллекции
     * @param printAscending вывод элементов по возрастанию
     * @param printFieldDescendingMpaaRating вывод MpaaRating по уменьшению
     * @param removeAllByGoldenPalmCount удаление всех элементов, чей GoldenPalmCount равен введенному
     * @param removeById удаление по id
     * @param removeFirst удаление первого элемента
     * @param show вывод информации о коллекции
     * @param updateById обновление элемента по id
     */
    public Help(String name, String description, Add add, Clear clear, ExecuteScript executeScript, Exit exit, Head head, History history, Info info, PrintAscending printAscending, PrintFieldDescendingMpaaRating printFieldDescendingMpaaRating, RemoveAllByGoldenPalmCount removeAllByGoldenPalmCount, RemoveById removeById, RemoveFirst removeFirst, Show show, UpdateById updateById) {
        super(name, description);
        this.add = add;
        this.clear = clear;
        this.executeScript = executeScript;
        this.exit = exit;
        this.head = head;
        this.history = history;
        this.info = info;
        this.printAscending = printAscending;
        this.printFieldDescendingMpaaRating = printFieldDescendingMpaaRating;
        this.removeAllByGoldenPalmCount = removeAllByGoldenPalmCount;
        this.removeById = removeById;
        this.removeFirst = removeFirst;
        this.show = show;
        this.updateById = updateById;
    }

    /**
     * Метод вызывает у команд геттеры имён и описаний и выводит их в консоль.
     * @return Возвращает True при выполнении
     * @throws IOException
     */
    @Override
    public boolean exec() {
        CommandManager.ans += ("Справка по командам: ")+ "\n";
        CommandManager.ans += (add.getName()+": "+add.getDescription())+ "\n";
        CommandManager.ans += (clear.getName()+": "+clear.getDescription())+ "\n";
        CommandManager.ans += (executeScript.getName()+": "+executeScript.getDescription())+ "\n";
        CommandManager.ans += (exit.getName()+": "+exit.getDescription())+ "\n";
        CommandManager.ans += (head.getName()+": "+head.getDescription())+ "\n";
        CommandManager.ans += (history.getName()+": "+history.getDescription())+ "\n";
        CommandManager.ans += (info.getName()+": "+info.getDescription())+ "\n";
        CommandManager.ans += (printAscending.getName()+": "+printAscending.getDescription())+ "\n";
        CommandManager.ans += (printFieldDescendingMpaaRating.getName()+": "+printFieldDescendingMpaaRating.getDescription())+ "\n";
        CommandManager.ans += (removeAllByGoldenPalmCount.getName()+": "+removeAllByGoldenPalmCount.getDescription())+ "\n";
        CommandManager.ans += (removeById.getName()+": "+removeById.getDescription())+ "\n";
        CommandManager.ans += (removeFirst.getName()+": "+removeFirst.getDescription())+ "\n";
        CommandManager.ans += (show.getName()+": "+show.getDescription())+ "\n";
        CommandManager.ans += (updateById.getName()+": "+updateById.getDescription())+ "\n";
        return true;
    }
}
