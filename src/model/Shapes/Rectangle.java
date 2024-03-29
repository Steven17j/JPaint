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
    public void drawOutline(Color color) {
        int x2 = (int) Math.min(startPoint.getX(), endPoint.getX());
        int y2 = (int) Math.min(startPoint.getY(), endPoint.getY());
        int height2 = getShapeHeight() + 10;
        int width2 = getShapeWidth() + 10;
        java.awt.Rectangle outline = new java.awt.Rectangle(x2-5, y2-5, width2, height2);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(
                4.0f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f,
                new float[] {16.0f, 20.0f}, 0.0f));
        graphics.drawRect(outline.x, outline.y, width2, height2);

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
    public String getShapeName() { return "Rectangle"; }

    @Override
    public IShape clone() {
        IShape clone;
        clone = (IShape) super.clone();
        return clone;
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
