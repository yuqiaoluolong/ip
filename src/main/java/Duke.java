import java.util.Scanner;

public class Duke {
    public static String indentation = "    ";
    public static String secondIndentation = "  ";
    public static String horizontalLine = indentation + "<------------------------------------------------------------>\n";
    public static String[] Lists = new String[100];
    public static int numberOfList = 0;

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
        String inputCommand;
        boolean isBye = false;

        printStatement("Hello from\n" + logo);
        printStatement(Greet);

        Scanner in = new Scanner(System.in);
        while(!isBye) {
            inputCommand = in.nextLine();
            switch (inputCommand.trim()){
                case "bye":
                    printStatement(Bye);
                    isBye = true;
                    break;
                case "list":
                    System.out.print(horizontalLine);
                    System.out.println(indentation + "Here is yuqiaoluolong's Duke: ");
                    for(int i = 0; i < numberOfList; i++){
                        System.out.println(indentation + secondIndentation + (i+1) + ". " + Lists[i]);
                    }
                    System.out.println(horizontalLine);
                    break;
                default:
                    Lists[numberOfList] = inputCommand.trim();
                    numberOfList++;
                    printStatement(indentation + secondIndentation + "added: " + inputCommand + "\n");
            }/*
            if(inputCommand.trim().equals("bye")){                  //must use .equal() instead of ==
                printStatement(Bye);
                isBye = true;
                break;
            }
            printStatement(indentation + " added: " + inputCommand + "\n");*/
        }

    }
}
