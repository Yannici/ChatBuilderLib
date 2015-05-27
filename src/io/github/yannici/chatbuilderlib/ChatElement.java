package io.github.yannici.chatbuilderlib;

import java.util.HashMap;
import java.util.Map;

import io.github.yannici.chatbuilderlib.chat.elements.ChatScoreElement;
import io.github.yannici.chatbuilderlib.chat.elements.ChatSelectorElement;
import io.github.yannici.chatbuilderlib.chat.elements.ChatTextElement;
import io.github.yannici.chatbuilderlib.chat.events.ChatClickEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatClickEventAction;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEventAction;
import io.github.yannici.chatbuilderlib.chat.events.ChatInsertionEvent;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;

/**
 * This class is the abstract class for the different element types in one message
 * built with the ChatBuilder
 * 
 * @see ChatScoreElement
 * @see ChatSelectorElement
 * @see ChatTextElement
 * @see ChatTranslateElement
 * 
 * @author Yannici
 *
 */
public abstract class ChatElement {
	
	private Map<String, Object> element = null;
	private boolean bold = false;
	private boolean italic = false;
	private boolean underlined = false;
	private boolean strikethrough = false;
	private boolean obfuscated = false;
	private String color = null;
	private ChatClickEvent clickEvent = null;
	private ChatHoverEvent hoverEvent = null;
	private ChatInsertionEvent insertionEvent = null;
	
	public ChatElement() {
		super();
		
		this.element = new HashMap<String, Object>();
	}
	
	/**
	 * Returns the element represented as map
	 * @return The map which represents this element
	 */
	public Map<String, Object> getElement() {
		return element;
	}

	/**
	 * If this element is bold
	 * @return Returns true if the message was set bold, otherwise false
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * Sets the chat element bold
	 * @param true if the message should be bold, otherwise false
	 * @return Returns this ChatElement
	 */
	public ChatElement setBold(boolean bold) {
		this.bold = bold;
		return this;
	}

	/**
	 * If this element is italic
	 * @return Returns true if the element was set italic, otherwise false
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * Sets the chat element italic
	 * @param true if the message should be italic, otherwise false
	 * @return Returns this ChatElement
	 */
	public ChatElement setItalic(boolean italic) {
		this.italic = italic;
		return this;
	}

	/**
	 * If this element is underlined
	 * @return Returns true if the element was set underlined, otherwise false
	 */
	public boolean isUnderlined() {
		return underlined;
	}

	/**
	 * Sets the element underlined
	 * @param If the message should be underlined
	 * @return Returns this ChatElement
	 */
	public ChatElement setUnderlined(boolean underlined) {
		this.underlined = underlined;
		return this;
	}

	/**
	 * If this element is stroke through
	 * @return Returns true if the element was stroke through, otherwise false
	 */
	public boolean isStrikethrough() {
		return strikethrough;
	}

	/**
	 * Sets this element stroke through
	 * @param If the element should be stroke through
	 * @return Returns this ChatElement
	 */
	public ChatElement setStrikethrough(boolean strikethrough) {
		this.strikethrough = strikethrough;
		return this;
	}

	/**
	 * If this element is obfuscated
	 * @return Returns true if the element is obfuscated, otherwise false
	 */
	public boolean isObfuscated() {
		return obfuscated;
	}

	/**
	 * Sets this element obfuscated
	 * @param If the element should be obfuscated
	 * @return Returns this ChatElement
	 */
	public ChatElement setObfuscated(boolean obfuscated) {
		this.obfuscated = obfuscated;
		return this;
	}

	/**
	 * Gets the color of this element
	 * @return The color of this element
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color of this element by string
	 * @param The color which should be used
	 * @return This ChatElement
	 */
	public ChatElement setColor(String color) {
		this.color = color;
		return this;
	}
	
	/**
	 * Sets the color of this element by chatcolor
	 * @param The ChatColor which should be used for this element
	 * @return This ChatElement
	 */
	public ChatElement setColor(ChatColor color) {
		this.color = color.name().toLowerCase();
		return this;
	}

	/**
	 * Gets the click event of this element
	 * @return The ChatClickEvent object or null if not set
	 */
	public ChatClickEvent getClickEvent() {
		return clickEvent;
	}

	/**
	 * Sets the event which should be executed on click
	 * @param A object of the ChatClickEvent
	 * @return This ChatElement
	 */
	public ChatElement setClickEvent(ChatClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return this;
	}
	
	/**
	 * Sets the event which should be executed on click
	 * @param The ChatClickEventAction which should be used
	 * @param The value for this action
	 * @return This ChatElement
	 */
	public ChatElement setClickEvent(ChatClickEventAction action, String value) {
		this.clickEvent = new ChatClickEvent();
		this.clickEvent.setAction(action);
		this.clickEvent.setValue(value);
		
		return this;
	}

	/**
	 * Gets the hover event of this element
	 * @return The ChatHoverEvent object or null if not set
	 */
	public ChatHoverEvent getHoverEvent() {
		return hoverEvent;
	}

	/**
	 * Sets the hover event of this element
	 * @param A object of the ChatHoverEvent
	 * @return This ChatElement
	 */
	public ChatElement setHoverEvent(ChatHoverEvent hoverEvent) {
		this.hoverEvent = hoverEvent;
		return this;
	}
	
	/**
	 * Sets the hover event of this element
	 * @param The ChatHoverEventAction which should be used
	 * @param The value for the ChatHoverEventAction
	 * @return This ChatElement
	 */
	public ChatElement setHoverEvent(ChatHoverEventAction action, String value) {
		this.hoverEvent = new ChatHoverEvent();
		this.hoverEvent.setAction(action);
		this.hoverEvent.setValue(value);
		
		return this;
	}

	/**
	 * Gets the chat insertion event or null if not set
	 * @return The ChatInsertionEvent object
	 */
	public ChatInsertionEvent getInsertionEvent() {
		return insertionEvent;
	}

	/**
	 * Sets the chat insertion event which should be used
	 * @param The ChatInsertionEvent object
	 * @return This ChatElement
	 */
	public ChatElement setInsertionEvent(ChatInsertionEvent insertionEvent) {
		this.insertionEvent = insertionEvent;
		return this;
	}
	
	/**
	 * The text/value which should be used for the insertion event
	 * @param The text for the ChatInsertionEvent
	 * @return This ChatElement
	 */
	public ChatElement setInsertionEvent(String text) {
		this.insertionEvent = new ChatInsertionEvent();
		this.insertionEvent.setText(text);
		
		return this;
	}

	private void prepareJson() {
		this.element.clear();
		
		this.element.put("bold", this.bold);
		this.element.put("italic", this.italic);
		this.element.put("underlined", this.underlined);
		this.element.put("strikethrough", this.strikethrough);
		this.element.put("obfuscated", this.obfuscated);
		
		if(this.color != null) {
			this.element.put("color", this.color);
		}
		
		if(this.clickEvent != null) {
			this.element.put("clickEvent", this.clickEvent.getJson());
		}
		
		if(this.hoverEvent != null) {
			this.element.put("hoverEvent", this.hoverEvent.getJson());
		}
		
		if(this.insertionEvent != null) {
			this.element.put("insertion", this.insertionEvent.getText());
		}
	}
	
	/**
	 * The abstract method to prepare the json of the different element-types
	 */
	public abstract void prepareElementsJson();
	
	/**
	 * Prepares the JSON string and creating a JSON object
	 * @return JSON object for this element
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getJson() {
		this.prepareJson();
		this.prepareElementsJson();
		
		JSONObject obj = new JSONObject();
		obj.putAll(this.element);
		
		return obj;
	}

}
