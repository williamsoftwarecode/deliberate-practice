package com.practice.commands;

import com.practice.stopwatch.Stopwatch;

public class StopCommand implements Command {

    Stopwatch stopwatch;

    public StopCommand(final Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {
        this.stopwatch.stop();
    }
}
