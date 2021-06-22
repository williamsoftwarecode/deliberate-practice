package com.practice.stopwatch;

import com.practice.database.StopwatchDatabase;

public interface Stopwatch {
    void start();
    void stop();
    StopwatchDatabase getDatabase();
}
