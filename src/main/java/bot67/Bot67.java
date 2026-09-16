package bot67;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import bot67.exception.Bot67Exception;
import bot67.parser.Command;
import bot67.parser.Parser;
import bot67.storage.Storage;
import bot67.task.Deadline;
import bot67.task.Event;
import bot67.task.Task;
import bot67.task.TaskList;
import bot67.task.Todo;
import bot67.ui.Ui;

/** Runs Bot67's task-management logic for either the console or JavaFX UI. */
public class Bot67 {
    private static final String BANNER = "_____       ___    __  ______\n"
            + "| __ )  ___ | |_  / /  |___  |\n"
            + "|  _ \\ / _ \\| __|/ /_     / /\n"
            + "| |_) | (_) | |_| '_ \\   / /\n"
            + "|____/ \\___/ \\__|\\___/  /_/\n";
    private static final String PERSONALITY_ART = "⠀⠀⢀⠤⣂⣤⣬⣭⣭⣭⣔⡠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠔⣵⣾⣿⣿⣿⢿⣿⣿⣿⣿⣎⢂⠀⢲⣤⣤⣤⣤⣀⣒⣒⣒⣒⣂⡠⠤⠤⣄\n"
            + "⠐⣾⣿⣿⣿⡏⣾⡿⢎⣛⣫⣭⣴⣾⠆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢼\n"
            + "⡇⣿⣿⣿⣿⣟⡿⢀⣐⣻⣛⡩⢁⠀⠀⣘⣛⣛⡛⠿⠿⠿⢿⣿⣿⣿⣿⣿⢟⣾\n"
            + "⡇⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⣿⣶⡕⠄⠉⠛⠛⠛⠛⡻⣣⣾⣿⣿⣿⢟⣵⣿⠛\n"
            + "⠃⣿⣿⣿⣿⣿⢋⣥⠭⡻⣿⣿⣿⣿⡌⡄⠀⠀⠀⡐⣼⣿⣿⣿⡿⣣⣾⠏⠀⠀\n"
            + "⠨⢻⣿⣿⣿⣧⢻⠁⠀⠘⢸⣿⣿⣿⡇⣿⠀⠀⠌⣼⣿⣿⣿⡿⢱⣿⠃⠀⠀⠀\n"
            + "⠀⢦⢻⣿⣿⣿⣦⣐⣀⣊⣼⣿⣿⡿⢱⡿⠀⠰⣸⣿⣿⣿⣿⢣⣿⠃⠀⠀⠀⠀\n"
            + "⠀⠀⠣⣙⠿⣿⣿⣿⣿⣿⣿⠿⢛⣵⡿⠃⢀⢃⣿⣿⣿⣿⡟⣾⡇⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠈⠛⠶⣮⣭⣭⣴⣶⡿⠿⠋⠀⠀⢨⣘⣿⡻⠿⠿⢇⣿⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⢀⠔⠒⠂⠠⠤⠭⡀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠛⠛⠛⠻⠃⠀⠀⠀⠀⠀⠀\n"
            + "⢀⠆⠁⠀⡄⠀⠀⠀⠀⠈⢂⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠒⠁⠀⠀⠒⢤⡀⠀⠀\n"
            + "⠣⠤⢤⠞⠂⠀⣀⠰⠃⠀⠘⣆⢀⣀⠀⠀⠀⠀⢀⠎⠀⢠⡀⠀⠀⠀⢀⠀⠙⡀\n"
            + "⠀⠀⢸⠀⠈⠭⡀⢈⣡⠔⢶⠁⣹⢩⠃⠀⢀⠀⢸⠀⠀⠀⣑⣠⣤⠀⠙⡦⣀⠜\n"
            + "⠀⠀⠀⠣⠀⢂⠞⠱⠴⣈⡸⠰⢇⠘⠀⠰⡭⠷⢝⡤⣂⣄⠒⢤⡐⠀⠀⡇⠀⠀\n"
            + "⠀⠀⠀⠀⠱⠄⣀⢜⢁⡠⠥⠊⠀⠀⠀⠀⠡⡘⡄⠐⡂⠘⢌⡀⠉⠂⡸⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠄⠹⢅⣀⠹⠒⠊⠀⠀⠀⠠";

