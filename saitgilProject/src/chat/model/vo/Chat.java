package chat.model.vo;

public class Chat {
	
	String cCode;
	int chatID;
	String fromID;
	String toID;
	String chatContent;
	String chatTime;
	
	public Chat() {}

	public Chat(String cCode, int chatID, String fromID, String toID, String chatContent, String chatTime) {
		super();
		this.cCode = cCode;
		this.chatID = chatID;
		this.fromID = fromID;
		this.toID = toID;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}
	
	

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		return "Chat [cCode=" + cCode + ", chatID=" + chatID + ", fromID=" + fromID + ", toID=" + toID
				+ ", chatContent=" + chatContent + ", chatTime=" + chatTime + "]";
	}


	

}
