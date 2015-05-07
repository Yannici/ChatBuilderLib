package io.github.yannici.chatbuilderlib.chat.elements;

import java.util.ArrayList;
import java.util.List;

import io.github.yannici.chatbuilderlib.ChatElement;

public class ChatTranslateElement extends ChatElement {
    
    private String key = null;
    private List<Object> with = null;
    
    public ChatTranslateElement() {
        super();
        
        this.with = new ArrayList<Object>();
    }
    
    public ChatElement addWith(Object with) {
    	this.with.add(with);
    	return this;
    }

    public String getText() {
        return this.key;
    }

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
