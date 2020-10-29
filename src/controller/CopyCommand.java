package controller;

import model.Shapes.ShapeList;

public class CopyCommand implements  ICommand, IUndoable{
    private ShapeList shapes;

    public CopyCommand(ShapeList shapes) {
        this.shapes = shapes;
    }


    @Override
    public void execute() {
        copy();
        System.out.println("You copied a shape");
        CommandHistory.add(this);
    }

    private void copy() {
        if(!shapes.isCanCopy()) {
            shapes.setShapeClipBoard();
        } else if(shapes.isCanCopy()) {
            shapes.clearShapeClipBoard();
        }
    }

    @Override
    public void undo() { shapes.clearShapeClipBoard(); }

    @Override
    public void redo() { shapes.setShapeClipBoard(); }
}
