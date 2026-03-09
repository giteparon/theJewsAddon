package com.example.addon.modules;
import com.example.addon.AddonTemplate;
import com.example.addon.utils.clickUtils;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.events.entity.player.InteractBlockEvent;
import meteordevelopment.meteorclient.events.meteor.MouseClickEvent;
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
import net.minecraft.client.gui.Click;
import net.minecraft.client.input.MouseInput;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import net.fabricmc.loader.impl.util.log.Log;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;



public class TpBotModule extends Module{

    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();
    private final MinecraftClient client = MinecraftClient.getInstance();
    public static final Logger LOG = LogUtils.getLogger();

    private final Setting<List<String>> playerList = sgGeneral.add(new StringListSetting.Builder()
        .name("List Of Players")
        .description("Players which are whitelisted on the command")
        .defaultValue("")
        .build()
    );
//    private final Setting<String> message = sgGeneral.add(new StringSetting.Builder()
//        .name("message")
//        .description("message to shush, enter the sign % in order to replace it with name")
//        .defaultValue("")
//        .build()
//    );
    public TpBotModule() {
        super(AddonTemplate.CATEGORY, "Tp Bot", "activate for tp bot to work");
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
//        if(name.get().get(i).equals(player) && client.player != null && !player.equals(client.player.getStringifiedName())){
        if(message.contains("!tp")){
            handleCommands("tp", player);
        }

//        }


    }
    private void handleCommands(String cmd, String player){
//        String msg = String.valueOf(message);
//        for(int i = 0; i < msg.length(); i++){
//            if(msg.charAt(i) == '%'){
//                msg = msg.substring(0,i) + player + msg.substring(i + 1);
//            }
//        }
        if(cmd.equals("tp") && playerList.get().contains(player) && client != null){
            LOG.info("tping");
            client.getNetworkHandler().sendChatMessage("tping player " + player);
            clickUtils.click(client);
//            if(client.player != null && !message.equals("")){
//                client.player.networkHandler.sendChatMessage(msg);
//            } else if (client.player != null && message.equals("")) {
//                LOG.info("no message set");
//            }
//            else{
//                LOG.info("no player");
//            }
//            client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND, );

//            double blockY = block.get().getY();
//            double blockX = block.get().getX();
//            double blockZ = block.get().getZ();


//            client.player.setHeadYaw();


        }
    }

//    private BlockHitResult blockPos(){
//        client.player.sendMessage(Text.literal("Interact with the block you want to set"), false);
//        return null;
//
//    }
    @EventHandler
    private BlockHitResult onInteract(MouseClickEvent e){
        LOG.info("a");
        return null;
    }


}
