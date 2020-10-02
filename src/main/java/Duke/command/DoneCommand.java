package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.*;

public class DoneCommand extends Command {
    public DoneCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage){
        try {
            tasks.get(Integer.parseInt(this.description)- 1).markAsDone();
            printStatement(DOUBLEINDENTATION + "Nice! I've marked this task as done: \n"
                    + TRIPLEINDENTATION + tasks.get(Integer.parseInt(this.description) - 1).toString() + "\n");
        } catch (NumberFormatException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! You did not indicate which task is done.\n");
        } catch (IndexOutOfBoundsException e) {
            printStatement(DOUBLEINDENTATION + "☹ OOPS!!! The index is out of the list boundary.\n");
        }
    }

    @Override
    public void sava(TaskList tasks, UI ui, Storage storage){
        Storage.save(tasks, numberOfTasks);
    }
}
