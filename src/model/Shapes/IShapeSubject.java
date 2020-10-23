package model.Shapes;

import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public interface IShapeSubject {
    void registerObserver(PaintCanvas paintCanvas);
    void notifyObservers();
}
