package io.github.yannici.chatbuilderlib.chat.events;

public enum ChatHoverEventAction {
	SHOW_TEXT("show_text"), 
	SHOW_ACHIEVEMENT("show_achievement"), 
	SHOW_ITEM("show_item");
	
	private String action = null;
	
	ChatHoverEventAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return this.action;
	}
	
	public static ChatHoverEventAction getByString(String action) {
		for(ChatHoverEventAction chea : ChatHoverEventAction.values()) {
			if(chea.toString().equalsIgnoreCase(action)) {
				return chea;
			}
		}
		
		return null;
	}
	
}
