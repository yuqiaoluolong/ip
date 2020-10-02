package Duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline Task. A<code>deadline</code> corresponds to a task
 * described by description ,isDone, and by.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Override: convert the task to a String in a certain format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: Year." + by.getYear() +", Month." + by.getMonth()
                + ", Day." + by.getDayOfMonth() +", Time: h." + by.getHour() +", m." + by.getMinute() + ")";
    }
}