package model.Shapes;

import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.IShape;

import java.awt.*;

public class ShapeFactory {

    public IShape generateShape(Graphics2D graphics, ShapeOptions options, Point startPoint, Point endPoint) {
        IShape new_shape = null;

        if(options.shapeType == ShapeType.RECTANGLE) {
            new_shape = new Rectangle(graphics, options, startPoint, endPoint );
        } else if(options.shapeType == ShapeType.TRIANGLE) {
            new_shape = new Triangle(graphics, options, startPoint, endPoint);
        } else if(options.shapeType == ShapeType.ELLIPSE) {
            new_shape = new Elipse(graphics, options, startPoint, endPoint);
        }

        return new_shape;

    }
}
