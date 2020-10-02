package Duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at){
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: Year." + at.getYear() +", Month." + at.getMonth()
                + ", Day." + at.getDayOfMonth() +", Time: h." + at.getHour() +", m." + at.getMinute() + ")";
    }
}
