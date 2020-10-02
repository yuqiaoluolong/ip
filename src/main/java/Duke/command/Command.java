package Duke.command;

import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

/**
 * Represents a Command with description and numberOfTasks.
 * Is it an abstracted type.
 */
public class Command {
    protected String description;
    protected int numberOfTasks = 0;

    public Command(String description, int numberOfTasks) {
        this.description = description;
        this.numberOfTasks = numberOfTasks;
    }

    /**
     * Execute the Command.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException {
        numberOfTasks++;
    }

    /**
     * Execute the save function.
     * Save the changed taskList into the file
     */
    public void sava(TaskList tasks, UI ui, Storage storage){

    }

    /**
     * Return a boolean variable indicating whether the command
     * is an ExitCommand.
     * If it is, return true. Else wise, return false.
     *
     * @return Boolean variable indicating whether the command
     * is an ExitCommand.
     */
    public boolean isExit() {
        if(description == "bye") {
            return true;
        }
        else {
            return false;
        }
    }
}
