/**
 * Handles output for Bot67's command-line interface.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";

    /** Displays the startup greeting. */
    public void showWelcome(String banner) {
        System.out.println(banner);
        showSeparator();
        System.out.println("Hello! I'm Bot67.");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    /** Displays the goodbye message and personality art. */
    public void showGoodbye(String personalityArt) {
        System.out.println("67676767676767676767676767676767676767");
        System.out.println(personalityArt);
        System.out.println("Bye. Hope to see you again soon. Six Seven!");
        showSeparator();
    }

    /** Displays a separator between UI interactions. */
    public void showSeparator() {
        System.out.println(SEPARATOR);
    }

    /** Displays an error message using Bot67's standard prefix. */
    public void showError(String message) {
        System.out.println("SIX SEVEN! " + message);
    }
}
