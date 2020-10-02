package Duke;

import Duke.DukeException.DeadlineNullException;
import Duke.DukeException.EventNullException;
import Duke.DukeException.TodoNullException;
import Duke.command.Command;
import Duke.parser.Parser;
import Duke.storage.Storage;
import Duke.taskList.TaskList;
import Duke.ui.UI;

import java.time.Month;

import static Duke.ui.UI.DOUBLEINDENTATION;
import static Duke.ui.UI.printStatement;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        //try {
            tasks = new TaskList(storage.load());
        /*} catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        int numberOfTasks = storage.loadNumberOfTasks();
        while (!isExit) {
            //try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c;
                if(fullCommand.contains("todo")) {
                    c = Parser.parseTodoCommand(fullCommand, numberOfTasks);
                    numberOfTasks++;
                } else if(fullCommand.contains("deadline")){
                    c = Parser.parseDeadlineCommand(fullCommand, numberOfTasks);
                    numberOfTasks++;
                } else if(fullCommand.contains("event")) {
                    c = Parser.parseEventCommand(fullCommand, numberOfTasks);
                    numberOfTasks++;
                } else if(fullCommand.contains("help")) {
                    c = Parser.parseHelpCommand(numberOfTasks);
                } else if(fullCommand.contains("list")){
                    c = Parser.parseListCommand(numberOfTasks);
                } else if(fullCommand.contains("find")) {
                    c = Parser.parseFindCommand(fullCommand.substring(fullCommand.indexOf("find")+4).trim(),
                            numberOfTasks);
                } else if(fullCommand.contains("done")) {
                    c = Parser.parseDoneCommand(fullCommand, numberOfTasks);
                } else if(fullCommand.contains("delete")) {
                    c = Parser.parseDeleteCommand(fullCommand, numberOfTasks);
                    if(c.description.length() != 0) {
                        numberOfTasks--;
                    }
                } else if(fullCommand.contains("bye")){
                    c = Parser.parseExitCommand(numberOfTasks);
                } else {
                    c = Parser.parseExceptionCommand(numberOfTasks);
                }
                try{
                    c.execute(tasks, ui, storage);
                    c.sava(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (NullPointerException e) {
                    numberOfTasks--;
                } catch (TodoNullException e) {
                    printStatement(DOUBLEINDENTATION +
                            "☹ OOPS!!! The description of a todo cannot be empty.\n");
                    tasks.remove(numberOfTasks-1);
                    numberOfTasks--;
                } catch (DeadlineNullException e) {
                    numberOfTasks--;
                } catch (EventNullException e) {
                    numberOfTasks--;
                }

            /*} catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }*/
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/yuqiao/Desktop/CS2113T/ip/data/duke.txt").run();
    }
}