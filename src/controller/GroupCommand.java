package controller;

import model.Shapes.ShapeList;
import model.interfaces.IShape;

import java.awt.*;

public class GroupCommand implements ICommand, IUndoable {
    private ShapeList shapes;
    private Graphics2D graphics;
    private int height;
    private int width;
    int minX, maxX, minY, maxY;
    private IShape start_point;

    public GroupCommand(ShapeList shapes, Graphics2D graphics) {
        this.shapes = shapes;
        this.graphics = graphics;
    }

    private void findValues(ShapeList shapes) {
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        maxX = Integer.MIN_VALUE;
        int tmp1, tmp2, tmp3, tmp4;

       for(IShape shape: shapes.getSelectedShapesList()) {
           tmp1 = Math.max(shape.getStartPoint().x, shape.getEndPoint().x);
           tmp2 = Math.min(shape.getStartPoint().x, shape.getEndPoint().x);

           tmp3 = Math.max(shape.getStartPoint().y, shape.getEndPoint().y);
           tmp4 = Math.min(shape.getStartPoint().y, shape.getEndPoint().y);

           if( tmp1 >= maxX) { maxX =  tmp1; }
           if( tmp2 <= minX) {
               minX = tmp2;
               start_point = shape;
           }
           if( tmp3 >= maxY) { maxY = tmp3; }
           if( tmp4 <= minY) { minY = tmp4; }
       }

       width = Math.abs(maxX - minX) + 50;
       height = Math.abs(maxY - minY)+ 50;
    }


    private void drawOutline(Color color) {
        java.awt.Rectangle rectangle = new java.awt.Rectangle(minX + -30, start_point.getStartPoint().y - 30, width , height);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(
                4.0f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 10.0f,
                new float[] {16.0f, 20.0f}, 0.0f));
        graphics.drawRect(rectangle.x, rectangle.y, width, height);
        for(IShape shape: shapes.getSelectedShapesList()) {
            shape.drawOutline(Color.WHITE);
        }
    }

    @Override
    public void execute() {
        findValues(shapes);
        drawOutline(Color.BLACK);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        drawOutline(Color.WHITE);
        shapes.deselectShapes();
        CommandHistory.add(this);
    }

    @Override
    public void redo() {
        drawOutline(Color.BLACK);
    }
}
