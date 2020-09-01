import java.util.Scanner;

public class Duke {
    public static String indentation = "    ";
    public static String secondIndentation = "  ";
    public static String horizontalLine = indentation + "<------------------------------------------------------------>\n";
    public static Task[] Tasks = new Task[100];
    public static int numberOfTasks = 0;

    public static void printStatement(String statement){
        System.out.println(horizontalLine + indentation + "Here is yuqiaoluolong's Duke: \n" + statement + horizontalLine);
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

        printStatement("Hello from\n" + logo);
        printStatement(greet);

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            inputCommand = in.nextLine();
            switch (inputCommand.trim()) {
            case "list":
                System.out.print(horizontalLine);
                System.out.println(indentation + "Here is yuqiaoluolong's Duke: ");
                for(int i = 0; i < numberOfTasks; i++){
                    System.out.println(indentation + secondIndentation + (i+1) + ".["
                            + Tasks[i].getStatusIcon() + "] " + Tasks[i].description);
                }
                System.out.println(horizontalLine);
                break;
            case "bye":
                printStatement(bye);
                isBye = true;
                break;
            default:
                if(inputCommand.contains("done")){
                    doneNum = Integer.parseInt(inputCommand.replace("done", " ").trim());
                    Tasks[doneNum-1].markAsDone();
                    printStatement(indentation + secondIndentation + "Nice! I've marked this task as done: \n"
                            + indentation + secondIndentation + secondIndentation + " ["
                            + Tasks[doneNum-1].getStatusIcon() + "] " + Tasks[doneNum-1].description + "\n");
                    break;
                }
                Tasks[numberOfTasks] = new Task(inputCommand.trim());
                numberOfTasks++;
                printStatement(indentation + secondIndentation + "added: " + inputCommand + "\n");
            }
        }

    }
}
