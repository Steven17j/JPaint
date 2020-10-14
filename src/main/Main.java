package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.Shapes.ShapeList;
import model.persistence.ApplicationState;
import view.Listeners.MouseAdapter;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ShapeList shapes = new ShapeList();
        MouseAdapter mouseListener = new MouseAdapter(paintCanvas, appState, shapes);
        (paintCanvas).addMouseListener(mouseListener);
        IJPaintController controller = new JPaintController(uiModule, appState, shapes);
        controller.setup();
    }
}
