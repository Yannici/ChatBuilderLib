package io.github.yannici.chatbuilderlib.chat.events;

public enum ChatClickEventAction {
	OPEN_URL("open_url"), 
	OPEN_FILE("open_file"), 
	RUN_COMMAND("run_command"), 
	SUGGEST_COMMAND("suggest_command");
	
	private String action = null;
	
	ChatClickEventAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return this.action;
	}
	
	public static ChatClickEventAction getByString(String action) {
		for(ChatClickEventAction ccea : ChatClickEventAction.values()) {
			if(ccea.toString().equalsIgnoreCase(action)) {
				return ccea;
			}
		}
		
		return null;
	}

}
