package Duke.command;

import Duke.DukeException.DeadlineNullException;
import Duke.DukeException.EventNullException;
import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.task.Deadline;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static Duke.ui.UI.*;

/**
 * Represents a DeadlineCommand with description and numberOfTasks.
 * A <code>deadline</code> object corresponds to a TodoCommand
 * with the description and due time extracted from what entered by users.
 */
public class DeadlineCommand extends Command {
    protected LocalDateTime by;

    public DeadlineCommand(String description, int numberOfTasks, String by) {
        super(description, numberOfTasks);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The time entered is not in the format(yyyy-mm-dd).\n");
        }
    }

    /**
     * Override: Execute the Command.
     * add a new Deadline into the taskList.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException, DeadlineNullException,
            EventNullException {
        super.execute(tasks, ui, storage);
        if(this.by.toString().length() == 0 && this.by.toString().length() == 0){
            throw new DeadlineNullException();
        }
        tasks.add(new Deadline(this.description, this.by));
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
