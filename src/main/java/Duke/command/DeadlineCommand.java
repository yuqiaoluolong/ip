package Duke.command;

import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.task.Deadline;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.TRIPLEINDENTATION;

public class DeadlineCommand extends Command {
    protected String by;

    public DeadlineCommand(String description, int numberOfTasks, String by) {
        super(description, numberOfTasks);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException {
        super.execute(tasks, ui, storage);
        tasks.add(new Deadline(this.description, this.by));
        ui.printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks - 1).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks) + " tasks in the list.\n");
    }

    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks);
    }
}
