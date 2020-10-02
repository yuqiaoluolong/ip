package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.*;

public class DeleteCommand extends Command {
    public DeleteCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage){
        printStatement(DOUBLEINDENTATION + "Noted. I've removed this task: \n" +
                TRIPLEINDENTATION + tasks.get(Integer.parseInt(this.description)-1).toString() + "\n" +
                DOUBLEINDENTATION + "Now you have " + (numberOfTasks-1) + " tasks in the list.\n");
        tasks.remove(Integer.parseInt(this.description)-1);
    }

    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks-1);
    }
}
