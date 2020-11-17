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

        //Clear the canvas
        Graphics2D graphics = getGraphics2D();
        graphics.clearRect(0,0,1600, 800);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, 1600, 800);

        //non selected shapes on canvas are drawn
        for(IShape shape: shapes) { shape.draw(); }

        //shapes that are selected are drawn with outline
        for(IShape shape: moreShapes) {
            shape.draw();
            shape.drawOutline(Color.BLACK);
        }
    }
}
