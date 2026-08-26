/**
 * Represents a task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    private final String deadline;
    private final java.time.LocalDateTime deadlineDateTime;
    private final boolean parsedDateTime;
    private final boolean hasTime;

    /**
     * Creates a deadline task from a user command.
     *
     * @param command the complete deadline command
     */
    public Deadline(String command) {
        super(command.substring(9, command.indexOf(" /by ")), TaskType.DEADLINE);
        this.deadline = command.substring(command.indexOf(" /by ") + 5);
        java.time.LocalDateTime parsed;
        try {
            parsed = DateTimeParser.parse(this.deadline);
        } catch (RuntimeException e) {
            parsed = null;
        }
        this.deadlineDateTime = parsed;
        this.parsedDateTime = parsed != null;
        this.hasTime = this.deadline.matches("\\d{4}-\\d{2}-\\d{2}.*\\d{2}:\\d{2}");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + (parsedDateTime
                ? DateTimeParser.format(deadlineDateTime, hasTime) : deadline) + ")";
    }

    @Override
    public String toFileFormat() {
        String savedDeadline = parsedDateTime
                ? (hasTime ? deadlineDateTime.toString() : deadlineDateTime.toLocalDate().toString()) : deadline;
        return super.toFileFormat() + " | " + savedDeadline;
    }
}
