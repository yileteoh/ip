public class Deadline extends Task{
    private final String deadline;

    public Deadline(String command){
        super(command.substring(9, command.indexOf(" /by ")), TaskType.DEADLINE);
        this.deadline = command.substring(command.indexOf(" /by ") + 5);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " (by: " + deadline + ")";
    }
}
