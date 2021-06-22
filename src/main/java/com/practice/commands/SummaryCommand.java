package com.practice.commands;

import com.practice.stopwatch.Stopwatch;

public class SummaryCommand implements Command {

    Stopwatch stopwatch;

    public SummaryCommand(final Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {
        this.stopwatch
                .getDatabase()
                .generateSummary()
                .printSummary();
    }
}
