package bot67.ui;

import java.io.PrintStream;

/**
 * Handles output for Bot67's command-line interface.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private final PrintStream output;

    /** Creates a UI that writes to the console. */
    public Ui() {
        this(System.out);
    }

    /** Creates a UI that writes to the supplied stream. */
    public Ui(PrintStream output) {
        this.output = output;
    }

    /** Displays the startup greeting. */
    public void showWelcome(String banner) {
        output.println(banner);
        showSeparator();
        output.println("Hello! I'm Bot67.");
        output.println("What can I do for you?");
        showSeparator();
    }

    /** Displays the goodbye message and personality art. */
    public void showGoodbye(String personalityArt) {
        output.println("67676767676767676767676767676767676767");
        output.println(personalityArt);
        output.println("Bye. Hope to see you again soon. Six Seven!");
        showSeparator();
    }

    /** Displays a separator between UI interactions. */
    public void showSeparator() {
        output.println(SEPARATOR);
    }

    /** Displays an error message using Bot67's standard prefix. */
    public void showError(String message) {
        output.println("SIX SEVEN! " + message);
    }

    /** Displays one or more lines of normal application output in the supplied order. */
    public void showLine(String... messages) {
        for (String message : messages) {
            output.println(message);
        }
    }
}
