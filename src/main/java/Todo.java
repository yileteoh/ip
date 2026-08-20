public class Todo extends Task{

    public Todo(String command){
        super(command.substring(5));
    }

    @Override
    public String getDescription(){
        return "[T][" + super.getStatusIcon() + "] " + super.getName();
    }
}
