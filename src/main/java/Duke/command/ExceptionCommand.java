package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.printStatement;

public class ExceptionCommand extends Command {
    public ExceptionCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        printStatement(DOUBLEINDENTATION + ui.WRONGMESSAGE);
    }
}
