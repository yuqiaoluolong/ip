package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.*;

/**
 * Represents a DeleteCommand with description and numberOfTasks.
 * A <code>delete</code> object corresponds to a DeleteCommand
 * with the description extracted from what entered by users,
 * which is the index of the task users intend to delete.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    /**
     * Override: Execute the Command.
     * Delete the task with entered index.
     * Print out the delete message.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage){
        try {
            printStatement(DOUBLEINDENTATION + "Noted. I've removed this task: \n" +
                    TRIPLEINDENTATION + tasks.get(Integer.parseInt(this.description)-1).toString() + "\n" +
                    DOUBLEINDENTATION + "Now you have " + (numberOfTasks-1) + " tasks in the list.\n");
            tasks.remove(Integer.parseInt(this.description)-1);
        } catch (NumberFormatException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! You did not indicate which task to delete.\n");
        }
    }

    /**
     * Execute the save function.
     * Save the changed taskList into the file
     */
    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks-1);
    }
}
