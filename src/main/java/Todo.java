public class Todo extends Task{

    public Todo(String command){
        super(command.substring(5), TaskType.TODO);
    }

    @Override
    public String getDescription(){
        return super.getDescription();
    }
}
