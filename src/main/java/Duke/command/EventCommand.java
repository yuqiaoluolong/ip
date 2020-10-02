package Duke.command;

import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.task.Event;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.TRIPLEINDENTATION;

public class EventCommand extends Command {
    protected String at;

    public EventCommand(String description, int numberOfTasks, String at) {
        super(description, numberOfTasks);
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException {
        super.execute(tasks, ui, storage);
        tasks.add(new Event(this.description, this.at));
        ui.printStatement(DOUBLEINDENTATION + "Got it. I've added this task:\n"
                + TRIPLEINDENTATION + tasks.get(numberOfTasks - 1).toString() + "\n" + DOUBLEINDENTATION
                + "Now you have " + (numberOfTasks) + " tasks in the list.\n");
    }

    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks);
    }
}
