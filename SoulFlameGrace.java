package com.soulflame.grace;

import org.bukkit.plugin.java.JavaPlugin;

public class SoulFlameGrace extends JavaPlugin {

    private GraceManager graceManager;

    @Override
    public void onEnable() {
        graceManager = new GraceManager(this);
        getServer().getPluginManager().registerEvents(
                new GraceListener(this, graceManager), this);
        getCommand("grace").setExecutor(
                new GraceCommand(graceManager));
    }
}
