package Duke.task;

/**
 * Represents a Todo Task. A<code>todo</code> corresponds to a task
 * described by description and isDone.
 */
public class Todo extends Task {

    public Todo(String description){
        super(description);
    }

    /**
     * Override: convert the task to a String in a certain format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
