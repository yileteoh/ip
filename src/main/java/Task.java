public class Task {
    private final String name;
    private final TaskType type;
    private boolean isDone;

    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public TaskType getType() {
        return this.type;
    }

    public String getDescription(){
        return "[" + this.getType().getSymbol() + "][" + this.getStatusIcon() + "] " + this.getName();
    }
}
