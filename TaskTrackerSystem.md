# Task tracker system
You asked to develop a POC of a task tracker system for some small business. Limitations of this POC are:
- console interface
- embedded or inmemory database (e.g. SQLite, H2)
- initial data loaded into db from files (csv, xml, json or any) during the system startup. Saving added data into those files on the finish is unnecessary
- it would be nice to add README with docs and tests

# Part 1
For the first prototype system should be able to:
- Create, show and delete users, projects and tasks
- Assign user on the project
- Assign task on the user
- Generate the report of all tasks created for specified Projects by specified User
Please remember that this system should be at least somewhat user-friendly. Any

# Part 2 (optional)
For the second version the system is updated with subtasks and remaining time for tasks and subtasks. Now in addition to the previous functionality it should be able to:
- Add any number of subtasks to any task or subtask
- Set and update remaining time for tasks and subtasks
- Show remaining time for task including subtasks
- Close the subtask if the remaining time for subtask is 0
- Close the task if the remaining time for task is 0 and all of its subtasks are closed, or their remaining time is 0
- Close the subtasks automatically if their root task is closed

## Common recommendations:
In case all parts weren’t completed it is still possible to send the task for check
Keep it simple. It is strongly not recommended to develop any features that are not specified in the task requirements.
Build tools are very useful. Do not hesitate to use one of them.
Do not upload your project to the public repos. Please upload it to a google disk and send a link

Task should be solved in 1 week since it was provided. Exceptions are possible but we should be notified in a timely manner.
