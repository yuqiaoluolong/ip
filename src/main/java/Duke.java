import java.util.Scanner;

public class Duke {
    public static String indentation = "    ";
    public static String doubleIndentation = indentation + "  ";
    public static String tripleIndentation = doubleIndentation + "  ";
    public static String horizontalLine = indentation
            + "<------------------------------------------------------------>\n";
    public static Task[] Tasks = new Task[100];
    public static int numberOfTasks = 0;

    public static void printStatement(String statement){
        System.out.println(horizontalLine + indentation + "Here is yuqiaoluolong's Duke: \n"
                + statement + horizontalLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = indentation + " Hello! I'm Duke\n"
                + indentation + " What can I do for you?\n";
        String bye = indentation + " Bye. Hope to see you again soon!\n";
        String wrongWarning = "Sorry, I don't know what's your command. Can you type again?\n";
        String inputCommand;
        boolean isBye = false;
        int doneNum;
        String outputStatement;
        String description;
        String date;

        printStatement("Hello from\n" + logo);          // greet in the beginning
        printStatement(greet);

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            inputCommand = in.nextLine();
            switch (inputCommand.trim()) {
            case "list":
                System.out.print(horizontalLine);
                System.out.println(indentation + "Here is yuqiaoluolong's Duke: \n" +
                        "      Here are the tasks in your list:");
                for(int i = 0; i < numberOfTasks; i++){
                    System.out.println(doubleIndentation + (i+1) + "." + Tasks[i].toString());
                }
                System.out.println(horizontalLine);
                break;
            case "bye":
                printStatement(bye);
                isBye = true;
                break;
            default:
                if (inputCommand.contains("done")) {
                    doneNum = Integer.parseInt(inputCommand.replace("done", " ").trim());
                    Tasks[doneNum-1].markAsDone();
                    outputStatement = doubleIndentation + "Nice! I've marked this task as done: \n"
                            + tripleIndentation + Tasks[doneNum-1].toString() + "\n";
                } else if (inputCommand.contains("todo")||inputCommand.contains("deadline")
                        ||inputCommand.contains("event")){
                    if (inputCommand.contains("todo")) {
                        Tasks[numberOfTasks] = new Todo(inputCommand.replace("todo"," ").trim());
                    } else if (inputCommand.contains("deadline")) {
                        description = inputCommand.substring(inputCommand.indexOf("deadline")+8,
                                inputCommand.indexOf("/")).trim();
                        date = inputCommand.substring(inputCommand.indexOf("/by")+3).trim();
                        Tasks[numberOfTasks] = new Deadline(description, date);

                    } else if (inputCommand.contains("event")) {
                        description = inputCommand.substring(inputCommand.indexOf("event")+5,
                                inputCommand.indexOf("/")).trim();
                        date = inputCommand.substring(inputCommand.indexOf("/at")+3).trim();
                        Tasks[numberOfTasks] = new Event(description, date);
                    }
                    outputStatement = doubleIndentation + "Got it. I've added this task:\n"
                            + tripleIndentation + Tasks[numberOfTasks].toString() + "\n" + doubleIndentation
                            + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n";
                    numberOfTasks++;
                } else {
                    outputStatement = doubleIndentation + wrongWarning;
                }
                printStatement(outputStatement);
                /*
                Tasks[numberOfTasks] = new Task(inputCommand.trim());
                numberOfTasks++;
                printStatement(doubleIndentation + "added: " + inputCommand + "\n");*/
            }
        }

    }
}
