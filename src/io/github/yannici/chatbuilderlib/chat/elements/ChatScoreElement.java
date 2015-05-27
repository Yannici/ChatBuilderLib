package io.github.yannici.chatbuilderlib.chat.elements;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.github.yannici.chatbuilderlib.ChatElement;

/**
 * Represents one score element text in one full chat message built with the ChatBuilder
 * 
 * @see ChatTranslateElement
 * @see ChatTextElement
 * @see ChatSelectorElement
 * 
 * @author Yannici
 *
 */
public class ChatScoreElement extends ChatElement {
    
    private String name = null;
    private String objective = null;
    
    /**
     * Initialize a new instance of the ChatScoreElement class
     */
    public ChatScoreElement() {
        super();
    }

    /**
     * Gets the name of the score
     * @return Score name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this score chat element
     * @param Name of the score
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the objective name
     * @return The objective name
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Sets the objective name which should be displayed
     * @param objective which should be used
     */
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
