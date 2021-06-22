package com.practice;

import com.practice.commands.Command;
import com.practice.commands.CommandFactory;
import com.practice.database.SQLiteDatabase;
import com.practice.stopwatch.Stopwatch;
import com.practice.stopwatch.StopwatchImpl;

import java.util.Scanner;

public class Server {

    public static void main(final String[] args) {
        final Server main = new Server();
        main.printTitle();
        main.run();
    }

    private void run() {
        final Scanner scanner = new Scanner(System.in);
        final CommandFactory commandFactory = getCommandFactory();

        while (true) {
            final String input = scanner.nextLine();
            final Command command = commandFactory.getCommand(input);
            command.run();
        }
    }

    private CommandFactory getCommandFactory() {
        final SQLiteDatabase sqLiteDatabase = SQLiteDatabase.getInstance();
        final Stopwatch stopwatch = new StopwatchImpl(sqLiteDatabase);
        return new CommandFactory(stopwatch);
    }

    private void printTitle() {
        System.out.println("\n______     _ _ _                    _        ______               _   _          \n" +
                "|  _  \\   | (_) |                  | |       | ___ \\             | | (_)         \n" +
                "| | | |___| |_| |__   ___ _ __ __ _| |_ ___  | |_/ / __ __ _  ___| |_ _  ___ ___ \n" +
                "| | | / _ \\ | | '_ \\ / _ \\ '__/ _` | __/ _ \\ |  __/ '__/ _` |/ __| __| |/ __/ _ \\\n" +
                "| |/ /  __/ | | |_) |  __/ | | (_| | ||  __/ | |  | | | (_| | (__| |_| | (_|  __/\n" +
                "|___/ \\___|_|_|_.__/ \\___|_|  \\__,_|\\__\\___| \\_|  |_|  \\__,_|\\___|\\__|_|\\___\\___|");
        System.out.println(" _____ _                  _____              _             \n" +
                "|_   _(_)                |_   _|            | |            \n" +
                "  | |  _ _ __ ___   ___    | |_ __ __ _  ___| | _____ _ __ \n" +
                "  | | | | '_ ` _ \\ / _ \\   | | '__/ _` |/ __| |/ / _ \\ '__|\n" +
                "  | | | | | | | | |  __/   | | | | (_| | (__|   <  __/ |   \n" +
                "  \\_/ |_|_| |_| |_|\\___|   \\_/_|  \\__,_|\\___|_|\\_\\___|_|\n");
        System.out.println("\nType a command and press Enter: \n");
    }
}
