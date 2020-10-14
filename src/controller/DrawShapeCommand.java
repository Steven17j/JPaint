package controller;


import model.ShapeType;
import model.Shapes.*;
import model.Shapes.Rectangle;
import model.interfaces.IShape;
import model.interfaces.IApplicationState;

import java.awt.*;

public class DrawShapeCommand implements  ICommand{

    private Graphics2D graphics;
    private IApplicationState state;
    private Point startPoint, endPoint;
    private ShapeList shapeList;
    private ShapeOptions shape_options = new ShapeOptions();
    private IShape new_Shape;

    public DrawShapeCommand(Graphics2D graphics, IApplicationState state, Point startPoint, Point endPoint) {
        this.graphics = graphics;
        this.state = state;
//        this.shapeList = shapes;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public  void execute() {
        state.getCurrentShapeOptions(shape_options);
        if(shape_options.shapeType == ShapeType.RECTANGLE) {
            Rectangle new_shape = new Rectangle(graphics, shape_options, startPoint, endPoint );
            new_shape.draw();
        } else if(shape_options.shapeType == ShapeType.TRIANGLE) {
            Triangle new_shape = new Triangle(graphics, shape_options, startPoint, endPoint);
            new_shape.draw();
        } else if(shape_options.shapeType == ShapeType.ELLIPSE) {
            Elipse new_shape = new Elipse(graphics, shape_options, startPoint, endPoint);
            new_shape.draw();
        }

    }
}
