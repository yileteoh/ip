# Bot67 User Guide

Bot67 is a desktop task manager that accepts short text commands and stores your tasks between sessions.

![Bot67 task manager interface](Ui.png)

## Quick start

Type a command into the box at the bottom of the window and press **Enter** or **Send**. Commands are
case-sensitive. Replace values in angle brackets with your own text and omit the brackets.

| Action | Command | Example |
|---|---|---|
| Add a todo | `todo <description>` | `todo read book` |
| Add a deadline | `deadline <description> /by <date or time>` | `deadline submit report /by 2026-10-15` |
| Add an event | `event <description> /from <start> /to <end>` | `event meeting /from 2026-10-15T14:00 /to 2026-10-15T16:00` |
| Show tasks | `list` | `list` |
| Find tasks | `find <keyword>` | `find report` |
| Sort tasks | `sort` | `sort` |
| Complete a task | `mark <task number>` | `mark 2` |
| Reopen a task | `unmark <task number>` | `unmark 2` |
| Delete a task | `delete <task number>` | `delete 2` |
| Exit | `bye` | `bye` |

## Adding tasks

### Adding a todo: `todo`

Use a todo for a task without a fixed date or time.

- Format: `todo <description>`
- Example: `todo borrow a library book`
- Result: Bot67 adds `[T][ ] borrow a library book` and reports the new number of tasks.

### Adding a deadline: `deadline`

Use a deadline for work that must be completed by a particular date or time.

- Format: `deadline <description> /by <date or time>`
- Example: `deadline submit report /by 2026-10-15 18:00`
- Result: Bot67 adds `[D][ ] submit report (by: Oct 15 2026 18:00)`.

The `/by` marker is required and may appear only once.

### Adding an event: `event`

Use an event for an activity with a start and an end.

- Format: `event <description> /from <start> /to <end>`
- Example: `event project meeting /from 2026-10-15T14:00 /to 2026-10-15T16:00`
- Result: Bot67 adds an `[E]` task and displays both endpoints in a readable format.

The `/from` marker must come before `/to`, and each marker may appear only once.

## Viewing and organizing tasks

### Showing the task list: `list`

Enter `list` to display every task in its current numbered order. For example:

```text
1.[T][ ] borrow a library book
2.[D][ ] submit report (by: Oct 15 2026 18:00)
3.[E][X] project meeting (from: Oct 15 2026 14:00 to: Oct 15 2026 16:00)
```

The first letter identifies the type: `[T]` for todo, `[D]` for deadline, and `[E]` for event. The second pair of
brackets shows status: `[ ]` means incomplete and `[X]` means completed.

### Finding tasks: `find`

- Format: `find <keyword>`
- Example: `find report`

Bot67 shows every task whose displayed description contains the exact keyword and keeps each task's original list
number. If nothing matches, Bot67 says so without changing the task list.

### Sorting tasks: `sort`

Enter `sort` to arrange all tasks alphabetically by description. Sorting is case-insensitive and preserves task
types, completion states, dates, and event times. The sorted order is saved automatically.

## Updating tasks

### Marking a task as completed: `mark`

- Format: `mark <task number>`
- Example: `mark 2`

Bot67 changes the selected task's status from `[ ]` to `[X]`.

### Marking a task as incomplete: `unmark`

- Format: `unmark <task number>`
- Example: `unmark 2`

Bot67 changes the selected task's status from `[X]` back to `[ ]`.

### Deleting a task: `delete`

- Format: `delete <task number>`
- Example: `delete 2`

Bot67 displays the removed task and the number of remaining tasks. Tasks below it are renumbered immediately.

Task numbers can also change after sorting, so use `list` before `mark`, `unmark`, or `delete` when unsure.

## Exiting Bot67

Enter `bye` to close Bot67. Your latest successful changes are already saved, so no separate save command is needed.

## Dates and times

Bot67 accepts these structured formats:

- `yyyy-MM-dd`, such as `2026-10-15`
- `yyyy-MM-ddTHH:mm`, such as `2026-10-15T14:00`
- `yyyy-MM-dd HH:mm`, such as `2026-10-15 14:00`

Structured dates are checked for impossible values. For events with two structured values, the start must be before
the end. Free-form values such as `Sunday` and `Mon 2pm` are also accepted, although Bot67 cannot compare their order.

## Command rules

Extra spaces and tabs are collapsed to one space, including inside task descriptions. Use `/by` exactly once for a
deadline and `/from` followed by `/to` exactly once for an event, with spaces around each marker. Descriptions and
date values cannot be empty. The pipe character (`|`) and control characters other than tabs are rejected because
saved tasks use pipe-separated records. Duplicate tasks are allowed.

`list`, `sort`, and `bye` take no arguments. Task numbers must be positive whole numbers that exist in the current
list. When a command is invalid, Bot67 highlights the error and provides specific guidance; correct the command and
try again.

## Saved tasks and recovery

Tasks are stored in `data/bot67.txt`, relative to the folder where Bot67 starts. Existing tasks from the previous
`data/duke.txt` location are imported automatically when the Bot67 file does not exist. New changes are saved to
`data/bot67.txt`, while the legacy file is retained as a backup.

A missing save file starts an empty list. If a file cannot be read or contains an invalid task, Bot67 loads no tasks
and disables changes to protect the original data. Back up the file, fix the reported record or permissions, and
restart Bot67. If saving fails, the requested change is undone so the in-memory list remains consistent.

## Troubleshooting

### Bot67 says a task number is out of range

Run `list` and use one of the numbers currently shown. Deleting or sorting tasks can change their numbers.

### A date or time is rejected

Check that the date exists and that its month, day, hour, and minute use the required number of digits. For example,
use `2026-02-28`, not the impossible date `2026-02-30`. For an event, also ensure the structured start is earlier
than the structured end.

### Changes are disabled at startup

Bot67 could not safely read the save file. Follow the path shown in the error, back up that file, and correct its
contents or permissions before restarting. Bot67 deliberately avoids overwriting data it could not understand.

### A change could not be saved

The attempted change was rolled back. Check that the `data` folder is writable, `data/bot67.txt` is a regular file,
and the drive has available space, then retry the command.

## Credits

- Bot67 was developed from the NUS CS2103T individual-project template and its JavaFX tutorial.
- The GUI images were selected through Google Images from images represented as reusable. Original source links were
  not retained; they are acknowledged here rather than presented as original artwork.
- OpenAI Codex assisted with implementation, testing, review, and documentation.
