package Duke.task;

/**
 * Represents a Event Task. A<code>event</code> corresponds to a task
 * described by description ,isDone, and at.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    /**
     * Override: convert the task to a String in a certain format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
