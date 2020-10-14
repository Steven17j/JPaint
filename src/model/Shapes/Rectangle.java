package model.Shapes;

import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IShape;
import model.ColorDictionary;

import java.awt.*;

public class Rectangle extends java.awt.Rectangle implements IShape {

    private Shape shapeType;
    private Point startPoint;
    private Point endPoint;
    private ShapeOptions shape_options;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shapeShadingType;
    private Graphics2D graphics;


    public Rectangle(Graphics2D graphics, ShapeOptions shape_options, Point start, Point end) {

        this.startPoint = start;
        this.endPoint = end;
        this.shapeShadingType = shape_options.shapeShadingType;
        this.primaryColor = shape_options.primaryColor;
        this.secondaryColor = shape_options.secondaryColor;
        this.graphics = graphics;
    }

    @Override
    public void draw() {
        int x = (int) Math.min(startPoint.getX(), endPoint.getX());
        int y = (int) Math.min(startPoint.getY(), endPoint.getY());
        int height = getShapeHeight();
        int width = getShapeWidth();
        java.awt.Rectangle rectangle = new java.awt.Rectangle(x, y, width, height);
        this.shapeType = rectangle;
        ColorDictionary primary = new ColorDictionary(primaryColor);
        ColorDictionary secondary = new ColorDictionary(secondaryColor);
        graphics.setColor(primary.getColor());

        if(shapeShadingType == shapeShadingType.FILLED_IN || shapeShadingType == shapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillRect(rectangle.x,rectangle.y, width, height);
        }
        graphics.setStroke(new BasicStroke((5)));
        graphics.setColor(secondary.getColor());
        if (shapeShadingType == ShapeShadingType.OUTLINE || shapeShadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawRect(rectangle.x, rectangle.y, width, height);
        }
    }

    @Override
    public Shape getShapeParameters() { return shapeType; }

    @Override
    public Point getStartPoint() { return startPoint; }

    @Override
    public Point getEndPoint() { return endPoint; }

    @Override
    public void setStartAndEndPoint(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint =  endPoint;
    }

    @Override
    public int getShapeWidth() { return (int) Math.abs(startPoint.getX() - endPoint.getX()); }

    @Override
    public int getShapeHeight() { return (int) Math.abs(startPoint.getY() - endPoint.getY()); }

    @Override
    public void getCurrentShapeOptions() {
        this.primaryColor = shape_options.primaryColor;
        this.secondaryColor = shape_options.secondaryColor;
        this.shapeShadingType = shape_options.shapeShadingType;
    }

    @Override
    public void setNewShapeOptions(ShapeOptions shape_option) {
        this.primaryColor = shape_option.primaryColor;
        this.secondaryColor = shape_option.secondaryColor;
        this.shapeShadingType = shape_option.shapeShadingType;

    }

    @Override
    public String toString() {
        return "Rectangle {" +
                "shapeType =" + shapeType +
                ", startPoint =" + startPoint +
                ", endPoint =" + endPoint +
                ", shapeShadingType =" + shapeShadingType +
                ", primaryColor =" + primaryColor +
                ", secondaryColor =" + secondaryColor +
                '}';
    }
}
