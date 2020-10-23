package controller;


import model.ShapeType;
import model.Shapes.*;
import model.interfaces.IShape;
import model.interfaces.IApplicationState;

import java.awt.*;


public class DrawShapeCommand implements  ICommand, IUndoable{

    private Graphics2D graphics;
    private IApplicationState state;
    private Point startPoint, endPoint;
    private ShapeList shapeList;
    private ShapeOptions shape_options = new ShapeOptions();
    private IShape newShape;

    public DrawShapeCommand(Graphics2D graphics, IApplicationState state, Point startPoint, Point endPoint, ShapeList shapes) {
        this.graphics = graphics;
        this.state = state;
        this.shapeList = shapes;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public  void execute() {
        state.getCurrentShapeOptions(shape_options);
        ShapeFactory factory = new ShapeFactory();
        IShape new_shape = factory.generateShape(graphics, shape_options, startPoint, endPoint);
        new_shape.draw();
        shapeList.getCurrentShapeList().add(new_shape);
        shapeList.notifyObservers();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        Shape final_shape = newShape.getShapeParameters();
        shapeList.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
        shapeList.notifyObservers();
    }

    @Override
    public void redo() {
        shapeList.getCurrentShapeList().add(newShape);
        shapeList.notifyObservers();

    }
}
