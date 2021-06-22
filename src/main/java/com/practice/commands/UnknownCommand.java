package com.practice.commands;

public class UnknownCommand implements Command {

    String commandName;

    public UnknownCommand(final String commandName) {
        this.commandName = commandName;
    }

    @Override
    public void run() {
        System.out.println("Unknown command: " + this.commandName);
        System.out.println("Try \"help\" for further details\n");
    }
}
