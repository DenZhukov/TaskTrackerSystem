# POC Task Tracker System

## How does it start
To run script "mvn clean compile exec:java"
During the system startup the application load data from "start.csv". And check task, if
some tasks have a deadline, the system will delete them. 
And use command "help" - it will help.

## How it works
Tracker has 3 subject - User, Project, Task.
### User
User can be assigned on several projects and tasks. 
### Project
Project can have executors(users) and tasks
### Task
Task must have executor(user) and project. Task can have subtasks, or can be subtask.
User can set deadline for a task.

## Technological stack
- SpringBoot
- H2 database
- Maven
