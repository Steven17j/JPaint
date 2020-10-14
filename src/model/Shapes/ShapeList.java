package model.Shapes;

import model.interfaces.IShape;
import model.interfaces.IShapeIterator;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeList extends Rectangle implements IShapeIterator {
    private ArrayList<IShape> currentShapeList;
    private ArrayList<IShape> selectedShapesList;
    private boolean selectReady = true;

    public ShapeList() {
        currentShapeList = new ArrayList<>();
        selectedShapesList = new ArrayList<>();
    }

    public void setSelectReady(){selectReady = !selectReady;}

    public void addShapes(ArrayList<IShape> shapes, Point startPoint, Point endPoint){
        int x = (int) Math.min(startPoint.getX(), endPoint.getX());
        int y = (int) Math.min(startPoint.getY(), endPoint.getY());
        int width = (int) Math.abs(startPoint.getX() - endPoint.getX());
        int height = (int) Math.abs(startPoint.getY() - endPoint.getY());
        Rectangle rect = new Rectangle(x, y, width, height);

        for(IShape shape: shapes){
            Shape s = shape.getShapeParameters();
            if (rect.getBounds().intersects(s.getBounds()) || s.contains(x, y)){
                selectedShapesList.add(shape);
            }
        }
        // remove selected shapes from currentShapeList
        for (IShape shape: selectedShapesList) {
            Shape finalS = shape.getShapeParameters();
            currentShapeList.removeIf((IShape i) -> i.getShapeParameters().equals(finalS));
        }
        setSelectReady();
    }

    public ArrayList<IShape> getCurrentShapeList(){ return currentShapeList; }
    @Override
    public Iterator<IShape> createCurrentShapeIterator() { return currentShapeList.iterator(); }

    @Override
    public Iterator<IShape> createSelectedShapeIterator() { return selectedShapesList.iterator(); }
}
