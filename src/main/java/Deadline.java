/**
 * Represents a task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Creates a deadline task from a user command.
     *
     * @param command the complete deadline command
     */
    public Deadline(String command) {
        super(command.substring(9, command.indexOf(" /by ")), TaskType.DEADLINE);
        this.deadline = command.substring(command.indexOf(" /by ") + 5);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + deadline + ")";
    }
}
