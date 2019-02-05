----------------------------------------------------------------------------------------------------
COMP 354 PJA - CODENAMES PROJECT - CONTROL README
Information about all Classes and their functions (not including Getters)
by Kevin McAllister (40031326) - Iteration 1
edited: ...
NOTE: CONVERT TO LATEX SHEET!!!
----------------------------------------------------------------------------------------------------

CONTROL:
----------------------------------------------------------------------------------------------------
Controller.java:

	Class Type:	Object - Observer
	Function:	An Observer class to be attached to Inbox.java in order to be notified of messages
				from the View. Processes the Message and updates the Model accordingly. 
	
	Constructor Requires:
		KeyCard[] keyCardCollection		// COLLECTION OF KEYCARDS TO SELECT FROM AT RANDOM
		Outbox outbox					// REFERENCE TO THE OUTBOX OBJECT (CREATED IN MAIN)
		
	Public Methods:
		void update(Observable o, Object arg)	// Invoked via Observable notifyObservers()
		/* Processes a passed Message object and updates the Model depending on the MessageType
		 * SELECT: If cardAffected is not revealed, reveal the card and log the change
		 * NEXT: Select a Card (Random / Next), reveal it and log the change
		 * UNDO: Revert the last reveal and log the change
		 * REDO: Reveal the last undo and log the change
		 * NEW_GAME: Update the NewGame flag (causes a reset internally!)
		 */
		 
GameObserver.java
	
	Class Type: Object - Observer
	Function:	An observer class to be attached to each instance of Card.java in order to be notified
				of changes in the card reveal state. Processes the game logic with each change and sends
				a reply to the Outbox.java to notify the View
	
	Constructor Requires:
		KeyCard keyCard					// REFERENCE TO THE KEYCARD SELECTED BY THE CONTROLLER
		Outbox outbox					// REFERENCE TO THE OUTBOX OBJECT (CREATED IN MAIN)
		
	Public Methods:
		void update(Observable o, Object arg)	// Invoked via Observable notifyObservers()
		/* Processes a passed Card object and updates the Game state depending on the change
		 * REVEAL: Updates scores and turns depending on the CardType of the passed card
		 * BLACK: Changes turn to the winning team and sends reply to View to END the game
		 * YELLOW: Changes the turn and sends Reply to View to UPDATE the game
		 * BLUE: Decrements the blueScore and changes turn if the current turn is red (no turn change if blue)
		 * RED: Decrements the redScore and changes turn if the current turn is blue (no turn change if red)
		 *
		 * HIDE: Updates scores and turn back to the previous turn values
		 * BLACK: The reveal of the Black (Assassin) card cannot be undone!
		 * YELLOW: Loads the last turn and sends a Reply to View to UPDATE the game
		 * BLUE: Loads the last turn and increments blueScore
		 * RED: Loads the last turn and increments redScore
		 */
		 
Inbox.java
	
	Class Type: Object - Observable
	Function: 	An observable class that will receive Message objects from the View and notify the Observer
				that it has been received
				
	Contructor Requires:
		Nothing
		
	Public Methods:
		void sendMessage(Message m)
		/* Receives Messages from the function caller and notifies the Observer (Controller.java in this case)
		 * Saves the last received Message and allows access to it (via getMessage())
		 */

Outbox.java

	Class Type: Object - Observable
	Function:	An observable class that will receive Reply objects from the Control (GameObserver.java) and
				notify the Observer that it has been received
				
	Constructor Requires:
		Nothing
		
	Public Methods:
		void sendReply(Reply r)
		/* Receives Replies from the function caller and notifies the Observer (In the View in this case)
		 * Saves the last received Reply and allows access to it (via getReply())
		 */
		 
Message.java

	Class Type:	Object
	Function:	An Object to store the information for a request by the View
	
	Constructor Requires:
		MessageType type				// ENUM FOR THE TYPE OF MESSAGE SENT
		int cardAffected				// REFERENCE TO THE CARD NUMBER AFFECTED
	
	Public Methods:
		None

Reply.java

	Class Type:	Object
	Function:	An Object to store the information to send back to the View
	
	Constructor Requires:
		ReplyType type					// ENUM FOR THE TYPE OF REPLY SENT
		