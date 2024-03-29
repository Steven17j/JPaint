package model.Shapes;

import model.ColorDictionary;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.IShape;

import java.awt.*;

public class Triangle extends Polygon implements IShape, Cloneable {
    private Graphics2D graphics;
    private Point startPoint;
    private Point endPoint;
    private ShapeOptions options;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private Shape type;

    public Triangle(Graphics2D graphics, ShapeOptions options, Point startPoint, Point endPoint) {
        this.graphics = graphics;
        this.options = options;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.primaryColor = options.primaryColor;
        this.secondaryColor = options.secondaryColor;
        this.shadingType = options.shapeShadingType;
    }

    @Override
    public void draw() {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int a = Math.max(startPoint.x, endPoint.x);
        int b = Math.max(startPoint.y, endPoint.y);

        int[] xPoints = new int[]{x, ((x+a) / 2), a};
        int[] yPoints = new int[]{b, y, b};

        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        this.type = triangle;
        ColorDictionary primary = new ColorDictionary(primaryColor);
        ColorDictionary secondary = new ColorDictionary(secondaryColor);
        graphics.setColor(primary.getColor());
        if (shadingType == ShapeShadingType.FILLED_IN || shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.fillPolygon(triangle.xpoints,triangle.ypoints, triangle.npoints);
        }
        graphics.setStroke(new BasicStroke(5));
        graphics.setColor(secondary.getColor());
        if(shadingType == ShapeShadingType.OUTLINE || shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics.drawPolygon(triangle.xpoints, triangle.ypoints, triangle.npoints);
        }
    }

    @Override
    public void drawOutline(Color color) {
        int x1 = Math.min(startPoint.x, endPoint.x);
        int y1 = Math.min(startPoint.y, endPoint.y);
        int a1 = Math.max(startPoint.x, endPoint.x);
        int b1 = Math.max(startPoint.y, endPoint.y);

        int[] xPoints = new int[]{x1-10, ((x1+a1) / 2), a1+10};
        int[] yPoints = new int[]{b1+10, y1-20, b1+10};

        Polygon outline = new Polygon(xPoints, yPoints, 3);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(
                4.0f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f,
                new float[] {16.0f, 20.0f}, 0.0f));
        graphics.drawPolygon(outline.xpoints, outline.ypoints, outline.npoints);
    }

    @Override
    public Shape getShapeParameters() {
        return type;
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public void setStartAndEndPoint(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int getShapeWidth() {
        return Math.abs(startPoint.x - endPoint.x);
    }

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
    public String getShapeName() { return "Triangle"; }

    @Override
    public IShape clone() {
        IShape clone;
        try {
            clone = (IShape) super.clone();
        } catch (CloneNotSupportedException exp) {
            throw new RuntimeException(exp);
        }
        return clone;
    }

    @Override
    public String toString() {

        return "Triangle {" +
                "shapeType =" + type +
                ", startPoint =" + startPoint +
                ", endPoint =" + endPoint +
                ", shapeShadingType =" + shadingType +
                ", primaryColor =" + primaryColor +
                ", secondaryColor =" + secondaryColor +
                '}';
    }
}
