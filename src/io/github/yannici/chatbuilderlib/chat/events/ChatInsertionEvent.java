package io.github.yannici.chatbuilderlib.chat.events;

public class ChatInsertionEvent {
	
	private String text = null;
	
	public ChatInsertionEvent() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
