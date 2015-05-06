package io.github.yannici.chatbuilderlib.chat.events;

import io.github.yannici.chatbuilderlib.ChatEvent;

public class ChatClickEvent extends ChatEvent {
	
	public ChatClickEvent() {
		super();
	}
	
	@Override
	public void setAction(String action) {
		if(ChatClickEventAction.getByString(action) != null) {
			super.setAction(action);
		}
	}
	
	public void setAction(ChatClickEventAction action) {
		super.setAction(action.toString());
	}
	
}
