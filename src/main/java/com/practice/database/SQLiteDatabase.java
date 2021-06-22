package com.practice.database;

import com.practice.summary.Summary;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

// Singleton
// For reference, https://www.tutorialspoint.com/sqlite/sqlite_java.htm
public class SQLiteDatabase implements StopwatchDatabase {

    private static SQLiteDatabase sqliteDbInstance = null;
    private static final String repositoryName = "deliberate-practice.db";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private SQLiteDatabase() {
    }

    public static SQLiteDatabase getInstance()
    {
        if (sqliteDbInstance == null) {
            sqliteDbInstance = new SQLiteDatabase();
            sqliteDbInstance.createTable();
        }

        return sqliteDbInstance;
    }

    @Override
    public void createTable() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + repositoryName);

            stmt = c.createStatement();
            final String sql = "CREATE TABLE IF NOT EXISTS \"PRACTICE\" (\n" +
                    "\"id\"             INTEGER NOT NULL UNIQUE,\n" +
                    "\"date\"           TEXT NOT NULL,\n" +
                    "\"elapsed_time\"   INTEGER NOT NULL,\n" +
                    "\"start_time\"     INTEGER NOT NULL,\n" +
                    "\"stop_time\"      INTEGER NOT NULL,\n" +
                    "PRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                    ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (final Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void insert(final Instant start, final Instant stop) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + repositoryName);
            c.setAutoCommit(false);

            final Duration elapsedTime = Duration.between(start, stop);
            final Timestamp startTimestamp = Timestamp.from(start);
            final Timestamp stopTimestamp = Timestamp.from(stop);

            pstmt = c.prepareStatement(
                    "INSERT INTO PRACTICE (DATE,ELAPSED_TIME,START_TIME,STOP_TIME) " +
                    "VALUES (?, ?, ?, ?);");
            pstmt.setString(1, sdf.format(startTimestamp));
            pstmt.setLong(2, elapsedTime.getSeconds());
            pstmt.setTimestamp(3, startTimestamp);
            pstmt.setTimestamp(4, stopTimestamp);
            pstmt.executeUpdate();

            pstmt.close();
            c.commit();
            c.close();
        } catch (final Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void select() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + repositoryName);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            final ResultSet rs = stmt.executeQuery( "SELECT * FROM PRACTICE;" );

            while ( rs.next() ) {
                final int id = rs.getInt("id");
                final String date = rs.getString("date");
                final long elapsedTime = rs.getLong("elapsed_time");
                final long startTime  = rs.getLong("start_time");
                final long stopTime = rs.getLong("stop_time");

                final Instant start = Instant.ofEpochMilli(startTime);
                final Instant stop = Instant.ofEpochMilli(stopTime);

                System.out.println("ID = " + id);
                System.out.println("Date = " + date);
                System.out.println("Elapsed Time = " + elapsedTime);
                System.out.println("Start Time = " + start.atZone(ZoneId.of("GMT+8")));
                System.out.println("Stop Time = " + stop.atZone(ZoneId.of("GMT+8")));
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (final Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public Summary generateSummary() {
        final Summary summary = new Summary();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + repositoryName);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            final ResultSet rs = stmt.executeQuery( "SELECT * FROM PRACTICE;" );

            while ( rs.next() ) {
                final String date = rs.getString("date");
                final long elapsedTime = rs.getLong("elapsed_time");

                summary.add(date, elapsedTime);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (final Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return summary;
    }
}