package com.practice.commands;

public class HelpCommand implements Command {
    @Override
    public void run() {
        System.out.println("This CLI program was designed to track the time spent in deliberate practice.");
        System.out.println("It is written in Java and uses a SQLite database.");
        System.out.println("For ease of use, it is bundled into a standalone jar using johnrengelman's shadowjar plugin.");
        System.out.println("A SQLite database is automatically created if it does not exist.\n");

        System.out.println("The available commands are: ");
        for (final Commands commandName : Commands.values()) {
            System.out.println("-> " + commandName.getName());
            System.out.println("    " + commandName.getDescription());
        }
        System.out.println();
    }
}
