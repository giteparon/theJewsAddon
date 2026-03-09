package com.example.addon.utils;
import com.example.addon.mixin.ExampleMixin;
import com.example.addon.mixin.IClientAccessor;
import com.example.addon.modules.*;
import com.example.addon.AddonTemplate;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
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



public class clickUtils {
    private final MinecraftClient client = MinecraftClient.getInstance();
    public static final Logger LOG = LogUtils.getLogger();
    public static boolean clickGlobal;
    @EventHandler
    public static void click(MinecraftClient client) {
        LOG.info("clicked");
        ((IClientAccessor) (Object) client).callDoItemUse();
//        if (client.player == null) return;
//
//        if (clickGlobal) {
//            client.options.useKey.setPressed(true);
//            clickGlobal = false;
//        } else {
//            client.options.useKey.setPressed(false);
//        }
    }
}
