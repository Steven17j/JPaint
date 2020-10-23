package model.interfaces;


import model.Shapes.ShapeOptions;

import java.awt.*;

public interface IShape extends Shape {

    void draw();

    Shape getShapeParameters();

    Point getStartPoint();

    Point getEndPoint();

    void setStartAndEndPoint(Point startPoint, Point endPoint);

    int getShapeWidth();

    int getShapeHeight();

    void getCurrentShapeOptions();

    void setNewShapeOptions(ShapeOptions shape_option);

    String getShapeName();

    IShape clone();

    String toString();
}
