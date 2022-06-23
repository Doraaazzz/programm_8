package com.example.programm_8.Utility;

import com.example.programm_8.Client.Client;
import com.example.programm_8.Commands.*;
import com.example.programm_8.Data.*;
import com.example.programm_8.exceptions.ArgumentException;
import com.example.programm_8.exceptions.IncompleteData;
import com.example.programm_8.exceptions.IncorrectData;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс сущности, которая будет парсить все поступаемые команды и вызывать их выполнение.
 */
public class CommandManager {

    /**
     * Поля, содержащие объекты команд.
     */
    private final Add add;
    private final Clear clear;
    private final ExecuteScript executeScript;
    private final Exit exit;
    private final Head head;
    private final Help help;
    private final History history;
    private final Info info;
    private final PrintAscending printAscending;
    private final PrintFieldDescendingMpaaRating printFieldDescendingMpaaRating;
    private final RemoveAllByGoldenPalmCount removeAllByGoldenPalmCount;
    private final RemoveById removeById;
    private final RemoveFirst removeFirst;
    private final Show show;
    private final UpdateById updateById;
    private final Client client;
    private final Chronicler chrolicler;
    //    private final Connect connect;
    private final User user;
    private ArrayList<String> ScriptsStack = new ArrayList<>();
    private ArrayList<Scanner> scripts = new ArrayList<>();
    private int scriptcounter = -1;
    public static String ans="";

    private String[] commands = {"add", "clear", "execute_script", "exit", "head", "history", "info", "print_ascending",
            "print_field_descending_mpaa_rating", "remove_all_by_golden_palm_count", "remove_by_id", "remove_first",
            "show", "update", "help","getTable"};