    private final Parser parser = new Parser();
    private final Storage storage = new Storage();
    private final TaskList tasks;
    private boolean isExitRequested;
    private boolean isLastResponseError;

    /** Loads saved tasks and prepares Bot67 to receive commands. */
    public Bot67() {
        this.tasks = new TaskList(loadTasks());
    }

    /** Starts the original text interface, which remains useful for automated testing. */
    public static void main(String[] args) {
        Bot67 bot = new Bot67();
        Ui ui = new Ui();
        ui.showWelcome(BANNER);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            ui.showSeparator();
            bot.execute(scanner.nextLine(), ui);
            if (bot.isExitRequested()) {
                break;
            }
            ui.showSeparator();
        }
    }

    /** Processes one GUI command and returns Bot67's response without console separators. */
    public String getResponse(String input) {
        isLastResponseError = false;
        if (input.equals("bye")) {
            isExitRequested = true;
            return Ui.GOODBYE;
        }
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(response, true, StandardCharsets.UTF_8)) {
            execute(input, new Ui(output));
        }
        return response.toString(StandardCharsets.UTF_8).stripTrailing();
    }

    /** Returns whether the latest command requested application shutdown. */
    public boolean isExitRequested() {
        return isExitRequested;
    }

    /** Supplies the original Braille art for graphical rendering without font dependencies. */
    public static String getPersonalityArt() {
        return PERSONALITY_ART;
    }

    /** Reports failure separately from response text so the GUI can highlight errors. */
    public boolean isLastResponseError() {
        return isLastResponseError;
    }

    /** Executes one command using the supplied output UI. */
    private void execute(String command, Ui ui) {
        try {
            Command parsedCommand = parser.parse(command, PERSONALITY_ART);
            if (parsedCommand != null) {
                parsedCommand.execute(tasks, ui, storage);
                isExitRequested = parsedCommand.isExit();
            } else {
                executeTaskCommand(command, ui);
            }
        } catch (Bot67Exception e) {
            isLastResponseError = true;
            ui.showError(e.getMessage());
        } catch (RuntimeException e) {
            isLastResponseError = true;
            ui.showError("I could not process that command. Please check its format.");
        }
    }

    /** Executes commands that are not represented by a {@link Command} object yet. */
    private void executeTaskCommand(String command, Ui ui) throws Bot67Exception {
        if (command.equals("list")) {
            showList(ui);
        } else if (command.equals("sort")) {
            sortTasks(ui);
        } else if (command.startsWith("find ")) {
            showFindResults(command, ui);
        } else if (isIncompleteAddCommand(command)) {
            rejectIncompleteAddCommand(command);
        } else if (command.startsWith("mark ")) {
            changeTaskStatus(command.substring(5), true, ui);
        } else if (command.startsWith("unmark ")) {
            changeTaskStatus(command.substring(7), false, ui);
        } else if (command.startsWith("delete ")) {
            deleteTask(command.substring(7), ui);
        } else if (command.startsWith("todo ")) {
            parser.requireText(command.substring(5));
            addTask(new Todo(command), ui);
        } else if (command.startsWith("deadline ")) {
            parser.requireValidDeadline(command);
            addTask(new Deadline(command), ui);
        } else if (command.startsWith("event ")) {
            parser.requireValidEvent(command);
            addTask(new Event(command), ui);
        } else {
            throw new Bot67Exception("I do not recognize that command.");
        }
    }

    /** Returns whether an add command is missing all required arguments. */
    private boolean isIncompleteAddCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    /** Reports the command-specific usage message for an incomplete add command. */
    private void rejectIncompleteAddCommand(String command) throws Bot67Exception {
        switch (command) {
            case "todo":
                throw new Bot67Exception("A todo description cannot be empty.");
            case "deadline":
                throw new Bot67Exception("Use: deadline <description> /by <date or time>.");
            case "event":
                throw new Bot67Exception("Use: event <description> /from <start> /to <end>.");
            default:
                throw new IllegalArgumentException("Expected an incomplete add command: " + command);
        }
    }

    /** Displays every task in its numbered position. */
    private void showList(Ui ui) {
        if (tasks.size() == 0) {
            ui.showLine("Six seven! No tasks in the list yet. Let's start small: todo read a book");
            return;
        }
        ui.showLine("Six seven! Here's your task lineup. One at a time, we've got this:");
        IntStream.rangeClosed(1, tasks.size())
                .mapToObj(taskNumber -> taskNumber + "." + tasks.get(taskNumber).getDescription())
                .forEach(ui::showLine);
    }

    /** Sorts tasks alphabetically, saves the new order, and displays it. */
    private void sortTasks(Ui ui) {
        tasks.sortByName();
        saveTasks();
        ui.showLine("Six seven! I've sorted your tasks alphabetically. Even 67 likes a little order:");
        showList(ui);
    }

    /** Displays tasks containing the requested keyword. */
    private void showFindResults(String command, Ui ui) throws Bot67Exception {
        String keyword = command.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new Bot67Exception("Use: find <keyword>.");
        }
        List<String> matches = IntStream.rangeClosed(1, tasks.size())
                .filter(taskNumber -> tasks.get(taskNumber).getDescription().contains(keyword))
                .mapToObj(taskNumber -> taskNumber + "." + tasks.get(taskNumber).getDescription())
                .toList();
        if (matches.isEmpty()) {
            ui.showLine("Six seven... no matching tasks this time. Try another keyword!");
        } else {
            ui.showLine("Six seven! Found them. Here are the matching tasks in your list:");
            matches.forEach(ui::showLine);
        }
    }

    /** Marks or unmarks the task at the supplied position. */
    private void changeTaskStatus(String value, boolean isDone, Ui ui) throws Bot67Exception {
        int taskNumber = parser.parseTaskNumber(value);
        if (isDone) {
            tasks.mark(taskNumber);
            saveTasks();
            ui.showLine("Six seven! One task down! I've marked this task as done:",
                    "  [X] " + tasks.get(taskNumber).getName());
        } else {
            tasks.unmark(taskNumber);
            saveTasks();
            ui.showLine("Six seven! Another round? I've marked this task as not done yet:",
                    "  [ ] " + tasks.get(taskNumber).getName());
        }
    }

    /** Deletes the task at the supplied position. */
    private void deleteTask(String value, Ui ui) throws Bot67Exception {
        int taskNumber = parser.parseTaskNumber(value);
        if (taskNumber > tasks.size()) {
            throw new Bot67Exception("Task number is out of range.");
        }
        Task deletedTask = tasks.delete(taskNumber);
        saveTasks();
        ui.showLine("Six seven. Making room! I've removed this task:",
                "  " + deletedTask.getDescription(),
                "Now you have " + taskCount() + " in the list.");
    }

    /** Adds and saves one task. */
    private void addTask(Task task, Ui ui) {
        tasks.add(task);
        saveTasks();
        ui.showLine("Six seven! On it. I've added this task:",
                "  " + task.getDescription(),
                "You have " + taskCount() + " in the list. 67!");
    }

    /** Formats the actual task count without letting the catchphrase obscure it. */
    private String taskCount() {
        return tasks.size() + (tasks.size() == 1 ? " task" : " tasks");
    }

    /** Loads saved tasks, falling back to an empty list if reading fails. */
    private List<Task> loadTasks() {
        try {
            return storage.load();
        } catch (IOException e) {
            return List.of();
        }
    }

    /** Saves the current task list. */
    private void saveTasks() {
        try {
            storage.save(tasks.asList());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save tasks", e);
        }
    }
}
