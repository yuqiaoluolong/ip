import java.util.Scanner;
import java.io.*;

public class Duke {
    public static String indentation = "    ";
    public static String horizontalLine = indentation + "<------------------------------------------------------------>\n";

    public static void printStatement(String statement){
        System.out.println(horizontalLine + indentation + "Here is yuqiaoluolong's Duke: \n" + statement + horizontalLine);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String Greet = indentation + " Hello! I'm Duke\n"
                + indentation + " What can I do for you?\n";
        String Bye = indentation + " Bye. Hope to see you again soon!\n";
        String inputCommand = " ";
        boolean isBye = false;

        printStatement("Hello from\n" + logo);
        printStatement(Greet);

        Scanner in = new Scanner(System.in);
        while(!isBye) {
            inputCommand = in.nextLine();
            if(inputCommand.trim().equals("bye")){                  //must use .equal() instead of ==
                printStatement(Bye);
                isBye = true;
                break;
            }
            printStatement(indentation + " " + inputCommand + "\n");
        }

    }
}
