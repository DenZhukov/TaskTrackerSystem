package com.denzhukov.tasktrackersystem.console;

import com.denzhukov.tasktrackersystem.command.CommandContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.denzhukov.tasktrackersystem.console.Messages.WELCOME;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CommandContainer commandContainer;

    @Autowired
    public ConsoleRunner(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    @Override
    public void run(String... args) {
        System.out.println(WELCOME.getMessage());

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = "";
            while (true) {
                command = reader.readLine();
                if (command.equals("exit")) System.exit(0);
                commandContainer.findCommand(command.split(" ")[0]).execute(command);
            }
        }catch(IOException e){
                e.printStackTrace();
        }
    }
}
