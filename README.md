# ChatBuilderLib

## What is the ChatBuilderLib?

The **ChatBuilderLib** was developed because I was asking me how I get links or hover effects to the chat.
So I searched a lot in the internet and found the Chat-Packet of the minecraft packet protocol. I read it and
thought: Wow, that's easy! But I decided to go a next step: How could I translate that difficult packet protocol to
classes and makes it easy to use for other developers. Very fast I decided to make it like the StringBuilder of java and ...  
  
**here we are!**

## Example

I think a example should be enough for every developer. All features of this builder they can find
in the source or jar ;)  

![Example chat](https://sc-cdn.scaleengine.net/i/4a6125312fa9ebc3b4e23238eb06c42c.png)  
![Example link](https://sc-cdn.scaleengine.net/i/8ce3f82d08a96e88d393cd20d997a325.png)  

Example code for that is:  
```java
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
```

## Support

Support that project by checking for bugs and creating pull requests or post issues :)  
That would be nice!