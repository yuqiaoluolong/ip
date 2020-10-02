package Duke.task;

/**
 * Represents a Task. It is an abstract type
 */
public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * mark the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * convert the task to a String in a certain format
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
