/**
 * Represents a user command that can be executed by Bot67.
 */
public abstract class Command {
    /** Executes this command using the application's collaborators. */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /** Returns whether this command terminates the application. */
    public boolean isExit() {
        return false;
    }
}
