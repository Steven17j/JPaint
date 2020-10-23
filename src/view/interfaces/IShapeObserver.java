package view.interfaces;

import model.interfaces.IShape;

import java.util.ArrayList;

public interface IShapeObserver {
    void update(ArrayList<IShape> shapes, ArrayList<IShape> moreShapes);
}
