package controller;

import model.Shapes.ShapeList;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PasteCommand implements ICommand, IUndoable {

    private ArrayList<IShape> clip_board;
    private ShapeList shapes;
    private int delta_x, delta_y;

    public PasteCommand(ShapeList shapes) {
        this.shapes = shapes;
    }


    @Override
    public void execute() {
        pasteShapes();

        CommandHistory.add(this);

    }

    private void pasteShapes() {
        System.out.println("First step");
        if(shapes.getCurrentShapeList().isEmpty() == false) {
            System.out.println("Bing bang");
        }
        if (shapes.isCanCopy() == false) {
            System.out.println("Gotcha bitch");
        }
        if(!shapes.getShapeClipBoard().isEmpty() && shapes.isCanCopy()) {
            System.out.println("second step");
            clip_board = new ArrayList<>(shapes.getShapeClipBoard().size());
            Iterator<IShape> clipIter = shapes.createClipBoardIter();

            while(clipIter.hasNext()) {
                System.out.println("third step");
                clip_board.add(clipIter.next().clone());
            }
            Random randNum = new Random();
            delta_x = randNum.nextInt(300) + 50;
            delta_y = randNum.nextInt(300) + 50;

            for(IShape shape: clip_board) {
                shapes.getCurrentShapeList().add(shape);
                System.out.println("You pasted");
                Point startPoint = new Point((int)(shape.getStartPoint().getX() + delta_x), (int)(shape.getStartPoint().getY() + delta_y));
                Point endPoint = new Point(shape.getShapeWidth() + (int) startPoint.getX(), shape.getShapeHeight() + (int) startPoint.getY() );
                shape.setStartAndEndPoint(startPoint,endPoint);
            }
            shapes.notifyObservers();
        }
    }

    @Override
    public void undo() {
        for(IShape shape: clip_board) {
            Shape final_shape = shape.getShapeParameters();
            shapes.getCurrentShapeList().removeIf((IShape i) -> i.getShapeParameters().equals(final_shape));
        }
        shapes.notifyObservers();
    }

    @Override
    public void redo() {
        for(IShape shape: clip_board) {
            shapes.getCurrentShapeList().add(shape);
        }
         shapes.notifyObservers();
    }
}
