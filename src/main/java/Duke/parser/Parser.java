package Duke.parser;

import Duke.command.*;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.printStatement;

public class Parser {

    /*public static Command parse(String fullCommand) {
        if(fullCommand.contains("todo")){
            return new Command(fullCommand.substring(fullCommand.indexOf("Todo")+4).trim());
        } else if(fullCommand.contains("event")) {
            return new Command(fullCommand.substring(fullCommand.indexOf("Event")+5,
                    fullCommand.indexOf("/")).trim());
        } else if(fullCommand.contains("deadline")) {
            return new Command(fullCommand.substring(fullCommand.indexOf("Deadline")+7,
                    fullCommand.indexOf("/")).trim());
        } else if(fullCommand.contains("bye")) {
            return new Command(("bye"));
        } else {
            return null;
        }
    }*/

    public static TodoCommand parseTodoCommand(String fullCommand, int numberOfTasks) {
        String description = fullCommand.substring(fullCommand.indexOf("todo")+4).trim();
        if(description =="") {
            return null;
        }
        return new TodoCommand(description, numberOfTasks);
    }

    public static DeadlineCommand parseDeadlineCommand(String fullCommand, int numberOfTasks) {
        try {
            return new DeadlineCommand(fullCommand.substring(fullCommand.indexOf("deadline") + 8,
                    fullCommand.indexOf("/")).trim(), numberOfTasks,
                    fullCommand.substring(fullCommand.indexOf("/") + 3).trim());
        } catch (StringIndexOutOfBoundsException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! There is nothing following \"deadline\".\n");
        }
        return null;
    }

    public static EventCommand parseEventCommand(String fullCommand, int numberOfTasks) {
        try {
            return new EventCommand(fullCommand.substring(fullCommand.indexOf("event")+5,
                    fullCommand.indexOf("/")).trim(), numberOfTasks,
                    fullCommand.substring(fullCommand.indexOf("/")+3).trim());
        } catch (StringIndexOutOfBoundsException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! There is nothing following \"event\".\n");
        }
        return null;
    }

    public static Command parseListCommand(int numberOfTasks) {
        return new ListCommand("list", numberOfTasks);
    }

    public static Command parseExitCommand(int numberOfTasks) {
        return new ExitCommand("bye", numberOfTasks);
    }

    public static Command parseDoneCommand(String fullCommand, int numberOfTasks) {
        return new DoneCommand(fullCommand.replace("done", " ").trim(), numberOfTasks);
    }

    public static Command parseDeleteCommand(String fullCommand, int numberOfTasks) {
        return new DeleteCommand(fullCommand.replace("delete", " ").trim(), numberOfTasks);
    }

    public static Command parseExceptionCommand(int numberOfTasks) {
        return new ExceptionCommand("exception", numberOfTasks);
    }
}
