# Bot67

Bot67 is a desktop task manager with a cheerful six-seven personality. It supports todos, deadlines, events,
task completion, deletion, keyword search, alphabetical sorting, and persistent local storage.

See the [Bot67 User Guide](docs/README.md) for commands and usage details.

## Running Bot67

Bot67 requires Java 25.

1. Clone this repository and open it in IntelliJ IDEA as a Gradle project.
2. Configure the project SDK and Gradle JVM to use JDK 25.
3. Run `./gradlew run` on macOS/Linux or `gradlew.bat run` on Windows.

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
