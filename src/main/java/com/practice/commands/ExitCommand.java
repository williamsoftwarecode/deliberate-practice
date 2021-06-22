package com.practice.commands;

import com.practice.commands.Command;

public class ExitCommand implements Command {
    @Override
    public void run() {
        System.exit(0);
    }
}
