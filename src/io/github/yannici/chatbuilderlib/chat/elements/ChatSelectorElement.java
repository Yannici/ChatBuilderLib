package io.github.yannici.chatbuilderlib.chat.elements;

import io.github.yannici.chatbuilderlib.ChatElement;

public class ChatSelectorElement extends ChatElement {
	
	private String selector = null;
	
	public ChatSelectorElement() {
		super();
	}
	
	public String getSelector() {
		return this.selector;
	}
	
	public ChatSelectorElement setSelector(String selector) {
		if(selector.startsWith("@")) {
			this.selector = selector;
		} else {
			this.selector = "@" + selector;
		}
		
		return this;
	}

	@Override
	public void prepareElementsJson() {
		this.getElement().put("selector", this.selector);
	}
	
}
