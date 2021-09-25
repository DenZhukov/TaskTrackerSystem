package com.denzhukov.tasktrackersystem.command;

public class ExitCommand implements Command {

    @Override
    public void execute(String command) {
        flag();
    }

    public boolean flag() {
        return false;
    }
}
