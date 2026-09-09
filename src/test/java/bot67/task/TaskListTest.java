package bot67.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    void listOperations_useOneBasedPositionsAndPreserveOrder() {
        Todo first = new Todo("todo first");
        Todo second = new Todo("todo second");
        TaskList list = new TaskList(List.of(first));
        list.add(second);

        assertEquals(2, list.size());
        assertEquals(first, list.get(1));
        list.mark(2);
        assertEquals("X", second.getStatusIcon());
        list.unmark(2);
        assertEquals(" ", second.getStatusIcon());
        assertEquals(second, list.delete(2));
        assertEquals(1, list.size());
    }

    @Test
    void constructor_nullTaskList_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new TaskList(null));
    }

    @Test
    void add_nullTask_throwsAssertionError() {
        TaskList list = new TaskList(List.of());

        assertThrows(AssertionError.class, () -> list.add(null));
    }

    @Test
    void delete_outOfRangeTaskNumber_throwsAssertionError() {
        TaskList list = new TaskList(List.of(new Task("first", TaskType.TODO)));

        assertThrows(AssertionError.class, () -> list.delete(0));
        assertThrows(AssertionError.class, () -> list.delete(2));
    }
}
