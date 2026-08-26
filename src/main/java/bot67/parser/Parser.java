package bot67.parser;

import bot67.exception.Bot67Exception;

/**
 * Interprets and validates the parts of commands that require parsing.
 */
public class Parser {
    /** Converts the exit command into its command object. Other commands are not handled yet. */
    public Command parse(String fullCommand, String personalityArt) throws Bot67Exception {
        if (fullCommand.equals("bye")) {
            return new ExitCommand(personalityArt);
        }
        return null;
    }
    /** Parses a one-based task number and validates its allowed range. */
    public int parseTaskNumber(String value) throws Bot67Exception {
        try {
            int taskNumber = Integer.parseInt(value.trim());
            if (taskNumber < 1 || taskNumber > 100) {
                throw new Bot67Exception("Task number must be between 1 and 100.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new Bot67Exception("Task number must be a whole number.");
        }
    }

    /** Validates a todo description. */
    public void requireText(String text) throws Bot67Exception {
        if (text.trim().isEmpty()) {
            throw new Bot67Exception("A todo description cannot be empty.");
        }
    }

    /** Validates a deadline command's description and date/time. */
    public void requireValidDeadline(String command) throws Bot67Exception {
        int marker = command.indexOf(" /by ");
        if (marker <= 9 || command.substring(marker + 5).trim().isEmpty()) {
            throw new Bot67Exception("Use: deadline <description> /by <date or time>.");
        }
        validateDateTime(command.substring(marker + 5), "deadline");
    }

    /** Validates an event command's description and date/time range. */
    public void requireValidEvent(String command) throws Bot67Exception {
        int from = command.indexOf(" /from ");
        int to = command.indexOf(" /to ");
        if (from <= 6 || to <= from + 7 || command.substring(to + 5).trim().isEmpty()) {
            throw new Bot67Exception("Use: event <description> /from <start> /to <end>.");
        }
        validateDateTime(command.substring(from + 7, to), "event");
        validateDateTime(command.substring(to + 5), "event");
    }

    private void validateDateTime(String value, String commandType) throws Bot67Exception {
        try {
            DateTimeParser.parse(value.trim());
        } catch (RuntimeException e) {
            // Keep Level 7's free-form values working, while parsing ISO values as Level 8 dates.
            if (value.trim().matches("\\d{4}-\\d{2}-\\d{2}.*")) {
                throw new Bot67Exception("Invalid " + commandType
                        + " date/time. Use yyyy-MM-dd or yyyy-MM-ddTHH:mm.");
            }
        }
    }
}
