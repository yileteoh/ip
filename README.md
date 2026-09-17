# Bot67

Bot67 is a desktop task manager with a cheerful six-seven personality. It supports todos, deadlines, events,
task completion, deletion, keyword search, alphabetical sorting, and persistent local storage.

See the [Bot67 User Guide](docs/README.md) for commands and usage details.

## Setting up in IntelliJ IDEA

### Before opening the project

Install the following:

- **JDK 25**. A Java Runtime Environment alone is not sufficient because IntelliJ and Gradle need the Java compiler.
- A recent version of **IntelliJ IDEA**, either Community or Ultimate Edition.
- **Git**, if you intend to clone the repository from GitHub instead of downloading it as a ZIP file.

You do not need to install Gradle separately. The repository includes the Gradle Wrapper, which downloads and uses
the correct Gradle version automatically.

### Opening the project

1. Clone this repository or download and extract it to a permanent folder.
2. Open IntelliJ IDEA. If another project is open, select **File → Close Project** first.
3. Select **Open** and choose the repository's root folder—the folder containing `build.gradle`, `gradlew`, and
   `gradlew.bat`.
4. If IntelliJ asks whether to trust the project, select **Trust Project**.
5. When prompted, choose to import the project using **Gradle** and accept the remaining defaults.
6. Wait for the progress indicators to finish. The first import can take longer because Gradle must download JavaFX,
   JUnit, Checkstyle, and the Shadow plugin.

Keep `src/main/java` as the source root and `src/test/java` as the test source root. IntelliJ normally detects these
folders automatically during the Gradle import, so they should not be moved or renamed.

### Configuring Java 25

Both IntelliJ and Gradle must use Java 25. Configuring only one of them can cause confusing compilation errors.

1. Open **File → Project Structure → Project**.
2. Set **SDK** to JDK 25. If it is not listed, select **Add SDK → JDK** and choose the installed JDK 25 folder.
3. Set **Language level** to **SDK default** and apply the changes.
4. Open **File → Settings → Build, Execution, Deployment → Build Tools → Gradle**. On macOS, open
   **IntelliJ IDEA → Settings** instead.
5. Set **Gradle JVM** to the same JDK 25 installation.
6. Select the **Reload All Gradle Projects** button in the Gradle tool window.

To verify the terminal configuration, open IntelliJ's **Terminal** tool window and run:

```text
java -version
```

The first line should begin with `java version "25` or `openjdk version "25`.

On macOS with the course SDKMAN setup, run `sdk use java 25.0.3.fx-zulu` before using the Gradle commands.

### Running Bot67

Use either of these methods:

- Open the Gradle tool window and run **Tasks → application → run**.
- Open IntelliJ's terminal and run `gradlew.bat run` on Windows or `./gradlew run` on macOS/Linux.

The Bot67 window should open with the application name, welcome message, command guide, input field, and Send button.
Try `todo read book`, followed by `list`, to verify that commands and saved data work.

### Running automated checks

Before submitting a change, run the full test and style checks from IntelliJ's terminal:

```text
gradlew.bat test checkstyleMain checkstyleTest
```

On macOS/Linux, replace `gradlew.bat` with `./gradlew`.

### Troubleshooting setup

- **Compilation errors immediately after opening:** wait for the Gradle import to finish, then reload the Gradle
  project. Restart IntelliJ if the errors remain.
- **Unsupported class-file or Java-version errors:** check both the Project SDK and Gradle JVM; both must use JDK 25.
- **JavaFX runtime components are missing:** launch Bot67 through the Gradle `run` task rather than running
  `Main.java` directly, so Gradle includes the JavaFX dependencies.
- **Gradle cannot download dependencies:** confirm that the computer has Internet access and that a firewall or proxy
  is not blocking Gradle.
- **`gradlew` is not executable on macOS/Linux:** run `chmod +x gradlew`, then try `./gradlew run` again.

## Building the JAR

To build the cross-platform fat JAR, run `./gradlew clean shadowJar`. The generated file is
`build/libs/bot67.jar`.

## Credits

- This project began from the NUS CS2103T individual-project template and follows the course's Duke tutorial
  progression.
- The JavaFX structure was adapted from the JavaFX tutorial supplied with the CS2103T individual-project template.
- `Background.png`, `Bot67.png`, and `User.png` were selected through Google Images from images represented as
  reusable. The original source links were not retained; attribution is recorded here transparently rather than
  claiming the artwork as original.
- Development used OpenAI Codex for code review, implementation support, test design, and documentation refinement.
