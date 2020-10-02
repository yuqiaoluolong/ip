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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "/Users/yuqiao/Desktop/CS2113T/ip/data/duke.txt";
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

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

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

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public ArrayList<Task> load(){
        ArrayList<Task> tasklist = new ArrayList<>();
        try {
            for (String encodedTask : Files.readAllLines(path)) {
                if(encodedTask.contains("D")) {
                    tasklist.add(new Deadline(getDescriptiongFromFile(encodedTask),
                            encodedTask.substring(encodedTask.indexOf("by")+3,
                                    encodedTask.lastIndexOf(")")).trim()));
                    if(encodedTask.contains("\u2713")) {
                        tasklist.get(tasklist.size()-1).markAsDone();
                    }
                } else if(encodedTask.contains("E")) {
                    tasklist.add(new Event(getDescriptiongFromFile(encodedTask),
                            encodedTask.substring(encodedTask.indexOf("at")+3,
                                    encodedTask.lastIndexOf(")")).trim()));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDescriptiongFromFile(String inputCommand) {
        if(inputCommand.contains(":")){
            return inputCommand.substring(inputCommand.lastIndexOf("]") + 1, inputCommand.indexOf((":"))-4).trim();
        } else {
            return inputCommand.substring(inputCommand.lastIndexOf("]") + 1).trim();
        }
    }

    public int loadNumberOfTasks(){
        int number = 0;
        try {
            for (int i = 0; i< Files.readAllLines(path).size(); i++) {
                number++;
            }
            return number;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String getPath() {
                return path.toString();
    }
}

