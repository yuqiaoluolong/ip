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
        String inputCommand;
        boolean isBye = false;
        int doneNum;
        String respond = " ";

        printStatement("Hello from\n" + logo);
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
                    respond = doubleIndentation + "Nice! I've marked this task as done: \n"
                            + tripleIndentation + Tasks[doneNum-1].toString() + "\n";
                } else if (inputCommand.contains("todo")||inputCommand.contains("deadline")
                        ||inputCommand.contains("event")){
                    if (inputCommand.contains("todo")) {
                        Tasks[numberOfTasks] = new Todo(inputCommand.replace("todo"," ").trim());
                    } else if (inputCommand.contains("deadline")) {
                        Tasks[numberOfTasks] = new Deadline(inputCommand.substring(inputCommand.indexOf("deadline")+8,
                                inputCommand.indexOf("/")).trim(),
                                inputCommand.substring(inputCommand.indexOf("/by")+3).trim());

                    } else if (inputCommand.contains("event")) {
                        Tasks[numberOfTasks] = new Event(inputCommand.substring(inputCommand.indexOf("event")+5,
                                inputCommand.indexOf("/")).trim(),
                                inputCommand.substring(inputCommand.indexOf("/at")+3).trim());
                    }
                    respond = doubleIndentation + "Got it. I've added this task:\n"
                            + tripleIndentation + Tasks[numberOfTasks].toString() + "\n" + doubleIndentation
                            + "Now you have " + (numberOfTasks+1) + " tasks in the list.\n";
                    numberOfTasks++;
                } else {
                    respond = doubleIndentation + "Sorry, I don't know what's your command. Can you type again?\n";
                }
                printStatement(respond);
                /*
                Tasks[numberOfTasks] = new Task(inputCommand.trim());
                numberOfTasks++;
                printStatement(doubleIndentation + "added: " + inputCommand + "\n");*/
            }
        }

    }
}
