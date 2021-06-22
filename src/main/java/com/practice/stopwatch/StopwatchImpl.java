package com.practice.stopwatch;

import com.practice.database.StopwatchDatabase;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

public class StopwatchImpl implements Stopwatch {

    StopwatchDatabase stopwatchDatabase;
    Instant startTime;
    Instant stopTime;

    public StopwatchImpl(final StopwatchDatabase stopwatchDatabase) {
        this.stopwatchDatabase = stopwatchDatabase;
    }

    @Override
    public void start() {
        if (!stopwatchIsStarted()) {
            this.startTime = Instant.now();
            System.out.println("Start time: " + getLocalTime(this.startTime) + "\n");
        } else {
            System.out.println("Stopwatch is already running\n");
        }
    }

    @Override
    public void stop() {
        if (stopwatchIsStarted()) {
            this.stopTime = Instant.now();
            System.out.println("Stop time: " + getLocalTime(this.stopTime));
            System.out.println("Time elapsed: " + getElapsedTime(this.startTime, this.stopTime).getSeconds() + " seconds \n");
            this.stopwatchDatabase.insert(this.startTime, this.stopTime);
            resetStopwatch();
        } else {
            System.out.println("Stopwatch has not been started\n");
        }
    }

    @Override
    public StopwatchDatabase getDatabase() {
        return this.stopwatchDatabase;
    }

    private Duration getElapsedTime(final Instant start, final Instant end) {
        return Duration.between(start, end);
    }

    private LocalTime getLocalTime(final Instant instant) {
        return LocalTime.from(instant.atZone(ZoneId.of("GMT+8")));
    }

    private void resetStopwatch() {
        this.startTime = null;
        this.stopTime = null;
    }

    private boolean stopwatchIsStarted() {
        return !Objects.isNull(this.startTime);
    }
}
