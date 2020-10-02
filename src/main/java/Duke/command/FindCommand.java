package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.*;

public class FindCommand extends Command {
    public FindCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.print(HORIZONTALLINE);
        System.out.println(INDENTATION + "Here is yuqiaoluolong's Duke: \n" +
                "      Here are the matching tasks in your list:");
        for(int i = 0; i < numberOfTasks; i++){
            if(tasks.get(i).toString().contains(description)) {
                System.out.println(DOUBLEINDENTATION + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        System.out.println(HORIZONTALLINE);
    }
}

