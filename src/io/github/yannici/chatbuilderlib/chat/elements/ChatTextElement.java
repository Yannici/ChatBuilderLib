package io.github.yannici.chatbuilderlib.chat.elements;

import io.github.yannici.chatbuilderlib.ChatElement;

public class ChatTextElement extends ChatElement {
	
	private String text = null;
	
	public ChatTextElement() {
		super();
	}

	public String getText() {
		return text;
	}

	public ChatElement setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public void prepareElementsJson() {
		this.getElement().put("text", this.text);
	}
	
}
