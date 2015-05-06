package io.github.yannici.chatbuilderlib;

import org.json.simple.JSONObject;

public class ChatEvent {
	
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
