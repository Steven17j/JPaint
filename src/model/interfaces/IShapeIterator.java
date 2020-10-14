package model.interfaces;

import java.util.Iterator;

public interface IShapeIterator {
    Iterator<IShape> createCurrentShapeIterator();
    Iterator<IShape> createSelectedShapeIterator();
}
