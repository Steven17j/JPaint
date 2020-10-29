package view.gui;

import model.interfaces.IShape;
import view.interfaces.IShapeObserver;
import javax.swing.JComponent;
import java.awt.*;
import java.util.ArrayList;


public class PaintCanvas extends JComponent implements IShapeObserver {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void update(ArrayList<IShape> shapes, ArrayList<IShape> moreShapes) {

        Graphics2D graphics = getGraphics2D();
        graphics.clearRect(0,0,1500, 800);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, 1500, 800);

        for(IShape shape: shapes) { shape.draw(); }

        for(IShape shape: moreShapes) { shape.draw(); }
    }
}
