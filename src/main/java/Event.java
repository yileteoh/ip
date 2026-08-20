public class Event extends Task{
    private final String from;
    private final String to;

    public Event(String command){
        super(command.substring(6, command.indexOf(" /from ")), TaskType.EVENT);
        this.from = command.substring(command.indexOf(" /from ") + 7,  command.indexOf(" /to "));
        this.to = command.substring(command.indexOf(" /to ") + 5);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}