package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.printStatement;

/**
 * Represents an ExceptionCommand with description and numberOfTasks.
 * It indicates that there are no corresponding functions in this Duke.
 */
public class ExceptionCommand extends Command {
    public ExceptionCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    /**
     * Override: Execute the Command.
     * Print out the wrong message.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        printStatement(DOUBLEINDENTATION + ui.WRONGMESSAGE);
    }
}
