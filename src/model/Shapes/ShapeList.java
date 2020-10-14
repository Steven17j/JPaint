package model.Shapes;



import model.interfaces.IShape;

import java.awt.Rectangle;
import java.util.ArrayList;

public class ShapeList extends Rectangle {

    private ArrayList<Object> currentShapeList;
    private ArrayList<Object> selectedShapesList;


    public ShapeList() {
        currentShapeList = new ArrayList<>();
        selectedShapesList = new ArrayList<>();
    }


    public void addShapes(ArrayList<Object> shapes, Object shape) {
        shapes.add(shape);
    }

    public ArrayList<Object> getCurrentShapeList(){ return currentShapeList; }


}
