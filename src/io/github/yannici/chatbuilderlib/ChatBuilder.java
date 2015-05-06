package io.github.yannici.chatbuilderlib;

import java.util.ArrayList;
import java.util.List;

import io.github.yannici.chatbuilderlib.chat.elements.ChatTextElement;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class ChatBuilder extends JavaPlugin {
	
	private List<ChatElement> elements = null;
	
	public ChatBuilder() {
		this.elements = new ArrayList<ChatElement>();
	}
	
	@Override
	public void onEnable() {
		// nothing needed
	}
	
	@Override
	public void onDisable() {
		// nothing needed
	}
	
	public ChatElement appendText(String text) {
		ChatTextElement element = new ChatTextElement();
		element.setText(text);
		
		this.elements.add(element);
		return element;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getJson() {
		if(this.elements.size() == 0) {
			return "{\"text\":\"malformed build\"}";
		}
		
		JSONObject obj = null;
		List<ChatElement> chatElements = new ArrayList(this.elements);
		ChatElement firstElement = chatElements.get(0);
		
		obj = firstElement.getJson();
		chatElements.remove(0);
		
		if(chatElements.size() == 0) {
			return obj.toJSONString();
		}
		
		List<JSONObject> extras = new ArrayList<JSONObject>();
		for(ChatElement element : chatElements) {
			extras.add(element.getJson());
		}
		
		obj.put("extra", extras);
		return obj.toJSONString();
	}
	
}
