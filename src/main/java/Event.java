/**
 * Represents a task that occurs during a specified time interval.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event task from a user command.
     *
     * @param command the complete event command
     */
    public Event(String command) {
        super(command.substring(6, command.indexOf(" /from ")), TaskType.EVENT);
        this.from = command.substring(command.indexOf(" /from ") + 7,  command.indexOf(" /to "));
        this.to = command.substring(command.indexOf(" /to ") + 5);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }
}
