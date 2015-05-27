package io.github.yannici.chatbuilderlib.chat.elements;

import java.util.ArrayList;
import java.util.List;

import io.github.yannici.chatbuilderlib.ChatElement;

/**
 * This class represents a translatable text element of a whole chat message built with the ChatBuilder
 * 
 * @author Yannici
 *
 */
public class ChatTranslateElement extends ChatElement {
    
    private String key = null;
    private List<Object> with = null;
    
    /**
     * Initialize a new instance of the ChatTranslateElement
     */
    public ChatTranslateElement() {
        super();
        
        this.with = new ArrayList<Object>();
    }
    
    /**
     * Adding a parameter used by the translation text
     * @param A object which represents the parameter
     * @return This ChatElement
     */
    public ChatElement addWith(Object with) {
    	this.with.add(with);
    	return this;
    }

    /**
     * Gets the key for this translation
     * @return The key of this translation text
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key of the translation text
     * @param The key of this translation text
     * @return This ChatElement
     */
    public ChatElement setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public void prepareElementsJson() {
        this.getElement().put("translate", this.key);
        if(this.with.size() > 0) {
        	this.getElement().put("with", this.with);
        }
    }
    
}
