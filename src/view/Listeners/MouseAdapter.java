package view.Listeners;

import controller.DrawShapeCommand;

import controller.ICommand;
import controller.MoveShapeCommand;
import controller.SelectShapeCommand;
import model.MouseMode;
import model.Shapes.ShapeList;
import model.interfaces.IApplicationState;
import view.gui.PaintCanvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseAdapter implements MouseListener {
    private java.awt.Point startPoint, endPoint;
    private IApplicationState applicationState;
    private PaintCanvas canvas;
    private ICommand command;
    private ShapeList shapes;

    public MouseAdapter(PaintCanvas canvas, IApplicationState state, ShapeList shapes) {
        this.canvas = canvas;
        this.applicationState = state;
        this.shapes = shapes;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       endPoint = e.getPoint();
       if(applicationState.getActiveMouseMode() == MouseMode.DRAW) {
           command = new DrawShapeCommand(canvas.getGraphics2D(), applicationState, startPoint, endPoint, shapes);
       }
       if(applicationState.getActiveMouseMode() == MouseMode.SELECT) {
           command = new SelectShapeCommand(shapes, startPoint, endPoint);
       }
       if(applicationState.getActiveMouseMode() == MouseMode.MOVE) {
           command = new MoveShapeCommand(startPoint, endPoint, shapes);
       }
       command.execute();
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }


}
