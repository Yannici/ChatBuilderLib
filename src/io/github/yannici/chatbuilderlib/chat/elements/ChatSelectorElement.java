package io.github.yannici.chatbuilderlib.chat.elements;

import io.github.yannici.chatbuilderlib.ChatElement;

/**
 * This class represents a selector element in the whole chat message built with the ChatBuilder
 * 
 * @see ChatScoreElement
 * @see ChatTextElement
 * @see ChatTranslateElement
 * 
 * @author Yannici
 *
 */
public class ChatSelectorElement extends ChatElement {
	
	private String selector = null;
	
	/**
	 * Initialize a new instance of the ChatSelectorElement class
	 */
	public ChatSelectorElement() {
		super();
	}
	
	/**
	 * Gets the used selector
	 * @return The using selector
	 */
	public String getSelector() {
		return this.selector;
	}
	
	/**
	 * Sets the selector which should be used. Parameter can be with or without a starting @.
	 * @param The selector which should be used
	 * @return This ChatSelectorElement object
	 */
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
