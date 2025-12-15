package com.soulflame.grace;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GraceListener implements Listener {

    private final SoulFlameGrace plugin;
    private final GraceManager manager;

    public GraceListener(SoulFlameGrace plugin, GraceManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;

        Block block = event.getClickedBlock();
        if (block.getType() != Material.SOUL_CAMPFIRE) return;

        event.setCancelled(true);
        Player player = event.getPlayer();

        String graceId = block.getWorld().getName() + "_"
                + block.getX() + "_" + block.getY() + "_" + block.getZ();

        if (!manager.hasUnlocked(player, graceId)) {
            manager.unlockGrace(player, graceId,
                    block.getLocation().add(0.5, 1, 0.5));
            player.sendMessage("§5Soul Flame Lit");
        }

        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);

        player.playSound(player.getLocation(),
                Sound.ITEM_TOTEM_USE, 1.2f, 0.8f);

        block.getWorld().spawnParticle(
                Particle.SOUL,
                block.getLocation().add(0.5, 1, 0.5),
                40, 0.3, 0.6, 0.3, 0.01
        );
    }
}
