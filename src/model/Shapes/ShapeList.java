package model.Shapes;



import model.interfaces.IShape;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeList extends Rectangle implements IShapeList, IShapeSubject {

    private ArrayList<IShape> currentShapeList;
    private ArrayList<IShape> selectedShapesList;
    private ArrayList<IShape> shapeClipBoard;
    private PaintCanvas paintCanvas;
    private boolean selected = true;
    private boolean canCopy = false;

    public ShapeList() {
        currentShapeList = new ArrayList<>();
        selectedShapesList = new ArrayList<>();
        shapeClipBoard = new ArrayList<>();
    }
    public boolean isCanCopy(){ return canCopy; }

    public void setCanCopy() { canCopy =! canCopy; }

    public boolean isSelected() { return selected; }

    public void setSelected() { selected =! selected;}

    public void addShapes(ArrayList<IShape> shapes, Point start, Point end) {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int height = Math.abs(start.y - end.y);
        int width = Math.abs(start.x - end.x);
        Rectangle rectangle = new Rectangle(x, y, width, height);

        for(IShape shape: shapes) {
            Shape temp = shape.getShapeParameters();
            if (rectangle.getBounds().intersects(temp.getBounds()) || temp.contains(x, y)){
                selectedShapesList.add(shape);
            }
        }

        for(IShape shape : selectedShapesList) {
            shapeClipBoard.addAll(selectedShapesList);
            setCanCopy();
        }
    }

    public ArrayList<IShape> getCurrentShapeList() { return currentShapeList; }

    public ArrayList<IShape> getSelectedShapesList() { return selectedShapesList; }

    public ArrayList<IShape> getShapeClipBoard() { return shapeClipBoard; }

    public void deselectShapes() {
        currentShapeList.addAll(selectedShapesList);
        selectedShapesList.clear();
        setSelected();
    }

    public void clearShapeClipBoard() {
        shapeClipBoard.clear();
        setCanCopy();
    }

    public void setShapeClipBoard() {
        shapeClipBoard.clear();
        setCanCopy();
    }

    @Override
    public Iterator<IShape> createCurrentIter() { return currentShapeList.iterator(); }

    @Override
    public Iterator<IShape> createSelectedShapeIter() { return selectedShapesList.iterator(); }

    @Override
    public Iterator<IShape> createClipBoardIter() { return shapeClipBoard.iterator(); }

    @Override
    public void registerObserver(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void notifyObservers() {
        paintCanvas.update(currentShapeList, selectedShapesList);
    }
}
