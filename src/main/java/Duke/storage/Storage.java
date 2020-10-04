package Duke.storage;

import Duke.DukeException.InvalidStorageFilePathException;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;
import Duke.taskList.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static Duke.ui.UI.INDENTATION;
import static Duke.ui.UI.printStatement;

/**
 * Represents a class saving the tasklist to a file and load the content from it.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "Duke.txt";
    public static Path path = Path.of(DEFAULT_STORAGE_FILEPATH);

    public Storage(String filePath) /*throws InvalidStorageFilePathException*/ {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            try {
                throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
            } catch (InvalidStorageFilePathException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a boolean variable indicating whethe the path is valid or not
     *
     * @param filePath  Path of the file to be tested.
     * @return the validity of the filePath.
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * save the changed task list into the file
     *
     * @param tasks Task list to save
     * @param numberOfTasks Number of the tasks in the task list.
     */
    public static void save(TaskList tasks, int numberOfTasks) {
        String text = "";
        for(int i = 0; i<numberOfTasks; i++) {
            text += tasks.get(i).toString() + System.lineSeparator();
        }
        try {
            writeToFile(String.valueOf(path), text);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * save the entered String into the file with the entered filepath
     *
     * @param filePath Filepath of the file where the String should be saved
     * @param textToAdd String need to be saved
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns a task list containing tasks inside the
     * file of the type ArrayList<Task>
     *
     * @return Task list containing tasks inside the
     * file of the type ArrayList<Task>
     */
    public ArrayList<Task> load(){
        ArrayList<Task> tasklist = new ArrayList<>();
        try {
            for (String encodedTask : Files.readAllLines(path)) {
                if(encodedTask.contains("D")) {
                    int year, dayOfMonth, hour, minute;
                    Month month;
                    try{
                        year =Integer.parseInt(encodedTask.substring(encodedTask.indexOf("Year") + 5,
                                encodedTask.indexOf("Month") - 2));
                        month = Month.valueOf(encodedTask.substring(encodedTask.indexOf("Month") + 6,
                                encodedTask.indexOf("Day") - 2));
                        dayOfMonth = Integer.parseInt(encodedTask.substring(encodedTask.indexOf("Day") + 4,
                                encodedTask.indexOf("Time") - 2));
                        hour = Integer.parseInt(encodedTask.substring(encodedTask.lastIndexOf("h") + 2,
                                encodedTask.lastIndexOf("m") - 2));
                        minute = Integer.parseInt(encodedTask.substring(encodedTask.lastIndexOf("m") + 2,
                                encodedTask.indexOf(")")));
                        tasklist.add(new Deadline(getDescriptiongFromFile(encodedTask),
                                LocalDateTime.of(year, month, dayOfMonth, hour, minute)));
                    } catch (DateTimeParseException e) {
                        tasklist.remove(tasklist.size()-1);
                        printStatement(INDENTATION + "☹ OOPS!!! There are time entered wrongly in the text file.\n" +
                                INDENTATION + "Please delete them.\n");
                    }
                    if(encodedTask.contains("\u2713")) {
                        tasklist.get(tasklist.size()-1).markAsDone();
                    }
                } else if(encodedTask.contains("E")) {
                    int year, dayOfMonth, hour, minute;
                    Month month;
                    try {
                        year =Integer.parseInt(encodedTask.substring(encodedTask.indexOf("Year") + 5,
                                encodedTask.indexOf("Month") - 2));
                        month = Month.valueOf(encodedTask.substring(encodedTask.indexOf("Month") + 6,
                                encodedTask.indexOf("Day") - 2));
                        dayOfMonth = Integer.parseInt(encodedTask.substring(encodedTask.indexOf("Day") + 4,
                                encodedTask.indexOf("Time") - 2));
                        hour = Integer.parseInt(encodedTask.substring(encodedTask.lastIndexOf("h") + 2,
                                encodedTask.lastIndexOf("m") - 2));
                        minute = Integer.parseInt(encodedTask.substring(encodedTask.lastIndexOf("m") + 2,
                                encodedTask.indexOf(")")));
                        tasklist.add(new Event(getDescriptiongFromFile(encodedTask),
                                LocalDateTime.of(year, month, dayOfMonth, hour, minute)));
                    } catch (DateTimeParseException e) {
                        tasklist.remove(tasklist.size()-1);
                        printStatement(INDENTATION + "☹ OOPS!!! There are time entered wrongly in the text file.\n" +
                                INDENTATION + "Please delete them.\n");
                    }
                    if(encodedTask.contains("\u2713")) {
                        tasklist.get(tasklist.size()-1).markAsDone();
                    }
                } else {
                    tasklist.add(new Todo(getDescriptiongFromFile(encodedTask)));
                    if(encodedTask.contains("\u2713")) {
                        tasklist.get(tasklist.size()-1).markAsDone();
                    }
                }
            }
            return tasklist;
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the description of a task as a String variable.
     * extracted from the inputCommand
     *
     * @param inputCommand  Command entered by users.
     * @return Description of a task as a String variable.
     */
    public static String getDescriptiongFromFile(String inputCommand) {
        if(inputCommand.contains(":")){
            return inputCommand.substring(inputCommand.lastIndexOf("]") + 1, inputCommand.indexOf((":"))-4).trim();
        } else {
            return inputCommand.substring(inputCommand.lastIndexOf("]") + 1).trim();
        }
    }

    /**
     * Returns the number of tasks inside the file when loading
     *
     * @return Number of tasks inside the file.
     */
    public int loadNumberOfTasks(){
        int number = 0;
        try {
            for (int i = 0; i< Files.readAllLines(path).size(); i++) {
                number++;
            }
            return number;
        } catch (NoSuchFileException e) {

        } catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

