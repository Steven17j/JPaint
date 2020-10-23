package controller;

import model.Shapes.ShapeList;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DeleteCommand implements ICommand, IUndoable {
    private ShapeList shapes;
    private ArrayList<IShape> deleted;

    public DeleteCommand(ShapeList shapes) {
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        delete();
        CommandHistory.add(this);
    }

    private void delete() {
        if(!shapes.isSelected()) {
            deleted = new ArrayList<>(shapes.getSelectedShapesList().size());
            Iterator<IShape> deletedIter = shapes.createSelectedShapeIter();

            while(deletedIter.hasNext()) {
                deleted.add(deletedIter.next().clone());
            }

            for(IShape delShape: deleted) {
                Shape final_shape = delShape.getShapeParameters();
                shapes.getSelectedShapesList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
            }
            shapes.setSelected();
            shapes.notifyObservers();
        }
    }

    @Override
    public void undo() {
        for(IShape delShape: deleted) {
            shapes.getSelectedShapesList().add(delShape);
        }
        shapes.setSelected();
        shapes.notifyObservers();

    }

    @Override
    public void redo() {
        for(IShape delShape: deleted) {
            Shape final_shape = delShape.getShapeParameters();
            shapes.getSelectedShapesList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
        }
        shapes.setSelected();
        shapes.notifyObservers();
    }
}
