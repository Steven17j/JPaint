package view.Listeners;

import controller.DrawShapeCommand;

import controller.ICommand;
import model.Shapes.ShapeList;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseAdapter implements MouseListener {
    private java.awt.Point startPoint, endPoint;
    private IApplicationState applicationState;
    private PaintCanvasBase canvas;
    private ICommand command;
    private ShapeList shapes;

    public MouseAdapter(PaintCanvasBase canvas, IApplicationState state) {
        this.canvas = canvas;
        this.applicationState = state;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       endPoint = e.getPoint();
       command = new DrawShapeCommand(canvas.getGraphics2D(), applicationState, startPoint, endPoint);
       command.execute();

    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }


}
