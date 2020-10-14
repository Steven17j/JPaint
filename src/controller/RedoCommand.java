package controller;


public class RedoCommand implements  ICommand{

    @Override
    public void execute() {
        CommandHistory.redo();
    }
}
