package Duke.taskList;

import Duke.task.Task;

import java.util.ArrayList;

/**
 * Represents an ArrayList of Task.
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        try {
            for(int i=0; i< tasks.size(); i++){
                taskList.add(tasks.get(i));
            }
        } catch (NullPointerException e) {

        }
    }

    /**
     * add one element to the taskList
     */
    public void add(Task oneTask){
        taskList.add(oneTask);
    }

    /**
     * get the element in the taskList with entered index
     *
     * @param index  the index of the element in taskList that we want.
     * @return the element of type Task
     */
    public Task get(int index){
        return taskList.get(index);
    }

    /**
     * remove one element in the taskList with entered index
     *
     * @param  i the index of the element in taskList that we want to remove.
     */
    public void remove(int i) {
        taskList.remove(i);
    }
}
