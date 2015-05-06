package io.github.yannici.chatbuilderlib.chat.elements;

import io.github.yannici.chatbuilderlib.ChatElement;

public class ChatTranslateElement extends ChatElement {
    
    private String key = null;
    
    public ChatTranslateElement() {
        super();
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
    }
    
}
