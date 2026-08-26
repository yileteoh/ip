package bot67.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Owns Bot67's tasks and provides operations for changing the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Creates a task list containing the supplied tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Removes and returns the task at the one-based position. */
    public Task delete(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /** Marks the task at the one-based position as done. */
    public void mark(int taskNumber) {
        get(taskNumber).mark();
    }

    /** Marks the task at the one-based position as not done. */
    public void unmark(int taskNumber) {
        get(taskNumber).unmark();
    }

    /** Returns the task at the one-based position. */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /** Returns the number of tasks. */
    public int size() {
        return tasks.size();
    }

    /** Returns the tasks for persistence. */
    public List<Task> asList() {
        return tasks;
    }
}
