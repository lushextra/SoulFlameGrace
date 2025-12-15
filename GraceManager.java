package com.soulflame.grace;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class GraceManager {

    private final Map<UUID, Set<String>> unlockedGraces = new HashMap<>();
    private final Map<String, Location> graceLocations = new HashMap<>();

    public GraceManager(Object plugin) {
        // kept for future expansion (saving/loading)
    }

    public void unlockGrace(Player player, String graceId, Location location) {
        unlockedGraces
                .computeIfAbsent(player.getUniqueId(), k -> new HashSet<>())
                .add(graceId);
        graceLocations.put(graceId, location);
    }

    public boolean hasUnlocked(Player player, String graceId) {
        return unlockedGraces
                .getOrDefault(player.getUniqueId(), Collections.emptySet())
                .contains(graceId);
    }

    public Set<String> getUnlocked(Player player) {
        return unlockedGraces
                .getOrDefault(player.getUniqueId(), Collections.emptySet());
    }

    public Location getLocation(String graceId) {
        return graceLocations.get(graceId);
    }
}