    /**
     * Конструктор менеджера. Автоматически инициализирует объекты всех команд при создании и менеджера коллекций.
     *
     * @throws IOException
     * @see CollectionManager
     */
    public CommandManager(Client client, User user) throws IOException {
        this.client = client;
        chrolicler = new Chronicler();
        this.user = user;
        add = new Add("add", "добавить новый элемент в коллекцию");
        clear = new Clear("clear", "очистить коллекцию", user);
        executeScript = new ExecuteScript("execute_script filename", "считать и исполнить скрипт из указанного файла." +
                " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", this);
        exit = new Exit("exit", "завершить программу (без сохранения в файл)");
        head = new Head("head", "вывести первый элемент коллекции");
        history = new History("history", "вывести последние 13 команд (без их аргументов)", chrolicler);
        info = new Info("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата " +
                "инициализации, количество элементов и т.д.)");
        printAscending = new PrintAscending("print_ascending", "вывести элементы коллекции в порядке возрастания");
        printFieldDescendingMpaaRating = new PrintFieldDescendingMpaaRating("print_field_descending_mpaa_rating",
                "вывести значения поля mpaaRating всех элементов в порядке убывания");
        removeAllByGoldenPalmCount = new RemoveAllByGoldenPalmCount("remove_all_by_golden_palm_count goldenPalmCount",
                "удалить из коллекции все элементы, значение поля goldenPalmCount которого эквивалентно заданному", user);
        removeById = new RemoveById("remove_by_id id", "удалить элемент из коллекции по его id", user);
        removeFirst = new RemoveFirst("remove_first", "удалить первый элемент из коллекции", user);
        show = new Show("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        updateById = new UpdateById("update id", "обновить значение элемента коллекции, id которого равен заданному");
        help = new Help("help", "вывести справку по доступным командам", add, clear, executeScript, exit,
                head, history, info, printAscending, printFieldDescendingMpaaRating, removeAllByGoldenPalmCount, removeById, removeFirst, show, updateById);
//        connect = new Connect("connect", "команда для проверки соединения");
    }

    /**
     * @param line принимает на вход строку, парсит её на команду и её аргументы, по полученному имени запускает выполнение нужной команды, передаёт на вход команде аргумент или пустую строку, выводит ошибку, если не удалось сопоставить строке команду
     * @see CommandManager#commandParser(String)
     * @see CommandManager#chooseCommand(String)
     */
    public String managerWork(String line) throws ArgumentException {
        ans = "";
        String[] data = commandParser(line);
        switch (chooseCommand(data[0])) {
            case (0): {
                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");
                Movie movie = Data.movie;
                ans += ("Запускаю команду " + getAdd().getName() + " ...")+"\n";
                add.setArgument(movie);
                Data.movie = null;
                ans += (client.run(getAdd()))+"\n";
                break;
            }
            case (1): {
                ans += ("Запускаю команду " + getClear().getName() + " ...")+"\n";
                ans += (client.run(getClear()))+"\n";
                chrolicler.addCommandInHistory("clear");
                break;
            }
            case (2): {

                ans += ("Запускаю команду " + getExecuteScript().getName() + ": " + data[1] + " ...")+"\n";
                executeScript.setArgument(data[1]);
                ans += (client.run(getExecuteScript()))+"\n";
                chrolicler.addCommandInHistory("excecuteScript"+data[1]);
                break;
            }
            case (3): {
                //exit.exec();
                //chrolicler.addCommandInHistory("exit");
                break;
            }
            case (4): {
                ans += ("Запускаю команду " + getHead().getName() + " ...")+"\n";
                ans += (client.run(getHead()))+"\n";
                chrolicler.addCommandInHistory("head");
                break;
            }
            case (5): {
                ans += ("Запускаю команду " + getHistory().getName() + " ...")+"\n";
                getHistory().exec();
                chrolicler.addCommandInHistory("history");
                break;
            }
            case (6): {
                ans += ("Запускаю команду " + getInfo().getName() + " ...")+"\n";
                ans+= (client.run(getInfo()))+"\n";
                chrolicler.addCommandInHistory("info");
                break;
            }
            case (7): {
                ans += ("Запускаю команду " + getPrintAscending().getName() + " ...")+"\n";
                ans+= (client.run(getPrintAscending()))+"\n";
                chrolicler.addCommandInHistory("print_ascending");
                break;
            }
            case (8): {
                ans+= ("Запускаю команду " + getPrintFieldDescendingMpaaRating().getName() + " ...")+"\n";
                ans+= (client.run(getPrintFieldDescendingMpaaRating()))+"\n";
                chrolicler.addCommandInHistory("print_field_descending_mpaa_rating");
                break;
            }
            case (9): {
                Long goldenpalmcount;
                try {
                    goldenpalmcount = Long.parseLong(data[1]);
                    if (!(goldenpalmcount > 0)) {
                        throw new InputMismatchException("<=0");
                    }
                    ans+= ("Запускаю команду " + getRemoveAllByGoldenPalmCount().getName() + " ...")+"\n";
                    removeAllByGoldenPalmCount.setArgument(goldenpalmcount);
                    ans += client.run(getRemoveAllByGoldenPalmCount())+"\n";
                    chrolicler.addCommandInHistory("remove_all_by_golden_palm_count");
                } catch (InputMismatchException | NumberFormatException e) {
                    ans += ("Передан некорректный аргумент программе.");
                }
                break;
            }
            case (10): {
                int id;
                try {
                    id = Integer.parseInt(data[1]);
                    if (!(id > 0)) {
                        throw new InputMismatchException("<=0");
                    }
                    ans+= ("Запускаю команду " + getRemoveById().getName() + " ...")+"\n";
                    removeById.setArgument(id);
                    ans+= (client.run(getRemoveById()))+"\n";
                    chrolicler.addCommandInHistory("remove_by_id");
                } catch (InputMismatchException | NumberFormatException e) {
                    ans+= ("Передан некорректный аргумент программе.")+"\n";
                }
                break;
            }
            case (11): {
                ans+= ("Запускаю команду " + getRemoveFirst().getName() + " ...")+"\n";
                ans+= (client.run(getRemoveFirst()))+"\n";
                chrolicler.addCommandInHistory("remove_first");
                break;
            }
            case (12): {
                ans += ("Запускаю команду " + getShow().getName() + " ...")+"\n";
                ans += (client.run(getShow()))+"\n";
                chrolicler.addCommandInHistory("show");
                break;
            }
            case (13): {
                String argForParse = data[1];
                if (Data.movie==null) throw new ArgumentException("Не задан объект movie.");
                try {
                    int id = Integer.parseInt(argForParse);
                    if (!(id > 0)) {
                        throw new NumberFormatException();
                    }
                    Movie movie = Data.movie;
                    Data.movie = null;
                    movie.setId(id);
                    ans += ("Запускаю команду " + getUpdateById().getName() + ": " + id + " ...")+"\n";
                    updateById.setArgument(movie);
                    ans += (client.run(getUpdateById()))+"\n";
                    chrolicler.addCommandInHistory("updateById");
                } catch (NumberFormatException | IncorrectData exception) {
                    throw new ArgumentException("Введён неверный формат id, повторите ввод.");
                }
                break;
            }
            case (14): {
                ans += ("Запускаю команду " + getHelp().getName() + " ..."+"\n");
                getHelp().exec();
                chrolicler.addCommandInHistory("help");
                break;
            }
            case (15): {
                return client.run(new getTable("getTable", "desc"));
            }
            case (-1): {
                ans+=("Команда не распознана."+"\n");
                break;
            }
        }
        return ans;
    }

    /**
     * @param line получает на вход строку, разбивает её на пробелы, первое слово - команда, второе (если есть) - аргумент.
     * @return возвращает массив строк, где 0й элемент - команда, 1й (если есть) - аргумент.
     */
    public String[] commandParser(String line) {

        try {
            Scanner scanner = new Scanner(line);
            if (!(!line.contains(" "))) {
                scanner.useDelimiter("\\s");
                String command = scanner.next();
                String data = "";
                if (scanner.hasNext()) {
                    data = scanner.next();
                }
                return new String[]{command, data};
            } else {
                String commandwodata = scanner.next();
                return new String[]{commandwodata, " "};
            }
        } catch (NoSuchElementException e) {
            return new String[]{"  "};
        }
    }

    /**
     * @param command принимает на вход команду, сопоставляет ей элемент из списка команд.
     * @return возвращает порядковый номер элемента, который удалось сопоставить или -1, если не получилось.
     */
    private int chooseCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Метод для запуска скрипта. Парсит его и по очереди запускает команды, тащит в себе дополнительные аргументы-строки, если нужны.
     *
     * @param script
     * @throws IOException
     */
    public void executeScriptCommand(Scanner script) {
        scripts.add(script);
        while (scripts.get(scriptcounter).hasNext()) {
            String[] data = commandParser(scripts.get(scriptcounter).next());
            switch (chooseCommand(data[0])) {
                case (0): {
                    try {
                        Movie newMovie = new Movie();
                        String answer;
                        newMovie.setName(getNextLineFromScript());
                        newMovie.setCoordinates(new Coordinates(Long.parseLong(getNextLineFromScript()), Double.parseDouble(getNextLineFromScript())));
                        newMovie.setCreationDate(ZonedDateTime.now());
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            newMovie.setOscarsCount(null);
                        } else {
                            newMovie.setOscarsCount(Integer.parseInt(answer));
                        }
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            newMovie.setGoldenPalmCount(null);
                        } else {
                            newMovie.setGoldenPalmCount(Long.parseLong(answer));
                        }
                        newMovie.setGenre(MovieGenre.valueOf(getNextLineFromScript()));
                        newMovie.setMpaaRating(MpaaRating.valueOf(getNextLineFromScript()));
                        Person operator = new Person();
                        operator.setName(getNextLineFromScript());
                        operator.setHeight(Double.parseDouble(getNextLineFromScript()));
                        operator.setEyeColor(Color.valueOf(getNextLineFromScript()));
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            operator.setHairColor(null);
                        } else {
                            operator.setHairColor(Color.valueOf(answer));
                        }
                        newMovie.setOperator(operator);
                        add.setArgument(newMovie);
                        System.out.println(client.run(add));
                    } catch (IncompleteData e) {
                        System.out.println(e.getMessage() + " Skip add.");
                    } catch (Exception e) {
                        System.out.println("Unreadable data. Skip add.");
                        rollScriptForNextCommand();
                    }
                    break;
                }

                case (1): {
                    System.out.println(client.run(clear));
                    break;
                }
                case (2): {
                    executeScript.setArgument(data[1]);
                    executeScript.setFlag(true);
                    executeScript.exec();
                    executeScript.setFlag(false);
                    break;
                }
                case (3): {
                    //pass exit
                    break;
                }
                case (4): {
                    System.out.println(client.run(head));
                    break;
                }
                case (5): {
                    history.exec();
                    break;
                }
                case (6): {
                    System.out.println(client.run(info));
                    break;
                }
                case (7): {
                    System.out.println(client.run(printAscending));
                    break;
                }
                case (8): {
                    System.out.println(client.run(printFieldDescendingMpaaRating));
                    break;
                }
                case (9): {
                    Long goldenpalmcount;
                    try {
                        goldenpalmcount = Long.parseLong(data[1]);
                        if (!(goldenpalmcount > 0)) {
                            throw new InputMismatchException("<=0");
                        }
                        removeAllByGoldenPalmCount.setArgument(goldenpalmcount);
                        System.out.println(client.run(removeAllByGoldenPalmCount));
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Передан некорректный аргумент программе remove_all_by_golden_palm в скрипте.");
                    }
                    break;
                }
                case (10): {
                    int id;
                    try {
                        id = Integer.parseInt(data[1]);
                        if (!(id > 0)) {
                            throw new InputMismatchException("<=0");
                        }
                        removeById.setArgument(id);
                        System.out.println(client.run(removeById));
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Передан некорректный аргумент программе remove_by_id в скрипте.");
                    }
                    break;
                }
                case (11): {
                    System.out.println(client.run(removeFirst));
                    break;
                }
                case (12): {
                    System.out.println(client.run(show));
                    break;
                }
                case (13): {
                    int id;
                    try {
                        id = Integer.parseInt(data[1]);
                        if (!(id > 0)) {
                            throw new InputMismatchException("<=0");
                        }

                        Movie newMovie = new Movie();
                        String answer;
                        newMovie.setName(getNextLineFromScript());
                        newMovie.setCoordinates(new Coordinates(Long.parseLong(getNextLineFromScript()), Double.parseDouble(getNextLineFromScript())));
                        newMovie.setCreationDate(ZonedDateTime.now());
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            newMovie.setOscarsCount(null);
                        } else {
                            newMovie.setOscarsCount(Integer.parseInt(answer));
                        }
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            newMovie.setGoldenPalmCount(null);
                        } else {
                            newMovie.setGoldenPalmCount(Long.parseLong(answer));
                        }
                        newMovie.setGenre(MovieGenre.valueOf(getNextLineFromScript()));
                        newMovie.setMpaaRating(MpaaRating.valueOf(getNextLineFromScript()));
                        Person operator = new Person();
                        operator.setName(getNextLineFromScript());
                        operator.setHeight(Double.parseDouble(getNextLineFromScript()));
                        operator.setEyeColor(Color.valueOf(getNextLineFromScript()));
                        answer = getNextLineFromScript();
                        if (answer == null) {
                            operator.setHairColor(null);
                        } else {
                            operator.setHairColor(Color.valueOf(answer));
                        }
                        newMovie.setOperator(operator);
                        newMovie.setId(id);
                        updateById.setArgument(newMovie);
                        System.out.println(client.run(updateById));
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Передан некорректный аргумент программе.");
                    } catch (IncompleteData e) {
                        System.out.println(e.getMessage() + " Skip update.");
                    } catch (Exception e) {
                        System.out.println("Unreadable data. Skip update.");
                        rollScriptForNextCommand();
                    }
                    break;
                }
                case (14): {
                    help.exec();
                    break;
                }
                case (15): {
                    //connect
                }
                case (-1): {
                    System.out.println("Команда не распознана.");
                    break;
                }
            }
        }
    }

    public String getNextLineFromScript() throws IncompleteData {
        Scanner scanner = scripts.get(scriptcounter);
        for (String c : commands) {
            if (scanner.hasNext(c)) {
                throw new IncompleteData("Данные объекта неполные.");
            }
        }
        String nextLine = scanner.next();
        if (nextLine.equals("")) {
            return null;
        } else {
            return nextLine;
        }
    }

    public void rollScriptForNextCommand() {
        boolean rollComplete = false;
        Scanner scanner = scripts.get(scriptcounter);
        while (scanner.hasNext()) {
            for (String c : commands) {
                if (scanner.hasNext(c)) {
                    rollComplete = true;
                    break;
                }
            }
            if (!rollComplete) {
                scanner.next();
            } else {
                break;
            }
        }
    }

    public void scriptscounterIncrement() {
        scriptcounter += 1;
    }

    public void scriptscounterDecrement() {
        scripts.remove(scriptcounter);
        scriptcounter -= 1;
        if (scriptcounter == -1) {
            ScriptsStack.removeAll(ScriptsStack);
        }
    }


    public Add getAdd() {
        return add;
    }

    public Exit getExit() {
        return exit;
    }

    public Help getHelp() {
        return help;
    }

    public PrintFieldDescendingMpaaRating getPrintFieldDescendingMpaaRating() {
        return printFieldDescendingMpaaRating;
    }

    public Clear getClear() {
        return clear;
    }

    public Head getHead(){
        return head;
    }

    public History getHistory(){
        return history;
    }

    public ExecuteScript getExecuteScript() {
        return executeScript;
    }

    public Info getInfo() {
        return info;
    }

    public RemoveById getRemoveById() {
        return removeById;
    }
    public RemoveFirst getRemoveFirst(){
        return removeFirst;
    }

    public UpdateById getUpdateById(){
        return updateById;
    }

    public PrintAscending getPrintAscending() {
        return printAscending;
    }

    public Show getShow() {
        return show;
    }

    public ArrayList<String> getScriptsStack() {
        return ScriptsStack;
    }

    public void setScriptsStack(ArrayList<String> scriptsStack) {
        ScriptsStack = scriptsStack;
    }

    public void addScriptsStack(String newScriptFile) {
        ScriptsStack.add(newScriptFile);
    }

    public boolean checkLoopTry(String newScriptFile) {
        for (String s : ScriptsStack) {
            if (s.equals(newScriptFile)) {
                return true;
            }
        }
        return false;
    }

    public void setScripts(ArrayList<Scanner> scripts) {
        this.scripts = scripts;
    }

    public RemoveAllByGoldenPalmCount getRemoveAllByGoldenPalmCount(){
        return removeAllByGoldenPalmCount;
    }



    public void setScriptcounter(int scriptcounter) {
        this.scriptcounter = scriptcounter;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }
}