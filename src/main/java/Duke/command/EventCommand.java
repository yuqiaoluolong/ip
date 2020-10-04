package Duke.command;

import Duke.DukeException.DeadlineNullException;
import Duke.DukeException.EventNullException;
import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.task.Event;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static Duke.ui.UI.*;

/**
 * Represents an EventCommand with description and numberOfTasks.
 * A <code>event</code> object corresponds to a TodoCommand
 * with the description and happening time extracted from what entered by users.
 */
public class EventCommand extends Command {
    protected LocalDateTime at;

    public EventCommand(String description, int numberOfTasks, String at) {
        super(description, numberOfTasks);
        try {
            this.at = LocalDateTime.parse(at);
        } catch (DateTimeParseException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The time entered is not in the format(yyyy-mm-dd).\n");
        }
    }

    /**
     * Override: Execute the Command.
     * add a new Event into the taskList.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException, DeadlineNullException,
            EventNullException {
        super.execute(tasks, ui, storage);
        if(this.at.toString().length() == 0 && this.at.toString().length() == 0){
            throw new EventNullException();
        }
        tasks.add(new Event(this.description, this.at));
        ui.printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks + 1) + " tasks in the list.\n");
    }

    /**
     * Execute the save function.
     * Save the changed taskList into the file
     */
    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks + 1);
    }
}
