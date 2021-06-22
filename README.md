# deliberate-practice

This CLI program was designed to track the time spent in deliberate practice.
It is written in Java and uses a SQLite database.
For ease of use, it is bundled into a standalone jar using johnrengelman's shadowjar plugin.
A SQLite database is automatically created if it does not exist.

The available commands are:
-> start
    Starts the stopwatch
-> stop
    Stops the stopwatch and stores the elapsed time and date in a SQLite database
-> summary
    Prints a summary of the dates and durations to the console
-> help
    Prints the available commands of the program and its descriptions
-> exit
    Exits the program
