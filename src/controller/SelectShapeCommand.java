package controller;

import model.Shapes.ShapeList;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectShapeCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    private Point startPoint, endPoint;
    private ArrayList<IShape> undoSelectedShapes;
    private Iterator<IShape> undoSelectShapesList;

    public SelectShapeCommand(ShapeList shapeList, Point startPoint, Point endPoint) {
        this.shapeList = shapeList;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void execute() {
        selector();
        CommandHistory.add(this);
    }

    private void selector() {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int height = Math.abs(startPoint.y - endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        Rectangle rectangle = new Rectangle(x, y, width, height);
        int count = 0;

        for(IShape shape: shapeList.getCurrentShapeList()) {
            Shape temp = shape.getShapeParameters();
            if (rectangle.getBounds().intersects(temp.getBounds()) || temp.contains(x, y)) { count += 1; }
        }

        if(count == 0) {
            shapeList.deselectShapes();
            setUndoSelectShapes();
        }

        if(shapeList.isSelected()) {
            shapeList.addShapes(shapeList.getCurrentShapeList(), startPoint, endPoint);
            setUndoSelectShapes();
        } else if(!shapeList.isSelected()) {
            setUndoSelectShapes();
            shapeList.deselectShapes();
        }

    }

    private void setUndoSelectShapes() {
        undoSelectedShapes = new ArrayList<>(shapeList.getSelectedShapesList());
        undoSelectShapesList = shapeList.createSelectedShapeIter();
        while(undoSelectShapesList.hasNext()){
            undoSelectedShapes.add(undoSelectShapesList.next().clone());
        }
    }

    @Override
    public void undo() {
        if(!shapeList.isSelected()) {
            for(IShape shape: undoSelectedShapes) {
                Shape final_shape = shape.getShapeParameters();
                shapeList.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
            }
            shapeList.getCurrentShapeList().addAll(undoSelectedShapes);
            shapeList.getSelectedShapesList().removeAll(undoSelectedShapes);
        } else {
            shapeList.getSelectedShapesList().addAll(undoSelectedShapes);
            for(IShape shape: undoSelectedShapes) {
                Shape final_shape = shape.getShapeParameters();
                shapeList.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
            }
        }
        shapeList.setSelected();
    }

    @Override
    public void redo() {
        if(shapeList.isSelected()) {
            for(IShape shape: undoSelectedShapes) {
                Shape final_shape = shape.getShapeParameters();
                shapeList.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
            }
            shapeList.getSelectedShapesList().addAll(undoSelectedShapes);
        } else {
            for(IShape shape: undoSelectedShapes) {
                Shape final_shape = shape.getShapeParameters();
                shapeList.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
            }
            shapeList.getCurrentShapeList().addAll(undoSelectedShapes);
            shapeList.getSelectedShapesList().removeAll(undoSelectedShapes);
        }
        shapeList.setSelected();
    }
}
