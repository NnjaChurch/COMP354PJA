package facade;

import control.Inbox;
import control.Message;
import control.MessageType;

public class MessageToInboxFacade {

	
	private Message[] messages;
	private Inbox testInbox;
	
	public MessageToInboxFacade() {
		 testInbox = new Inbox(); 
		 // create 8 types of message to different cards.
		 messages = new Message[8];
		 messages[0] = new Message(MessageType.NEW_GAME_B_RANDOM_R_RANDOM,1);
		 messages[1] = new Message(MessageType.NEW_GAME_B_RANDOM_R_NEXT,2);
		 messages[2] = new Message(MessageType.NEW_GAME_B_NEXT_R_RANDOM,3);
		 messages[3] = new Message(MessageType.NEW_GAME_B_NEXT_R_NEXT,4);
		 messages[4] = new Message(MessageType.NEXT,5);
		 messages[5] = new Message(MessageType.UNDO,6);
		 messages[6] = new Message(MessageType.REDO,7);
		 messages[7] = new Message(MessageType.SELECT,8);		 
	}
	
	public void start() {
		System.out.println("Testing sending a message to the inbox...");
		System.out.println();
		for (int i =0;i<8;i++) {
			System.out.print("Test"+(i+1)+": ");
			// sending message 8 times to test all types of message.
			testInbox.sendMessage(messages[i]);
			printMessage(testInbox);
		}
	}
	
	private void printMessage(Inbox ib) {
		// printing the message in the Inbox.
		System.out.println("Message detail: "+ib.getMessage().getMessageType()+" affacting on card#"+ib.getMessage().getCardAffected());
		System.out.println();
	}
	
	public static class InboxFacadeDriver{
		public static void main(String[] args) {
			MessageToInboxFacade facade = new MessageToInboxFacade();
			facade.start();
		}
	}
}
