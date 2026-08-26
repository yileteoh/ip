package bot67.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import bot67.task.Deadline;
import bot67.task.Event;
import bot67.task.Task;
import bot67.task.Todo;

/** Writes Bot67 tasks to the local save file. */
public class Storage {
    private static final Path SAVE_FILE = Path.of("data", "duke.txt");

    /**
     * Saves all tasks to disk using one task per line.
     *
     * @param tasks the tasks to save
     * @throws IOException if the file cannot be written
     */
    public void save(List<Task> tasks) throws IOException {
        Files.createDirectories(SAVE_FILE.getParent());
        Files.write(SAVE_FILE, tasks.stream().map(Task::toFileFormat).toList());
    }

    /**
     * Loads valid tasks from the save file. A missing file represents an empty task list.
     * Invalid lines are ignored so that one malformed task does not prevent Duke from starting.
     *
     * @return the tasks read from the save file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(SAVE_FILE)) {
            return tasks;
        }

        for (String line : Files.readAllLines(SAVE_FILE)) {
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private Task parseTask(String line) {
        String[] parts = line.split("\\s+\\|\\s+", -1);
        try {
            if (parts.length == 3 && parts[0].equals("T")) {
                return createTask("todo " + parts[2], parts[1], new Todo("todo " + parts[2]));
            }
            if (parts.length == 4 && parts[0].equals("D")) {
                String command = "deadline " + parts[2] + " /by " + parts[3];
                return createTask(command, parts[1], new Deadline(command));
            }
            if (parts.length == 5 && parts[0].equals("E")) {
                String command = "event " + parts[2] + " /from " + parts[3] + " /to " + parts[4];
                return createTask(command, parts[1], new Event(command));
            }
        } catch (RuntimeException e) {
            return null;
        }
        return null;
    }

    private Task createTask(String command, String status, Task task) {
        if (status.equals("1")) {
            task.mark();
        } else if (!status.equals("0")) {
            return null;
        }
        return task;
    }
}
