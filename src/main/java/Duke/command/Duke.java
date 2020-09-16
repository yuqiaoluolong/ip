package Duke.command;

import Duke.task.*;
import java.util.Scanner;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.io.FileWriter;
import java.io.IOException;
>>>>>>> branch-Level-7

public class Duke {
    public static final int MAX_NUM_TASKS = 100;
    public static final String INDENTATION = "    ";
    public static final String DOUBLEINDENTATION = INDENTATION + "  ";
    public static final String TRIPLEINDENTATION = DOUBLEINDENTATION + "  ";
    public static final String HORIZONTALLINE = INDENTATION
            + "<------------------------------------------------------------>\n";
    private static ArrayList<Task> tasks = new ArrayList<>();
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
    public static int getDeleteNum(String inputCommand) {
        return Integer.parseInt(inputCommand.replace("delete", " ").trim());
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
            System.out.println(DOUBLEINDENTATION + (i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTALLINE);
    }
    public static void executeDoneCommand(String inputCommand) {
        int doneNum = getDoneNum(inputCommand);
        try {
            tasks.get(doneNum - 1).markAsDone();
            printStatement(DOUBLEINDENTATION + "Nice! I've marked this task as done: \n"
                    + TRIPLEINDENTATION + tasks.get(doneNum - 1).toString() + "\n");
        } catch (NullPointerException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! There is no task with such an index.\n");
        }       //catch the command "done x" and x is out of the doundary of the task list
    }
    public static void executeDeleteCommand(String inputCommand) {
        int deleteNum = getDeleteNum(inputCommand);
        printStatement(DOUBLEINDENTATION + "Noted. I've removed this task: \n" +
                TRIPLEINDENTATION + tasks.get(deleteNum-1).toString() + "\n" +
                DOUBLEINDENTATION + "Now you have " + (numberOfTasks-1) + " tasks in the list.\n");
        tasks.remove(deleteNum-1);
        numberOfTasks--;
    }

    public static void executeTodoCommand(ArrayList<Task> tasks, int numberOfTasks, String inputCommand) throws TodoNullException{
        tasks.add(numberOfTasks, new Todo(inputCommand.substring(inputCommand.indexOf("todo") + 4).trim()));
        if(tasks.get(numberOfTasks).description.length() == 0){
            throw new TodoNullException();
        }
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks + 1) + " tasks in the list.\n");
    }
    public static boolean executeDeadlineCommand(ArrayList<Task> tasks, int numberOfTasks, String inputCommand) {
        try {
            String description = getDescriptiong("deadline", inputCommand);
            String date = inputCommand.substring(inputCommand.indexOf("/by") + 3).trim();
            tasks.add(numberOfTasks, new Deadline(description, date));
        } catch (StringIndexOutOfBoundsException e) {    //catch the empty deadline command exception
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! There is nothing following \"deadline\".\n");
            return true;
        }
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
        return false;
    }
    public static boolean executeEventCommand(ArrayList<Task> tasks, int numberOfTasks, String inputCommand) {
        try {
            String description = getDescriptiong("event", inputCommand);
            String date = inputCommand.substring(inputCommand.indexOf("/at") + 3).trim();
            tasks.add(numberOfTasks, new Event(description, date));
        } catch (StringIndexOutOfBoundsException e){
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The description of an event cannot be empty.\n");
            return true;
        }       //catch the empty event command exception
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
        return false;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void write(int numberOfTasks, String file) {
        String textToAdd = "";
        for(int i = 0; i<numberOfTasks; i++) {
            textToAdd += tasks[i].toString() + System.lineSeparator();
        }
        try {
            writeToFile(file, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
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
        String file = "data/duke.txt";

        printStatement("Hello from\n" + LOGO);          // greet in the beginning
        printStatement(GREET);

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            inputCommand = in.nextLine();
            isNewTask = inputCommand.contains("todo") || inputCommand.contains("deadline")
                    || inputCommand.contains("event");
            switch (inputCommand.trim()) {
            case "list":
                executeListCommand(inputCommand);
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
<<<<<<< HEAD
                } else if (inputCommand.contains("delete")) {
                    executeDeleteCommand(inputCommand);
=======
                    write(numberOfTasks, file);
>>>>>>> branch-Level-7
                } else if (isNewTask) {
                    if (inputCommand.contains("todo")) {
                        try {
                            executeTodoCommand(tasks, numberOfTasks, inputCommand);
                        } catch (TodoNullException e){      //catch the empty todo command exception
                            printStatement(DOUBLEINDENTATION +
                                    "☹ OOPS!!! The description of a todo cannot be empty.\n");
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                        write(numberOfTasks+1, file);
                    } else if (inputCommand.contains("deadline")) {
                        isCommandEmpty = executeDeadlineCommand(tasks, numberOfTasks, inputCommand);
                        if(isCommandEmpty){
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                        write(numberOfTasks+1, file);
                    } else if (inputCommand.contains("event")) {
                        isCommandEmpty = executeEventCommand(tasks, numberOfTasks, inputCommand);
                        if(isCommandEmpty){
                            numberOfTasks--;    //to deal with "numberOfTasks++ below
                        }
                        write(numberOfTasks+1, file);
                    }
                    numberOfTasks++;
                } else {
                    printStatement(DOUBLEINDENTATION + WRONGMESSAGE);
                }
            }
        }

    }
}