package io.github.yannici.chatbuilderlib.chat.events;

import io.github.yannici.chatbuilderlib.ChatEvent;

public class ChatHoverEvent extends ChatEvent {
	
	public ChatHoverEvent() {
		super();
	}
	
	@Override
	public void setAction(String action) {
		if(ChatHoverEventAction.getByString(action) != null) {
			super.setAction(action);
		}
	}
	
	public void setAction(ChatHoverEventAction action) {
		super.setAction(action.toString());
	}
	
}
