package model;

import java.util.Stack;

public class Controller {

    private Stack<Command> commandStack;


    // Methods
    /*
     TODO: SEPARATE METHODS FOR EACH POSSIBLE EVENT
     This includes: CardClick, NewGame, NextMove, ViewKeyCard, ...
     */

    public static void getEvent() {
        /*
        TODO: CREATE EVENT HANDLER
        General format for event handlers in the controller will be:
        - Receive event type from GUI interaction
        - Process received data and call appropriate command function
        - Push command onto the commandStack (to allow undo)
            - If EVENT is an UNDO, pop command from stack and undo its action (load game state)
        - Process response from command function
        - Return response to the GUI and update appropriately
         */

    }

}
