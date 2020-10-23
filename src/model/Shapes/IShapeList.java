package model.Shapes;

import model.interfaces.IShape;
import java.util.Iterator;


public interface IShapeList {
    Iterator<IShape> createCurrentIter();
    Iterator<IShape> createSelectedShapeIter();
    Iterator<IShape> createClipBoardIter();

}
