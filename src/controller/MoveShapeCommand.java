package controller;

import model.Shapes.ShapeList;
import model.interfaces.IShape;

import java.awt.*;
import java.util.Iterator;

public class MoveShapeCommand implements ICommand, IUndoable {

    private Point startPoint, endPoint;
    private ShapeList shapes;
    private int delta_x, delta_y;
    private Iterator<IShape> selectedShapesIter;

    public MoveShapeCommand(Point startPoint, Point endPoint, ShapeList shapes) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapes = shapes;
    }

    private void move(Iterator<IShape> selectedShapes) {
        while(selectedShapes.hasNext()) {
            IShape shape = selectedShapes.next();
            Point dStart = new Point((int)(shape.getStartPoint().getX() + delta_x), (int)(shape.getStartPoint().getY() + delta_y));
            Point dEnd = new Point(shape.getShapeWidth() + (int) dStart.getX(), shape.getShapeHeight() + (int) dStart.getY());
            shape.setStartAndEndPoint(dStart, dEnd);
        }
        shapes.notifyObservers();
    }

    public void move() {
        delta_x = (int) (endPoint.getX() - startPoint.getX());
        delta_y = (int) (endPoint.getY() - startPoint.getY());
        selectedShapesIter = shapes.createSelectedShapeIter();

        move(selectedShapesIter);
    }

    @Override
    public void execute() {
        if(shapes.getSelectedShapesList().isEmpty()) {
            System.out.println("No shapes were selected");
        } else {
            move();
            CommandHistory.add(this);
        }
    }

    @Override
    public void undo() {
        undo_helper(delta_x, delta_y);
    }

    private void undo_helper(int delta_x, int delta_y) {
        selectedShapesIter = shapes.createSelectedShapeIter();
        while(selectedShapesIter.hasNext()) {
            IShape shape = selectedShapesIter.next();
            Point start_delta = new Point((int)(shape.getStartPoint().getX() - delta_x), (int)(shape.getStartPoint().getY() - delta_y));
            Point end_delta = new Point((int) (shape.getEndPoint().getX() - delta_x), (int) (shape.getEndPoint().getY() - delta_y));
            shape.setStartAndEndPoint(start_delta, end_delta);
        }
        shapes.notifyObservers();
    }

    @Override
    public void redo() {
        redo_helper(delta_x, delta_y);
    }

    private void redo_helper(int delta_x, int delta_y) {
        selectedShapesIter = shapes.createSelectedShapeIter();
        while(selectedShapesIter.hasNext()) {
            IShape shape = selectedShapesIter.next();
            Point start_delta = new Point((int)(shape.getStartPoint().getX() + delta_x), (int)(shape.getStartPoint().getY() + delta_y));
            Point end_delta = new Point((int) (shape.getEndPoint().getX() + delta_x), (int) (shape.getEndPoint().getY() + delta_y));
            shape.setStartAndEndPoint(start_delta, end_delta);
        }
        shapes.notifyObservers();
    }
}
