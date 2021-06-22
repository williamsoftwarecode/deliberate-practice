package com.practice.commands;

import com.practice.stopwatch.Stopwatch;

public class StartCommand implements Command {

    Stopwatch stopwatch;

    public StartCommand(final Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {
        this.stopwatch.start();
    }
}
