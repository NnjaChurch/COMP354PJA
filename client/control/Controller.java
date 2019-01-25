package control;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class Controller implements Observer {

    // Attributes
    private Stack<Command> mCommandStack;
    private Stack<Command> mUndoStack;

    // Constructor
    public Controller() {
        this.mCommandStack = new Stack<>();
        this.mUndoStack = new Stack<>();
    }

    // Methods
    public void getMessage(Message m) {
        if(m instanceof SelectMessage) {
            // TODO: Code to handle a select action
        }
        if(m instanceof NextMessage) {
            /*
             TODO: Code to handle a next action
             Run function to decide next action of computer (random or sequential)
              */
        }
        if(m instanceof UndoMessage) {
            // TODO: Code to handle an undo action
        }
        if(m instanceof RedoMessage) {
            // TODO: Code to handle a redo action
        }
        if(m instanceof NewGameMessage) {
            // TODO: Code to handle a new game action
        }
        if(m instanceof KeyCardMessage) {
            // TODO: Code to handle a keyCard action
        }
    }

    @Override
    public void update(Observable o, Object arg) {
       // TODO: Add body to update to handle observer changes
    }


}
