package com.practice.commands;

import com.practice.stopwatch.Stopwatch;

import static com.practice.commands.Commands.*;

public class CommandFactory {

    Stopwatch stopwatch;

    public CommandFactory(final Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    public Command getCommand(final String inputFromConsole) {
        final String input = inputFromConsole.trim();

        if (input.equals(START_STOPWATCH.getName())) {
            return new StartCommand(this.stopwatch);
        } else if (input.equals(STOP_STOPWATCH.getName())) {
            return new StopCommand(this.stopwatch);
        } else if (input.equals(SUMMARY.getName())) {
            return new SummaryCommand(this.stopwatch);
        } else if (input.equals(HELP.getName())) {
            return new HelpCommand();
        } else if (input.equals(EXIT.getName())) {
            return new ExitCommand();
        } else {
            return new UnknownCommand(input);
        }
    }
}
