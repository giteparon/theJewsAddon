package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.commands.Command;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.misc.Notifier;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.orbit.EventHandler;


import net.fabricmc.loader.impl.util.log.Log;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModuleExample extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
    private final MinecraftClient client = MinecraftClient.getInstance();
    public static final Logger LOG = LogUtils.getLogger();
//    private final SettingGroup sgRender = this.settings.createGroup("Render");

    /**
     * Example setting.d
     * The {@code name} parameter should be in kebab-case.
     * If you want to access the setting from another class, simply make the setting {@code public}, and use
     * {@link meteordevelopment.meteorclient.systems.modules.Modules#get(Class)} to access the {@link Module} object.
     */
    private final Setting<List<String>> name = sgGeneral.add(new StringListSetting.Builder()
        .name("name")
        .description("name of who to shush")
        .defaultValue("")
        .build()
    );
    private final Setting<String> message = sgGeneral.add(new StringSetting.Builder()
        .name("message")
        .description("message to shush, enter the sign % in order to replace it with name")
        .defaultValue("")
        .build()
    );


    /**
     * The {@code name} parameter should be in kebab-case.
     */
    public ModuleExample() {
        super(AddonTemplate.CATEGORY, "shush", "shushes a person");
    }

    /**
     * Example event handling method.
     * Requires {@link AddonTemplate#getPackage()} to be setup correctly, otherwise the game will crash whenever the module is enabled.
     */

    @EventHandler
    private void onSpeak(ReceiveMessageEvent event) {
        String message = event.getMessage().getString();

        String player = "";
        for(int i = 0; i < message.length(); i++){
            if (message.charAt(i) == '<' ){
                for(int j = i + 1; j < message.length(); j++){
                    if(message.charAt(j) == '>'){
                        break;
                    }
                    player = message.substring(i + 1 , j + 1);
                }
                break;

            }
        }

        LOG.info(message + " sent by: "  + player);
        for(int i = 0; i < name.get().size(); i++ ){
            LOG.info("list content " + name.get().get(i));
            if(name.get().get(i).equals(player) && client.player != null && !player.equals(client.player.getStringifiedName())){
                handleCommands("shush", player);
            }
        }


    }
    private void handleCommands(String cmd, String player){
        String msg = String.valueOf(message);
        for(int i = 0; i < msg.length(); i++){
            if(msg.charAt(i) == '%'){
                msg = msg.substring(0,i) + player + msg.substring(i + 1);
            }
        }
        if(cmd.equals("shush")){
            LOG.info("shushing");
            if(client.player != null && !message.equals("")){
                client.player.networkHandler.sendChatMessage(msg);
            } else if (client.player != null && message.equals("")) {
                LOG.info("no message set");
            }
            else{
                LOG.info("no player");
            }


        }
    }
}
//import mineflayer from "mineflayer"
//
//function startBot() {
//  const botConfig = {
//        name: "fourouk",
//        password: "q69YDK5R24pUy"
//  }
//
//  const bot = mineflayer.createBot({
//        host: "alt.6b6t.org",
//        port: 25565,
//        username: botConfig.name,
//        version: "1.20",
//        auth: "offline"
//  })
//
//    bot.once("spawn", () => {
//        console.log([${bot.username}] Joined the server)
//
//    // Start walking forward
//    bot.setControlState("forward", true)
//  })
//
//    bot.on("messagestr", (msg) => {
//        console.log([CHAT] ${msg})
//
//    if (msg.includes("/login")) {
//        bot.chat(/login ${botConfig.password})
//        console.log([${bot.username}] Sent login command)
//    }
//  })
//
//    bot.on("end", () => {
//        console.log([${bot.username}] Disconnected — restarting)
//    setTimeout(startBot, 3000)
//  })
//}
//
//startBot()
