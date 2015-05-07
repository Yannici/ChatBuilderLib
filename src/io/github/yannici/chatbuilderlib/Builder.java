package io.github.yannici.chatbuilderlib;

import io.github.yannici.chatbuilderlib.chat.elements.ChatTranslateElement;
import io.github.yannici.chatbuilderlib.chat.events.ChatClickEventAction;
import io.github.yannici.chatbuilderlib.chat.events.ChatHoverEventAction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Builder extends JavaPlugin implements Listener {
    
    private static Builder instance = null;
    
    private Package craftbukkit = null;
    private Package minecraft = null;
    private String version = null;

    @Override
    public void onEnable() {
        Builder.instance = this;
        
        this.loadVersion();
        
        ChatBuilder builder = new ChatBuilder();
        ChatTranslateElement translate = (ChatTranslateElement)builder.appendTranslate("multiplayer.player.joined");
        translate.addWith("Yannici");
        translate.addWith(4);
        
        this.getServer().getConsoleSender().sendMessage(builder.getJson());
    }
    
    @Override
    public void onDisable() {
        // nothing to do
    }
    
    public static Builder getInstance() {
        return Builder.instance;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (!(sender instanceof Player)) {
            return false;
        }
        
        Player player = (Player) sender;
        
        if (cmd.getName().equalsIgnoreCase("cbl")) {
            ChatBuilder builder = new ChatBuilder();
            builder.appendText(ChatColor.YELLOW + "That is my new ");
            
            ChatElement link = builder.appendText("ChatBuilderLib-Plugin");
            link.setClickEvent(ChatClickEventAction.OPEN_URL, "https://github.com/Yannici/ChatBuilderLib");
            link.setBold(true);
            link.setColor("green");
            link.setHoverEvent(ChatHoverEventAction.SHOW_TEXT, ChatColor.AQUA + "YEAH!");
            link.setUnderlined(true);
            
            // no attributes
            builder.appendText("! ");
            
            // one line
            builder.appendText("And I love it!")
                .setColor(ChatColor.RED)
                .setItalic(true)
                .setHoverEvent(ChatHoverEventAction.SHOW_TEXT, ChatColor.GREEN + "SO MUCH!");
            
            // send to player
            builder.sendToPlayer(player);
            return true;
        }
        
        return false; 
    }
    
    private String loadVersion() {
        String packName = Bukkit.getServer().getClass().getPackage().getName();
        return packName.substring(packName.lastIndexOf('.') + 1);
    }

    public String getCurrentVersion() {
        return this.version;
    }
    
    public Package getCraftBukkit() {
        try {
            if (this.craftbukkit == null) {
                return Package.getPackage("org.bukkit.craftbukkit."
                        + Bukkit.getServer().getClass().getPackage().getName()
                                .replace(".", ",").split(",")[3]);
            } else {
                return this.craftbukkit;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public Package getMinecraftPackage() {
        try {
            if (this.minecraft == null) {
                return Package.getPackage("net.minecraft.server."
                        + Bukkit.getServer().getClass().getPackage().getName()
                                .replace(".", ",").split(",")[3]);
            } else {
                return this.minecraft;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public Class getCraftBukkitClass(String classname) {
        try {
            if (this.craftbukkit == null) {
                this.craftbukkit = this.getCraftBukkit();
            }

            return Class.forName(this.craftbukkit.getName() + "." + classname);
        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public Class getMinecraftServerClass(String classname) {
        try {
            if (this.minecraft == null) {
                this.minecraft = this.getMinecraftPackage();
            }

            return Class.forName(this.minecraft.getName() + "." + classname);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static String prefix() {
        return ChatColor.GRAY + "[" + ChatColor.AQUA + "ChatBuilderLib" + ChatColor.GRAY + "] " + ChatColor.WHITE;
    }
    
    public void sendPlayerPacket(Player player, Object packet) {
        try {
            Class<?> craftPlayerClass = this.getCraftBukkitClass("entity.CraftPlayer");
            Method getHandleMethod = craftPlayerClass.getMethod("getHandle", new Class<?>[0]);
            getHandleMethod.setAccessible(true);
            
            Class<?> entityPlayerClass = this.getMinecraftServerClass("EntityPlayer");
            Class<?> playerConnectionClass = this.getMinecraftServerClass("PlayerConnection");
            Class<?> packetClass = this.getMinecraftServerClass("Packet");
            
            Object nmsPlayer = getHandleMethod.invoke(player, new Object[0]);
            Field playerConnectionField = entityPlayerClass.getDeclaredField("playerConnection");
            playerConnectionField.setAccessible(true);
            
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method sendPacketMethod = playerConnectionClass.getMethod("sendPacket", new Class<?>[]{packetClass});
            sendPacketMethod.setAccessible(true);
            
            sendPacketMethod.invoke(playerConnection, new Object[]{packet});
        } catch(Exception ex) {
            this.getServer().getConsoleSender().sendMessage(Builder.prefix() + ChatColor.RED + "Send packet failed, maybe not compatible with minecraft server version?");
        }
    }
 
    public Constructor<?> getConstructorOfClass(Class<?> clazz, Class<?>[] paramTypes) {
        try {
            Constructor<?> constr = clazz.getConstructor(paramTypes);
            return constr;
        } catch(Exception ex) {
            // no constructor
        }
        
        return null;
    }
    
    public Class<?> getCorrectChatSerializerClass() {
        Class<?> clazz = null;
        
        try {
            Class<?> chatBaseComponent = this.getMinecraftServerClass("IChatBaseComponent");
            Class<?>[] classes = chatBaseComponent.getDeclaredClasses();
            for(Class<?> cl : classes) {
                if(cl.getSimpleName().equals("ChatSerializer")) {
                    clazz = cl;
                }
            }
            
            if(clazz == null) {
                // 1.7 or earlier
                clazz = this.getMinecraftServerClass("ChatSerializer");
            }
        } catch(Exception ex) {
            // failed
        }
        
        return clazz;
    }
}
