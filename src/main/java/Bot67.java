import java.io.IOException;
import java.util.Scanner;

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
            if (command.equals("bye")) {
                ui.showGoodbye(sixSeven);
                break;
            }

            if (command.equals("list")) {
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
                int taskNumber = parseTaskNumber(command.substring(5));
                int taskIndex = taskNumber - 1;
                tasks.mark(taskNumber);
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've marked this task as done:");
                System.out.println("  [X] " + tasks.get(taskNumber).getName());
            } else if (command.startsWith("unmark ")) {
                int taskNumber = parseTaskNumber(command.substring(7));
                int taskIndex = taskNumber - 1;
                tasks.unmark(taskNumber);
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks.get(taskNumber).getName());
            } else if (command.startsWith("delete ")) {
                int taskNumber = parseTaskNumber(command.substring(7));
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
                requireText(command.substring(5));
                tasks.add(new Todo(command));
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("deadline ")) {
                requireValidDeadline(command);
                tasks.add(new Deadline(command));
                saveTasks(storage, tasks.asList());

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size()).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("event ")) {
                requireValidEvent(command);
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

    private static int parseTaskNumber(String value) throws Bot67Exception {
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

    private static void requireText(String text) throws Bot67Exception {
        if (text.trim().isEmpty()) {
            throw new Bot67Exception("A todo description cannot be empty.");
        }
    }

    private static void requireValidDeadline(String command) throws Bot67Exception {
        int marker = command.indexOf(" /by ");
        if (marker <= 9 || command.substring(marker + 5).trim().isEmpty()) {
            throw new Bot67Exception("Use: deadline <description> /by <date or time>.");
        }
        validateDateTime(command.substring(marker + 5), "deadline");
    }

    private static void requireValidEvent(String command) throws Bot67Exception {
        int from = command.indexOf(" /from ");
        int to = command.indexOf(" /to ");
        if (from <= 6 || to <= from + 7 || command.substring(to + 5).trim().isEmpty()) {
            throw new Bot67Exception("Use: event <description> /from <start> /to <end>.");
        }
        validateDateTime(command.substring(from + 7, to), "event");
        validateDateTime(command.substring(to + 5), "event");
    }

    private static void validateDateTime(String value, String commandType) throws Bot67Exception {
        try {
            DateTimeParser.parse(value.trim());
        } catch (RuntimeException e) {
            // Keep Level 7's free-form values working, while parsing ISO values as Level 8 dates.
            if (value.trim().matches("\\d{4}-\\d{2}-\\d{2}.*")) {
                throw new Bot67Exception("Invalid " + commandType + " date/time. Use yyyy-MM-dd or yyyy-MM-ddTHH:mm.");
            }
        }
    }

    private static void saveTasks(Storage storage, java.util.List<Task> tasks) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
