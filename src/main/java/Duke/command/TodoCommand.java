package Duke.command;

import Duke.DukeException.DeadlineNullException;
import Duke.DukeException.EventNullException;
import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.task.Todo;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.TRIPLEINDENTATION;

/**
 * Represents a TodoCommand with description and numberOfTasks.
 * A <code>todo</code> object corresponds to a TodoCommand
 * with the description extracted from what entered by users.
 */
public class TodoCommand extends Command {

    public TodoCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    /**
     * Override: Execute the Command.
     * add a new Todo into the taskList.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException, DeadlineNullException,
            EventNullException {
        super.execute(tasks, ui, storage);
        tasks.add(new Todo(this.description));
        if(tasks.get(numberOfTasks-1).description.length() == 0){
            throw new TodoNullException();
        }
        ui.printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks - 1).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks) + " tasks in the list.\n");
    }

    /**
     * Execute the save function.
     * Save the changed taskList into the file
     */
    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks);
    }
}
