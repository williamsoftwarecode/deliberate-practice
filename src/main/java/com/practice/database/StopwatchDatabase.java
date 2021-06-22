package com.practice.database;

import com.practice.summary.Summary;

import java.time.Instant;

public interface StopwatchDatabase {
    void createTable();
    void insert(Instant start, Instant end);
    void select();
    Summary generateSummary();
}
