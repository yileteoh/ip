# Bot67 User Guide

Bot67 is a desktop task manager that accepts short text commands. It stores your tasks between sessions.

## Sorting tasks

Enter `sort` to arrange all tasks alphabetically by their descriptions. Sorting is case-insensitive, keeps each
task's type, completion status, and date information, and saves the new order.

Example: `sort`

```text
Six seven! I've sorted your tasks alphabetically:
1.[D][ ] Apple task (by: Oct 15 2026)
2.[E][X] banana task (from: Mon 2pm to: 4pm)
3.[T][ ] zebra task
```

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details

## Command validation

Commands are case-sensitive. Extra spaces and tabs are collapsed to one space, including in task descriptions.
Use `list` to find a task's current number. Task numbers must be positive whole numbers in the current list.
`list`, `sort`, and `bye` take no arguments.

Use `/by` once for a deadline, and `/from` followed by `/to` once each for an event, with spaces around each marker.
Descriptions and date values cannot be empty. Pipe characters (`|`) and control characters other than tabs are
not accepted because tasks are stored as one line with pipe-separated fields.

Dates accept `yyyy-MM-dd`, `yyyy-MM-ddTHH:mm`, or `yyyy-MM-dd HH:mm`.
Impossible dates and times are rejected. When both event endpoints are ISO dates, the start must be before the end;
a date without a time means midnight. Free-form values such as `Sunday` and `Mon 2pm` are still supported,
but their ordering cannot be checked. Duplicate tasks are allowed, including tasks with the same details.

## Problems loading or saving tasks

Tasks are stored in `data/duke.txt`, relative to the folder where you start Bot67. A missing file starts an empty list.
If the file cannot be read or contains an invalid task, Bot67 shows a startup warning and loads no tasks.
Changes are disabled to protect the original file. Back up the file, fix the reported record or file permissions,
then restart Bot67. A damaged record warning includes its line number.

If a save fails, the requested change is undone. Check the file and folder permissions, available disk space,
and that `data/duke.txt` is a file rather than a folder, then retry. Saving requires a filesystem that supports
atomic file replacement. Bot67 writes a complete temporary file before replacing the previous save.
