import java.util.ArrayList;
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

        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage();

        System.out.println(banner);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bot67.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            try {
            if (command.equals("bye")) {
                System.out.println("67676767676767676767676767676767676767");
                System.out.println(sixSeven);
                System.out.println("Bye. Hope to see you again soon. Six Seven!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).getDescription());
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
                tasks.get(taskIndex).mark();
                saveTasks(storage, tasks);

                System.out.println("Six seven! I've marked this task as done:");
                System.out.println("  [X] " + tasks.get(taskIndex).getName());
            } else if (command.startsWith("unmark ")) {
                int taskNumber = parseTaskNumber(command.substring(7));
                int taskIndex = taskNumber - 1;
                tasks.get(taskIndex).unmark();
                saveTasks(storage, tasks);

                System.out.println("Six seven! I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks.get(taskIndex).getName());
            } else if (command.startsWith("delete ")) {
                int taskNumber = parseTaskNumber(command.substring(7));
                int taskIndex = taskNumber - 1;
                if (taskIndex >= tasks.size()) {
                    throw new Bot67Exception("Task number is out of range.");
                }
                Task deletedTask = tasks.remove(taskIndex);
                saveTasks(storage, tasks);
                System.out.println("Six seven. I've removed this task:");
                System.out.println("  " + deletedTask.getDescription());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.startsWith("todo ")) {
                requireText(command.substring(5));
                tasks.add(new Todo(command));
                saveTasks(storage, tasks);

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("deadline ")) {
                requireValidDeadline(command);
                tasks.add(new Deadline(command));
                saveTasks(storage, tasks);

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else if (command.startsWith("event ")) {
                requireValidEvent(command);
                tasks.add(new Event(command));
                saveTasks(storage, tasks);

                System.out.println("Six seven! I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1).getDescription());
                System.out.println("You have " + tasks.size() + " tasks in the list. 67!");
            } else {
                throw new Bot67Exception("I do not recognize that command.");
            }

            } catch (Bot67Exception e) {
                System.out.println("SIX SEVEN! " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("SIX SEVEN! I could not process that command. Please check its format.");
            }

            System.out.println("____________________________________________________________");
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
    }

    private static void requireValidEvent(String command) throws Bot67Exception {
        int from = command.indexOf(" /from ");
        int to = command.indexOf(" /to ");
        if (from <= 6 || to <= from + 7 || command.substring(to + 5).trim().isEmpty()) {
            throw new Bot67Exception("Use: event <description> /from <start> /to <end>.");
        }
    }

    private static void saveTasks(Storage storage, ArrayList<Task> tasks) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
