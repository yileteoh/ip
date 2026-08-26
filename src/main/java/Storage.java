import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
}
