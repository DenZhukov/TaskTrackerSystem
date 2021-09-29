package com.denzhukov.tasktrackersystem.console;

import com.denzhukov.tasktrackersystem.controller.ProjectController;
import com.denzhukov.tasktrackersystem.controller.TaskController;
import com.denzhukov.tasktrackersystem.controller.UserController;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.denzhukov.tasktrackersystem.console.Subject.*;

public class InputStart {
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    public InputStart(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
    }

    private final String PATH = "\\src\\main\\resources\\files\\";
    private final String FILE = "start.csv";

    public void start() {
        try (CSVReader reader = new CSVReader(new FileReader(getPath()))) {
            String[] record;
            while ((record = reader.readNext()) != null) {
                if (record[0].equalsIgnoreCase(USER.getSubject()))
                    userController.create(record[1], record[2]);

                if (record[0].equalsIgnoreCase(PROJECT.getSubject()))
                    projectController.create(record[1]);

                if (record[0].equalsIgnoreCase(TASK.getSubject()))
                    if(record.length == 4)
                    taskController.create(record[1],record[2].split(" ")[0],
                            record[2].split(" ")[1], record[3]);
                    if (record.length == 6) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                        try {
                            Date deadLine = formatter.parse(record[5]);
                            taskController.create(record[1], record[2].split(" ")[0],
                                    record[2].split(" ")[1], record[3], record[4], deadLine);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private String getPath() {
        Path currentAbsolutePath = Paths.get("").toAbsolutePath();
        return currentAbsolutePath + PATH + FILE;
    }


}
