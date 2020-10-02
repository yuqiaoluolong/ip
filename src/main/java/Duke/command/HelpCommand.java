package Duke.command;

import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.TRIPLEINDENTATION;

public class HelpCommand extends Command {
    public HelpCommand(String description, int numberOfTasks) {
        super(description, numberOfTasks);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        UI.printStatement(DOUBLEINDENTATION + "Hello, below is the explanation of the functions:\n"
                + TRIPLEINDENTATION + "1. you can add todo tasks follwed by the description\n"
                + TRIPLEINDENTATION + "example: todo task1 --> [T][✘] task1\n"
                + TRIPLEINDENTATION + "2. you can add deadlines followed by the description and the due time\n"
                + TRIPLEINDENTATION + "example: deadline d2 /by 2015-02-20T06:30:00 --> " +
                "[D][✘] d2 (by: Year.2015, Month.FEBRUARY, Day.20, Time: h.6, m.30)\n"
                + TRIPLEINDENTATION + "3. you can add events followed by the description and the happening time\n"
                + TRIPLEINDENTATION + "example: event e2 /by 2015-02-20T06:30:00 --> " +
                "[E][✘] e2 (by: Year.2015, Month.FEBRUARY, Day.20, Time: h.6, m.30)\n"
                + TRIPLEINDENTATION + "4. you can view all the tasks by typing \" list\" \n"
                + TRIPLEINDENTATION + "5. you can search for the tasks containing some words\n"
                + TRIPLEINDENTATION + "example: find book --> Duke will show all the tasks related with book\n"
                + TRIPLEINDENTATION + "6. you can mark one task as done by byping \"done\" followed its number\n"
                + TRIPLEINDENTATION + "7. you can delete one task as done by byping \"delete\" followed its number\n");
    }
}
