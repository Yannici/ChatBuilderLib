package io.github.yannici.chatbuilderlib.chat.elements;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.github.yannici.chatbuilderlib.ChatElement;

public class ChatScoreElement extends ChatElement {
    
    private String name = null;
    private String objective = null;
    
    public ChatScoreElement() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void prepareElementsJson() {
        Map<String, String> data = new HashMap<String, String>();
        
        JSONObject obj = new JSONObject();
        data.put("name", this.name);
        data.put("objective", this.objective);
        
        obj.putAll(data);
        
        this.getElement().put("score", obj);
    }
    
}
