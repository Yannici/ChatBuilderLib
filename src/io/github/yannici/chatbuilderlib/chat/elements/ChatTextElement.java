package io.github.yannici.chatbuilderlib.chat.elements;

import io.github.yannici.chatbuilderlib.ChatElement;

/**
 * This class represents a text element of a whole chat message built with the ChatBuilder
 * 
 * @author Yannici
 *
 */
public class ChatTextElement extends ChatElement {
	
	private String text = null;
	
	/**
	 * Initialize a new instance of the ChatTextElement class
	 */
	public ChatTextElement() {
		super();
	}

	/**
	 * Gets the text of this element
	 * @return The text of this element
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text for this element
	 * @param The text which should be used
	 * @return This ChatElement
	 */
	public ChatElement setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public void prepareElementsJson() {
		this.getElement().put("text", this.text);
	}
	
}
