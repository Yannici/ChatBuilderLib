package io.github.yannici.chatbuilderlib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.github.yannici.chatbuilderlib.chat.elements.ChatScoreElement;
import io.github.yannici.chatbuilderlib.chat.elements.ChatTextElement;
import io.github.yannici.chatbuilderlib.chat.elements.ChatTranslateElement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class ChatBuilder {
	
	private List<ChatElement> elements = null;
	
	public ChatBuilder() {
		this.elements = new ArrayList<ChatElement>();
	}
	
	public ChatElement appendText(String text) {
		ChatTextElement element = new ChatTextElement();
		element.setText(text);
		
		this.elements.add(element);
		return element;
	}
	
	public ChatElement appendTranslate(String translateKey) {
	    ChatTranslateElement element = new ChatTranslateElement();
        element.setKey(translateKey);
        
        this.elements.add(element);
        return element;
	}
	
	public ChatElement appendTranslate(String translateKey, List<Object> with) {
		ChatTranslateElement element = new ChatTranslateElement();
		element.setKey(translateKey);
		
		for(Object wObj : with) {
			element.addWith(wObj);
		}
		
		this.elements.add(element);
		return element;
	}
	
	public ChatElement appendScore(String name, String objective) {
	    ChatScoreElement element = new ChatScoreElement();
	    element.setName(name);
	    element.setObjective(objective);
	    
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
	
	public void sendToPlayer(Player player) {
	    this.sendNMS(player, this.getJson());
	}
	
	private void sendNMS(Player player, String json) {
	    Constructor<?> oneEightConstr = null;
	    Constructor<?> oneSevenConstr = null;
	    Constructor<?> constrOneParam = null;
	    Constructor<?> finalConstr = null;
	    
        // newer packets
        Class<?> packetPlayOutChatClass = Builder.getInstance().getMinecraftServerClass("PacketPlayOutChat");
        Class<?> chatBaseComponentClass = Builder.getInstance().getMinecraftServerClass("IChatBaseComponent");
        
        if(chatBaseComponentClass == null) {
            Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
            return;
        }
        
        if(packetPlayOutChatClass != null) {
            // 1.8
            oneEightConstr = Builder.getInstance().getConstructorOfClass(packetPlayOutChatClass, new Class<?>[]{chatBaseComponentClass, byte.class});
            if(oneEightConstr == null) {
                // 1.7
                oneSevenConstr = Builder.getInstance().getConstructorOfClass(packetPlayOutChatClass, new Class<?>[]{chatBaseComponentClass, boolean.class});
                if(oneSevenConstr == null) {
                    // ?? use without second param
                    constrOneParam = Builder.getInstance().getConstructorOfClass(packetPlayOutChatClass, new Class<?>[]{chatBaseComponentClass});
                    if(constrOneParam == null) {
                        // no constructor available
                        Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
                        return;
                    } else {
                        finalConstr = constrOneParam;
                    }
                } else {
                    finalConstr = oneSevenConstr;
                }
            } else {
                finalConstr = oneEightConstr;
            }
            
            Class<?> chatSerializerClass = Builder.getInstance().getCorrectChatSerializerClass();
            if(chatSerializerClass == null) {
                // ChatSerializer not found
                Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
                return;
            }
            
            Method aStaticMethod = null;
            try {
                aStaticMethod = chatSerializerClass.getMethod("a", new Class<?>[]{String.class});
            } catch(Exception ex) {
                Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
                return;
            }
            
            Object packet = null;
            try {
                aStaticMethod.setAccessible(true);
                Object chatBaseComponent = aStaticMethod.invoke(null, new Object[]{json});
                
                if(finalConstr.equals(oneEightConstr)) {
                    packet = finalConstr.newInstance(new Object[]{chatBaseComponent, (byte)1});
                } else if(finalConstr.equals(oneSevenConstr)) {
                    packet = finalConstr.newInstance(new Object[]{chatBaseComponent, true});
                } else {
                    packet = finalConstr.newInstance(new Object[]{chatBaseComponent});
                }
            } catch(Exception ex) {
                // failed
                Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
                return;
            }
            
            if(packet == null) {
                Builder.getInstance().getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "This plugin isn't compatible with this server version!");
                return;
            }
            
            Builder.getInstance().sendPlayerPacket(player, packet);
        } else {
            // TODO: earlier version
        }
    }
	
}
