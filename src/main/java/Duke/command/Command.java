package Duke.command;

import Duke.DukeException.TodoNullException;
import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

public class Command {
    protected String description;
    protected int numberOfTasks = 0;

    public Command(String description, int numberOfTasks) {
        this.description = description;
        this.numberOfTasks = numberOfTasks;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws TodoNullException {
        numberOfTasks++;
    }

    public void sava(TaskList tasks, UI ui, Storage storage){

    }

    public boolean isExit() {
        if(description == "bye") {
            return true;
        }
        else {
            return false;
        }
    }

    public Command change() {
        return null;
    }
}
