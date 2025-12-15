package com.soulflame.grace;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GraceCommand implements CommandExecutor {

    private final GraceManager manager;

    public GraceCommand(GraceManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§7Unlocked Soul Flames:");
            for (String grace : manager.getUnlocked(player)) {
                player.sendMessage(" §8- §b" + grace);
            }
            return true;
        }

        String target = args[0];

        if (!manager.hasUnlocked(player, target)) {
            player.sendMessage("§cYou have not unlocked this Soul Flame.");
            return true;
        }

        Location location = manager.getLocation(target);
        if (location == null) {
            player.sendMessage("§cThat Soul Flame location is missing.");
            return true;
        }

        player.teleport(location);
        player.playSound(player.getLocation(),
                org.bukkit.Sound.BLOCK_SOUL_FIRE_AMBIENT, 1f, 1f);

        return true;
    }
}
