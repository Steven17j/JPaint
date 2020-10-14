package controller;


import model.ShapeType;
import model.Shapes.*;
import model.interfaces.IShape;
import model.interfaces.IApplicationState;

import java.awt.*;
import java.util.ArrayList;

public class DrawShapeCommand implements  ICommand{

    private Graphics2D graphics;
    private IApplicationState state;
    private Point startPoint, endPoint;
    private ShapeList shapeList;
    private ShapeOptions shape_options = new ShapeOptions();

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
    }
}
