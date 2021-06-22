package com.practice.commands;

public enum Commands {
    START_STOPWATCH("start", "Starts the stopwatch"),
    STOP_STOPWATCH("stop", "Stops the stopwatch and stores the elapsed time and date in a SQLite database"),
    SUMMARY("summary", "Prints a summary of the dates and durations to the console"),
    HELP("help", "Prints the available commands of the program and its descriptions"),
    EXIT("exit", "Exits the program");

    private final String commandLineInput;
    private final String description;

    Commands(final String commandLineInput, final String description) {
        this.commandLineInput = commandLineInput;
        this.description = description;
    }

    public String getName() {
        return this.commandLineInput;
    }

    public String getDescription() {
        return this.description;
    }
}
