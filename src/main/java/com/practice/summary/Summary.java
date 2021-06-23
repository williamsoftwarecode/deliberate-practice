package com.practice.summary;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Summary {

    Map<String, Long> dateAndDuration = new TreeMap<>();

    public Summary() {
    }

    public void add(final String date, final long duration) {
        final Long totalDuration = this.dateAndDuration.get(date);
        if (!Objects.isNull(totalDuration)) {
            final Long newTotalDuration = totalDuration + duration;
            this.dateAndDuration.replace(date, newTotalDuration);
        } else {
            this.dateAndDuration.put(date, duration);
        }
    }

    public void printSummary() {
        this.dateAndDuration.forEach((date, duration) -> {
            final long minutes = duration / 60;
            final long seconds = duration % 60;
            System.out.println(date + " -> " + minutes + "m " + seconds + "s");
        });
        System.out.println();
    }
}
