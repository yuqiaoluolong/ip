package Duke.task;

/**
 * Represents a Deadline Task. A<code>deadline</code> corresponds to a task
 * described by description ,isDone, and by.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Override: convert the task to a String in a certain format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}