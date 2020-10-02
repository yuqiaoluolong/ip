package Duke.parser;

import Duke.command.*;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.printStatement;

/**
 * parse the command entered into different format
 * for methods to take in
 */
public class Parser {

    /**
     * Returns TodolineCommand with the description
     * extracted from the fullCommand
     *
     * @param fullCommand  Command entered by users.
     * @param numberOfTasks Number of tasks.
     * @return TodolineCommand with the description.
     */
    public static TodoCommand parseTodoCommand(String fullCommand, int numberOfTasks) {
        String description = fullCommand.substring(fullCommand.indexOf("todo")+4).trim();
        if(description =="") {
            return null;
        }
        return new TodoCommand(description, numberOfTasks);
    }

    /**
     * Returns DeadlineCommand with the description and due time
     * extracted from the fullCommand
     *
     * @param fullCommand  Command entered by users.
     * @param numberOfTasks Number of tasks.
     * @return DeadlineCommand with the description and due time.
     */
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

    /**
     * Returns EventCommand with the description and happening time
     * extracted from the fullCommand
     *
     * @param fullCommand  Command entered by users.
     * @param numberOfTasks Number of tasks.
     * @return EventCommand with the description and happening time.
     */
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

    /**
     * Returns ListCommand with the description of "list"
     *
     * @param numberOfTasks Number of tasks.
     * @return ListCommand with the description of "list".
     */
    public static Command parseListCommand(int numberOfTasks) {
        return new ListCommand("list", numberOfTasks);
    }
    /**
     * Returns ListCommand with the description of "bye"
     *
     * @param numberOfTasks Number of tasks.
     * @return ListCommand with the description of "bye".
     */
    public static Command parseExitCommand(int numberOfTasks) {
        return new ExitCommand("bye", numberOfTasks);
    }

    /**
     * Returns DoneCommand with the description which is the index of tasks to change to done
     * and extracted from the fullCommand.
     *
     * @param fullCommand Command entered by users
     * @param numberOfTasks Number of tasks.
     * @return DoneCommand with the description of the index of tasks to change to done.
     */
    public static Command parseDoneCommand(String fullCommand, int numberOfTasks) {
        return new DoneCommand(fullCommand.replace("done", " ").trim(), numberOfTasks);
    }

    /**
     * Returns DeleteCommand with the description which is the index of tasks to delete
     * and extracted from the fullCommand.
     *
     * @param fullCommand Command entered by users
     * @param numberOfTasks Number of tasks.
     * @return DeleteCommand with the description that is the index of tasks to delete.
     */
    public static Command parseDeleteCommand(String fullCommand, int numberOfTasks) {
        return new DeleteCommand(fullCommand.replace("delete", " ").trim(), numberOfTasks);
    }

    /**
     * Returns Exception Command with the description "exception"
     *
     * @param numberOfTasks Number of tasks.
     * @return Exception Command with the description "exception"
     */
    public static Command parseExceptionCommand(int numberOfTasks) {
        return new ExceptionCommand("exception", numberOfTasks);
    }
}
