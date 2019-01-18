package model;

public class Command {

    // Attributes
    private CommandType type;
    private GameState state;

    /*
    TODO: ADD CONTAINER VARIABLES FOR THE RECEIVED DATA AND SENT DATA
     */

    // Constructor

    public Command() {
        // TODO: CREATE CONSTRUCTOR FOR COMMAND / OTHER CONSTRUCTOR FOR CERTAIN TYPES OF COMMANDS
    }

    // Getters

    public CommandType getCommandType() {
        return this.type;
    }

    public GameState getGameState() {
        return this.state;
    }

    // TODO: ADD GETTERS FOR OTHER VARIABLES

    // Methods

    // TODO: ADD METHODS FOR COMMANDS



}
