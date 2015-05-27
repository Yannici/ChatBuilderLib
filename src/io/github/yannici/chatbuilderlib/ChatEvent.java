package io.github.yannici.chatbuilderlib;

import io.github.yannici.chatbuilderlib.chat.events.ChatClickEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEvent;
import io.github.yannici.chatbuilderlib.chat.events.ChatInsertionEvent;

import org.json.simple.JSONObject;

/**
 * The abstract class for the chat event types
 * 
 * @see ChatClickEvent
 * @see ChatHoverEvent
 * @see ChatInsertionEvent
 * 
 * @author Yannici
 *
 */
public abstract class ChatEvent {
	
	private String action = null;
	private String value = null;
	
	public ChatEvent() {
		super();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getJson() {
		if(action == null || value == null) {
			return null;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("action", this.action);
		obj.put("value", this.value);
		
		return obj;
	}

}
