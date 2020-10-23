package controller;

import model.Shapes.ShapeList;
import model.Shapes.ShapeOptions;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;

import java.util.Iterator;

public class SelectChangeCommand implements ICommand, IUndoable{

    private IApplicationState state;
    private ShapeOptions shapeOptions = new ShapeOptions();
    private ShapeList shapeList;
    private Iterator<IShape> selectedShapes;

    public SelectChangeCommand(IApplicationState state, ShapeList shapeList) {
        this.state = state;
        this.shapeList = shapeList;
    }


    @Override
    public void execute() {
        changeShapes();
        CommandHistory.add(this);
    }

    private void changeShapes() {
        state.getCurrentShapeOptions(shapeOptions);
        selectedShapes = shapeList.createSelectedShapeIter();
        while(selectedShapes.hasNext()) {
            IShape s = selectedShapes.next();
            s.setNewShapeOptions(shapeOptions);
        }
        shapeList.notifyObservers();
    }

    @Override
    public void undo() {
        selectedShapes = shapeList.createSelectedShapeIter();
        while(selectedShapes.hasNext()) {
            IShape s = selectedShapes.next();
            s.getCurrentShapeOptions();
        }
        shapeList.notifyObservers();
    }

    @Override
    public void redo() {
        selectedShapes = shapeList.createSelectedShapeIter();
        while(selectedShapes.hasNext()){
            IShape s = selectedShapes.next();
            s.setNewShapeOptions(shapeOptions);
        }
        shapeList.notifyObservers();
    }
}
