package control;

public class LogMessage {
	
	// Attributes
	private MessageType mType;
	private int mCardAffected;
	private String mHint;
	
	// Constructors
	public LogMessage(MessageType type, int cardAffected, String hint) {
		this.mType = type;
		this.mCardAffected = cardAffected;
		this.mHint = hint;
	}
	
	// Getters
	public MessageType getType() {
		return this.mType;
	}
	
	public int getCardAffected() {
		return this.mCardAffected;
	}
	
	public String getHint() {
		return this.mHint;
	}

}
