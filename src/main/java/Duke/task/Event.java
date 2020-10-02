package Duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Event Task. A<code>event</code> corresponds to a task
 * described by description ,isDone, and at.
 */
public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at){
        super(description);
        this.at = at;
    }

    /**
     * Override: convert the task to a String in a certain format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: Year." + at.getYear() +", Month." + at.getMonth()
                + ", Day." + at.getDayOfMonth() +", Time: h." + at.getHour() +", m." + at.getMinute() + ")";
    }
}
