package controller;

import model.Shapes.ShapeList;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapeList shapes;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapes) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapes = shapes;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        ICommand redo = new RedoCommand();
        ICommand undo = new UndoCommand();
        ICommand copy = new CopyCommand(shapes);
        ICommand paste = new PasteCommand(shapes);
        ICommand delete = new DeleteCommand(shapes);
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());

        uiModule.addEvent(EventName.UNDO, () -> undo.execute());
        uiModule.addEvent(EventName.REDO, () -> redo.execute());
        uiModule.addEvent(EventName.COPY, () -> copy.execute());
        uiModule.addEvent(EventName.PASTE, () -> paste.execute());
        uiModule.addEvent(EventName.DELETE, () -> delete.execute());

    }
}
