package io.github.yannici.chatbuilderlib;

import java.util.HashMap;
import java.util.Map;

import io.github.yannici.chatbuilderlib.chat.events.ChatClickEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatClickEventAction;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEventAction;
import io.github.yannici.chatbuilderlib.chat.events.ChatInsertionEvent;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;

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

	public Map<String, Object> getElement() {
		return element;
	}

	public boolean isBold() {
		return bold;
	}

	public ChatElement setBold(boolean bold) {
		this.bold = bold;
		return this;
	}

	public boolean isItalic() {
		return italic;
	}

	public ChatElement setItalic(boolean italic) {
		this.italic = italic;
		return this;
	}

	public boolean isUnderlined() {
		return underlined;
	}

	public ChatElement setUnderlined(boolean underlined) {
		this.underlined = underlined;
		return this;
	}

	public boolean isStrikethrough() {
		return strikethrough;
	}

	public ChatElement setStrikethrough(boolean strikethrough) {
		this.strikethrough = strikethrough;
		return this;
	}

	public boolean isObfuscated() {
		return obfuscated;
	}

	public ChatElement setObfuscated(boolean obfuscated) {
		this.obfuscated = obfuscated;
		return this;
	}

	public String getColor() {
		return color;
	}

	public ChatElement setColor(String color) {
		this.color = color;
		return this;
	}
	
	public ChatElement setColor(ChatColor color) {
		this.color = color.name().toLowerCase();
		return this;
	}

	public ChatClickEvent getClickEvent() {
		return clickEvent;
	}

	public ChatElement setClickEvent(ChatClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return this;
	}
	
	public ChatElement setClickEvent(ChatClickEventAction action, String value) {
		this.clickEvent = new ChatClickEvent();
		this.clickEvent.setAction(action);
		this.clickEvent.setValue(value);
		
		return this;
	}

	public ChatHoverEvent getHoverEvent() {
		return hoverEvent;
	}

	public ChatElement setHoverEvent(ChatHoverEvent hoverEvent) {
		this.hoverEvent = hoverEvent;
		return this;
	}
	
	public ChatElement setHoverEvent(ChatHoverEventAction action, String value) {
		this.hoverEvent = new ChatHoverEvent();
		this.hoverEvent.setAction(action);
		this.hoverEvent.setValue(value);
		
		return this;
	}

	public ChatInsertionEvent getInsertionEvent() {
		return insertionEvent;
	}

	public ChatElement setInsertionEvent(ChatInsertionEvent insertionEvent) {
		this.insertionEvent = insertionEvent;
		return this;
	}
	
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
	
	public abstract void prepareElementsJson();
	
	@SuppressWarnings("unchecked")
	public JSONObject getJson() {
		this.prepareJson();
		this.prepareElementsJson();
		
		JSONObject obj = new JSONObject();
		obj.putAll(this.element);
		
		return obj;
	}

}
