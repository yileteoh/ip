# Text UI test plan

The application is compiled with Java 25 and run as `Bot67`. Each test ends with `bye` so the session terminates cleanly.

## Test 1: Add and list all Level-4 task types

Aim: Verify that ToDos, Deadlines, and Events are added and listed with the correct type and date information.

Input commands:

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

Expected output checkpoints, in order:

```text
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

Console record: passed. The three add confirmations and the final `list` output matched the checkpoints above.

## Test 2: Mark and unmark typed tasks

Aim: Verify that marking and unmarking work for typed tasks without losing their type or date information.

Input commands:

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 2
unmark 2
list
bye
```

Expected output checkpoints, in order:

```text
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

Console record: passed. The `mark 2` and `unmark 2` confirmations appeared, and the final `list` showed task 2 as `[D][ ]` with its deadline preserved.

## Test 3: Required Level-5 errors

Aim: Verify that empty todos and unknown commands produce errors without terminating the application.

Input commands:

```text
todo
blah
todo valid task
list
bye
```

Expected output checkpoints, in order:

```text
SIX SEVEN! A todo description cannot be empty.
SIX SEVEN! I do not recognize that command.
1.[T][ ] valid task
```

## Test 4: Malformed commands and invalid task numbers

Aim: Verify that malformed Level-4 commands and invalid task numbers are handled safely.

Input commands:

```text
deadline
event meeting
mark abc
unmark 0
todo valid task
list
bye
```

Expected output checkpoints, in order:

```text
SIX SEVEN! Use: deadline <description> /by <date or time>.
SIX SEVEN! Use: event <description> /from <start> /to <end>.
SIX SEVEN! Task number must be a whole number.
SIX SEVEN! Task number must be between 1 and 100.
1.[T][ ] valid task
```
