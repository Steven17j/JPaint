package model.Shapes;

import model.ColorDictionary;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IShape;

import java.awt.*;

public class Elipse extends java.awt.Rectangle implements IShape {

    private Graphics2D graphics;
    private Shape type;
    private ShapeOptions options;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private Point startPoint;
    private Point endPoint;
    private ShapeShadingType shadingType;

    public Elipse(Graphics2D graphics, ShapeOptions options, Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shadingType = options.shapeShadingType;
        this.primaryColor = options.primaryColor;
        this.secondaryColor = options.secondaryColor;
        this.graphics = graphics;
    }

    @Override
    public void draw() {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = getShapeWidth();
        int height = getShapeHeight();

        java.awt.Rectangle elipse = new java.awt.Rectangle(x, y, width, height);
        this.type = elipse;
        ColorDictionary primary = new ColorDictionary(primaryColor);
        ColorDictionary secondary = new ColorDictionary(secondaryColor);
        graphics.setColor(primary.getColor());
        if(shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN || shadingType == ShapeShadingType.FILLED_IN) {
            graphics.fillOval(elipse.x, elipse.y, elipse.width, elipse.height);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(secondary.getColor());
        if(shadingType == ShapeShadingType.OUTLINE || shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawOval(elipse.x, elipse.y, elipse.width, elipse.height);
        }
    }

    @Override
    public void drawOutline(Color color) {
        int x1 = (int) Math.min(startPoint.getX(), endPoint.getX());
        int y1 = (int) Math.min(startPoint.getY(), endPoint.getY());
        int height1 = getShapeHeight() + 10;
        int width1 = getShapeWidth() + 10;
        java.awt.Rectangle outline = new java.awt.Rectangle(x1-5, y1-5, width1, height1);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(
                4.0f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f,
                new float[] {16.0f, 20.0f}, 0.0f));
        graphics.drawOval(outline.x, outline.y, outline.width, outline.height);
    }

    @Override
    public Shape getShapeParameters() { return type; }

    @Override
    public Point getStartPoint() { return startPoint; }

    @Override
    public Point getEndPoint() { return endPoint; }

    @Override
    public void setStartAndEndPoint(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int getShapeWidth() { return Math.abs(startPoint.x - endPoint.x); }

    @Override
    public int getShapeHeight() { return Math.abs(startPoint.y - endPoint.y); }

    @Override
    public void getCurrentShapeOptions() {
        this.primaryColor = options.primaryColor;
        this.secondaryColor = options.secondaryColor;
        this.shadingType = options.shapeShadingType;
    }

    @Override
    public void setNewShapeOptions(ShapeOptions shape_option) {
        this.primaryColor = shape_option.primaryColor;
        this.secondaryColor = shape_option.secondaryColor;
        this.shadingType = shape_option.shapeShadingType;
    }

    @Override
    public String getShapeName() { return "Ellipse"; }

    @Override
    public IShape clone() {
        IShape clone;
        clone = (IShape) super.clone();
        return clone;
    }

    @Override
    public String toString() {

        return "Elipse {" +
                "shapeType =" + type +
                ", startPoint =" + startPoint +
                ", endPoint =" + endPoint +
                ", shapeShadingType =" + shadingType +
                ", primaryColor =" + primaryColor +
                ", secondaryColor =" + secondaryColor +
                '}';
    }
}
