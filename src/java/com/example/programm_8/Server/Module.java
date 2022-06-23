package com.example.programm_8.Server;

import com.example.programm_8.Utility.CollectionManager;
import com.example.programm_8.Commands.*;

import java.util.Scanner;

public class Module {

    private static CollectionManager collectionManager;
    private static String outputMessage = "";

    private static final String[] commands = {"add", "clear", "execute_script", "exit", "head", "history", "info",
            "print_ascending", "print_field_descending_mpaa_rating", "remove_all_by_golden_palm_count", "remove_by_id",
            "remove_first", "show", "update", "help", "connect", "getTable"};


    public static boolean running(AbstractCommand command) {
        String actualCommand = command.getName();
        Scanner scanner = new Scanner(actualCommand);
        scanner.useDelimiter("\\s");
        actualCommand = scanner.next();
        switch (chooseCommand(actualCommand)) {
            case (0): {
                Add add = (Add) command;
                add.setCollectionManager(collectionManager);
                return add.exec();
            }
            case (1): {
                Clear clear = (Clear) command;
                clear.setCollectionManager(collectionManager);
                return clear.exec();
            }
            case (2): {
                //pass execute_script
                break;
            }
            case (3): {
                //pass exit
            }
            case (4): {
                Head head = (Head) command;
                head.setCollectionManager(collectionManager);
                return head.exec();
            }
            case (5): {
                //pass history
                break;
            }
            case (6): {
                Info info = (Info) command;
                info.setCollectionManager(collectionManager);
                return info.exec();
            }
            case (7): {
                PrintAscending printAscending = (PrintAscending) command;
                printAscending.setCollectionManager(collectionManager);
                return printAscending.exec();
            }
            case (8): {
                PrintFieldDescendingMpaaRating printFieldDescendingMpaaRating = (PrintFieldDescendingMpaaRating) command;
                printFieldDescendingMpaaRating.setCollectionManager(collectionManager);
                return printFieldDescendingMpaaRating.exec();
            }
            case (9): {
                RemoveAllByGoldenPalmCount removeAllByGoldenPalmCount = (RemoveAllByGoldenPalmCount) command;
                removeAllByGoldenPalmCount.setCollectionManager(collectionManager);
                return removeAllByGoldenPalmCount.exec();
            }
            case (10): {
                RemoveById removeById = (RemoveById) command;
                removeById.setCollectionManager(collectionManager);
                return removeById.exec();
            }
            case (11): {
                RemoveFirst removeFirst = (RemoveFirst) command;
                removeFirst.setCollectionManager(collectionManager);
                return removeFirst.exec();
            }
            case (12): {
                Show show = (Show) command;
                show.setCollectionManager(collectionManager);
                return show.exec();
            }
            case (13): {
                UpdateById updateById = (UpdateById) command;
                updateById.setCollectionManager(collectionManager);
                return updateById.exec();
            }
            case (14): {
                //pass help
                break;
            }
            case (15): {
                Connect connect = (Connect) command;
                connect.setCollectionManager(collectionManager);
                return connect.exec();
            }
            case (16): {
                getTable getTable = (getTable) command;
                getTable.setCollectionManager(collectionManager);
                return getTable.exec();
            }
            case (-1): {
                System.out.println("Команда не распознана.");
                break;
            }
        }
        return false;
    }

    public static String messageFlush() {
        String output = Module.outputMessage;
        Module.outputMessage = "";
        return output;
    }

    public static void addMessage(String s) {
        outputMessage += s + "\n";
    }

    private static int chooseCommand(String command) {
        for (int i = 0; i < commands.length; i++) {
            if (command.equals(commands[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void setCollectionManager(CollectionManager collectionManager) {
        Module.collectionManager = collectionManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static String getMessage() {
        return outputMessage;
    }
}
