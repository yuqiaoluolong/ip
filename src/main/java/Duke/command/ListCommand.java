package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.*;

/**
 * Represents a ListCommand with description and numberOfTasks.
 */
public class ListCommand extends Command {
    public ListCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    /**
     * Override: Execute the Command.
     * Print out all the tasks in the taskList.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.print(HORIZONTALLINE);
        System.out.println(INDENTATION + "Here is yuqiaoluolong's Duke: \n" +
                "      Here are the tasks in your list:");
        for(int i = 0; i < numberOfTasks; i++){
            System.out.println(DOUBLEINDENTATION + (i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTALLINE);
    }
}

