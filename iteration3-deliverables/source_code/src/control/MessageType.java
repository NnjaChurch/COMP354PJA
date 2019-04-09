/**
 * MessageType enum used to define the types of messages sent from the View to Control
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

public enum MessageType {
    NEW_GAME_B_RANDOM_R_RANDOM, NEW_GAME_B_RANDOM_R_NEXT, NEW_GAME_B_NEXT_R_RANDOM, NEW_GAME_B_NEXT_R_NEXT, NEXT, UNDO, REDO, SELECT
}
