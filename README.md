# Bot67

Bot67 is a desktop task manager with a cheerful six-seven personality. It supports todos, deadlines, events,
task completion, deletion, keyword search, alphabetical sorting, and persistent local storage.

See the [Bot67 User Guide](docs/README.md) for commands and usage details.

## Setting up in IntelliJ IDEA

Prerequisites: install JDK 25 and update IntelliJ IDEA to a recent version.

1. Open IntelliJ IDEA. If another project is open, select **File → Close Project** first.
2. Select **Open**, choose this repository's folder, and accept the default prompts.
3. Open **File → Project Structure → Project** and select JDK 25 as the project SDK. Keep the language level at
   **SDK default**.
4. Open **Settings → Build, Execution, Deployment → Build Tools → Gradle** and select JDK 25 as the Gradle JVM.
5. Let IntelliJ finish importing the Gradle project and downloading its dependencies.
6. Open the Gradle tool window and run **Tasks → application → run**. Alternatively, run
   `gradlew.bat run` in IntelliJ's terminal on Windows, or `./gradlew run` on macOS/Linux.

If IntelliJ shows compilation errors immediately after setup, reload the Gradle project or restart IntelliJ after
confirming both Java settings use JDK 25.

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
