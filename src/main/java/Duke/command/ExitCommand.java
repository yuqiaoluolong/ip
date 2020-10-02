package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

/**
 * Represents an ExitCommand with description and numberOfTasks.
 */
public class ExitCommand extends Command {
    public ExitCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    /**
     * Override: Execute the Command.
     * Print out the Exit message and exit.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printStatement(ui.BYE);
    }
}
