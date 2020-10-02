package Duke.taskList;

import Duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static final int MAX_NUM_TASKS = 100;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        for(int i=0; i< tasks.size(); i++){
            taskList.add(tasks.get(i));
        }
    }

    public void add(Task oneTask){
        taskList.add(oneTask);
    }

    public Task get(int index){
        return taskList.get(index);
    }

    public void remove(int i) {
        taskList.remove(i);
    }
}
