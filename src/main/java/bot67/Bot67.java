package bot67;

import java.io.IOException;
import java.util.Scanner;

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

/**
 * Runs the Bot67 command-line task manager.
 */
public class Bot67 {
    /**
     * Starts the command-line interface and processes user commands.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        String banner =  "_____       ___    __  ______\n"
                + "| __ )  ___ | |_  / /  |___  |\n"
                + "|  _ \\ / _ \\| __|/ /_     / /\n"
                + "| |_) | (_) | |_| '_ \\   / /\n"
                + "|____/ \\___/ \\__|\\___/  /_/\n";

        String sixSeven = "⠀⠀⢀⠤⣂⣤⣬⣭⣭⣭⣔⡠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
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

        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList(java.util.List.of());
            ui.showError("I could not load the saved tasks. Starting with an empty list.");
        }

        ui.showWelcome(banner);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ui.showSeparator();

            try {
            Command parsedCommand = parser.parse(command, sixSeven);
            if (parsedCommand != null) {
                parsedCommand.execute(tasks, ui, storage);
                if (parsedCommand.isExit()) {
                    break;
                }
            } else if (command.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i).getDescription());
                }
            } else if (command.equals("todo")) {
                throw new Bot67Exception("A todo description cannot be empty.");
            } else if (command.equals("deadline")) {
                throw new Bot67Exception("Use: deadline <description> /by <date or time>.");
            } else if (command.equals("event")) {
                throw new Bot67Exception("Use: event <description> /from <start> /to <end>.");
            } else if (command.startsWith("mark ")) {
                int taskNumber = parser.parseTaskNumber(command.substring(5));
                int taskIndex = taskNumber - 1;
                tasks.mark(taskNumber);
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've marked this task as done:");
                System.out.println("  [X] " + tasks.get(taskNumber).getName());
            } else if (command.startsWith("unmark ")) {
                int taskNumber = parser.parseTaskNumber(command.substring(7));
                int taskIndex = taskNumber - 1;
                tasks.unmark(taskNumber);
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks.get(taskNumber).getName());
            } else if (command.startsWith("delete ")) {
                int taskNumber = parser.parseTaskNumber(command.substring(7));
                int taskIndex = taskNumber - 1;
                if (taskNumber > tasks.size()) {
                    throw new Bot67Exception("Task number is out of range.");
                }
                Task deletedTask = tasks.delete(taskNumber);
                saveTasks(storage, tasks.asList());
                System.out.println("Six seven. I've removed this task:");
                System.out.println("  " + deletedTask.getDescription());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.startsWith("todo ")) {
                parser.requireText(command.substring(5));
                tasks.add(new Todo(command));
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("deadline ")) {
                parser.requireValidDeadline(command);
                tasks.add(new Deadline(command));
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("event ")) {
                parser.requireValidEvent(command);
                tasks.add(new Event(command));
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else {
                throw new Bot67Exception("I do not recognize that command.");
            }

            } catch (Bot67Exception e) {
                ui.showError(e.getMessage());
            } catch (RuntimeException e) {
                ui.showError("I could not process that command. Please check its format.");
            }

            ui.showSeparator();
        }

    }

    /** Persists the current tasks and converts storage failures into application errors. */
    private static void saveTasks(Storage storage, java.util.List<Task> tasks) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
