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
        tasks[doneNum-1].markAsDone();
        printStatement(DOUBLEINDENTATION + "Nice! I've marked this task as done: \n"
                + TRIPLEINDENTATION + tasks[doneNum-1].toString() + "\n");
    }
    public static void executeTodoCommand(Task[] tasks, int numberOfTasks, String inputCommand) {
        tasks[numberOfTasks] = new Todo(inputCommand.replace("todo"," ").trim());
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
    }
    public static void executeDeadlineCommand(Task[] tasks, int numberOfTasks, String inputCommand) {
        String description = getDescriptiong("deadline", inputCommand);
        String date = inputCommand.substring(inputCommand.indexOf("/by")+3).trim();
        tasks[numberOfTasks] = new Deadline(description, date);
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
    }
    public static void executeEventCommand(Task[] tasks, int numberOfTasks, String inputCommand) {
        String description = getDescriptiong("event", inputCommand);
        String date = inputCommand.substring(inputCommand.indexOf("/at")+3).trim();
        tasks[numberOfTasks] = new Event(description, date);
        printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks[numberOfTasks].toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n");
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
        final String WRONGMESSAGE = "Sorry, I don't know what's your command. Can you type again?\n";
        boolean isNewTask;
        String inputCommand;
        boolean isBye = false;
        int doneNum;
        String outputStatement;
        String description;
        String date;

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
                    executeDoneCommand(inputCommand);
                } else if (isNewTask) {
                    if (inputCommand.contains("todo")) {
                        executeTodoCommand(tasks, numberOfTasks, inputCommand);
                    } else if (inputCommand.contains("deadline")) {
                        executeDeadlineCommand(tasks, numberOfTasks, inputCommand);

                    } else if (inputCommand.contains("event")) {
                        executeEventCommand(tasks, numberOfTasks, inputCommand);
                    }
                    numberOfTasks++;
                } else {
                    printStatement(DOUBLEINDENTATION + WRONGMESSAGE);
                }
            }
        }

    }
}
