package Duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String LS = System.lineSeparator();
    public static final int MAX_NUM_TASKS = 100;
    public static final String INDENTATION = "    ";
    public static final String DOUBLEINDENTATION = INDENTATION + "  ";
    public static final String TRIPLEINDENTATION = DOUBLEINDENTATION + "  ";
    public static final String HORIZONTALLINE = INDENTATION
            + "<------------------------------------------------------------>\n";
    public final String LOGO = TRIPLEINDENTATION + " ____        _        \n"
            + TRIPLEINDENTATION + "|  _ \\ _   _| | _____ \n"
            + TRIPLEINDENTATION + "| | | | | | | |/ / _ \\\n"
            + TRIPLEINDENTATION + "| |_| | |_| |   <  __/\n"
            + TRIPLEINDENTATION + "|____/ \\__,_|_|\\_\\___|\n";
    public final String GREET = INDENTATION + " Hello! I'm Duke\n"
            + INDENTATION + " What can I do for you?\n";
    public final String BYE = INDENTATION + " Bye. Hope to see you again soon!\n";
    public final String WRONGMESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";

    private final Scanner in;
    private final PrintStream out;
    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static void printStatement(String statement) {
        System.out.println(HORIZONTALLINE + INDENTATION + "Here is yuqiaoluolong's Duke: \n"
                + statement + HORIZONTALLINE);
    }

    public void showWelcome() {
        printStatement(TRIPLEINDENTATION + "Hello from\n" + LOGO);
        printStatement(GREET);
    }

    public String readCommand(){
        String input = in.nextLine();
        return input;
    }

    public void showLine(){
        System.out.printf(LS);
    }

}
