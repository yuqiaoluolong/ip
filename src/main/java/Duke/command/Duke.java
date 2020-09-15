package Duke.command;

import Duke.task.*;

import java.util.Scanner;

public class Duke {
    public static final int MAX_NUM_TASKS = 100;
    public static final String INDENTATION = "    ";
    public static final String DOUBLEINDENTATION = INDENTATION + "  ";
    public static final String TRIPLEINDENTATION = DOUBLEINDENTATION + "  ";
    public static final String HORIZONTALLINE = INDENTATION
            + "<------------------------------------------------------------>\n";
    public static Task[] tasks = new Task[MAX_NUM_TASKS];
    public static int numberOfTasks = 0;

    public static class TodoNullException extends Exception {
        //no other code needed
    }

    public static void printStatement(String statement) {
        System.out.println(HORIZONTALLINE + INDENTATION + "Here is yuqiaoluolong's Duke: \n"
                + statement + HORIZONTALLINE);
    }
    public static int getDoneNum(String inputCommand) {
        return Integer.parseInt(inputCommand.replace("done", " ").trim());
    }
    public static String getDescriptiong(String index, String inputCommand) {
        if(index == "deadline") {
            return inputCommand.substring(inputCommand.indexOf("deadline")+8, inputCommand.indexOf("/")).trim();
        } else {
            return inputCommand.substring(inputCommand.indexOf("event")+5, inputCommand.indexOf("/")).trim();
        }
    }
    public static void executeListCommand(String inputcommanc) {
        System.out.print(HORIZONTALLINE);
        System.out.println(INDENTATION + "Here is yuqiaoluolong's Duke: \n" +
                "      Here are the tasks in your list:");
        for(int i = 0; i < numberOfTasks; i++){
            System.out.println(DOUBLEINDENTATION + (i+1) + "." + tasks[i].toString());
        }
        System.out.println(HORIZONTALLINE);
    }
    public static void executeDoneCommand(String inputCommand) {
        int doneNum = getDoneNum(inputCommand);
        try {
            tasks[doneNum - 1].markAsDone();
            printStatement(DOUBLEINDENTATION + "Nice! I've marked this task as done: \n"
                    + TRIPLEINDENTATION + tasks[doneNum - 1].toString() + "\n");
        } catch (NullPointerException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! There is no task with such an index.\n");
        }       //catch the command "done x" and x is out of the doundary of the task list
    }
    public static void executeTodoCommand(Task[] tasks, int numberOfTasks, String inputCommand) throws TodoNullException{
        tasks[numberOfTasks] = new Todo(inputCommand.substring(inputCommand.indexOf("todo")+4).trim());
        if(tasks[numberOfTasks].description.length() == 0){
            throw new TodoNullException();
        }
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks + 1) + " tasks in the list.\n");
    }
    public static boolean executeDeadlineCommand(Task[] tasks, int numberOfTasks, String inputCommand) {
        try {
            String description = getDescriptiong("deadline", inputCommand);
            String date = inputCommand.substring(inputCommand.indexOf("/by") + 3).trim();
            tasks[numberOfTasks] = new Deadline(description, date);
        } catch(StringIndexOutOfBoundsException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The description of a deadline cannot be empty.\n");
            return true;
        }       //catch the empty deadline command exception
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
        return false;
    }
    public static boolean executeEventCommand(Task[] tasks, int numberOfTasks, String inputCommand) {
        try {
            String description = getDescriptiong("event", inputCommand);
            String date = inputCommand.substring(inputCommand.indexOf("/at") + 3).trim();
            tasks[numberOfTasks] = new Event(description, date);
        } catch (StringIndexOutOfBoundsException e){
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The description of an event cannot be empty.\n");
            return true;
        }       //catch the empty event command exception
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
        return false;
    }

    public static void main(String[] args) {
        final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String GREET = INDENTATION + " Hello! I'm Duke\n"
                + INDENTATION + " What can I do for you?\n";
        final String BYE = INDENTATION + " Bye. Hope to see you again soon!\n";
        final String WRONGMESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        boolean isNewTask;
        String inputCommand;
        boolean isBye = false;
        int doneNum;
        String outputStatement;
        String description;
        String date;
        boolean isCommandEmpty;

        printStatement("Hello from\n" + LOGO);          // greet in the beginning
        printStatement(GREET);

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            inputCommand = in.nextLine();
            isNewTask = inputCommand.contains("todo") || inputCommand.contains("deadline")
                    || inputCommand.contains("event");
            switch (inputCommand.trim()) {
            case "list":
                //try {
                    executeListCommand(inputCommand);
                /*} catch (NullPointerException e) {
                    numberOfTasks--;
                }*/
                break;
            case "bye":
                printStatement(BYE);
                isBye = true;
                break;
            default:
                if (inputCommand.contains("done")) {
                    try {
                        executeDoneCommand(inputCommand);
                    } catch (NumberFormatException e) {     //catch the cammand "done" whithout any number
                        printStatement(DOUBLEINDENTATION + "☹ OOPS!!! You did not indicate which task is done.\n");
                    } catch (ArrayIndexOutOfBoundsException e) {      //catch commands like "done555"
                        printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The index is out of the list boundary.\n");
                    }
                } else if (isNewTask) {
                    if (inputCommand.contains("todo")) {
                        try {
                            executeTodoCommand(tasks, numberOfTasks, inputCommand);
                        } catch (TodoNullException e){      //catch the empty todo command exception
                            printStatement(DOUBLEINDENTATION +
                                    "☹ OOPS!!! The description of a todo cannot be empty.\n");
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                    } else if (inputCommand.contains("deadline")) {
                        isCommandEmpty = executeDeadlineCommand(tasks, numberOfTasks, inputCommand);
                        if(isCommandEmpty){
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                    } else if (inputCommand.contains("event")) {
                        isCommandEmpty = executeEventCommand(tasks, numberOfTasks, inputCommand);
                        if(isCommandEmpty){
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                    }
                    numberOfTasks++;
                } else {
                    printStatement(DOUBLEINDENTATION + WRONGMESSAGE);
                }
            }
        }

    }
}