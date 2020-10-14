package controller;

public class UndoCommand implements ICommand {
    @Override
    public void execute() {
        CommandHistory.undo();
    }
}
