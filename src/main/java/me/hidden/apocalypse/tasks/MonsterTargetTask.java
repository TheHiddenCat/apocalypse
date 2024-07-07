package me.hidden.apocalypse.tasks;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public final class MonsterTargetTask implements Runnable {
    private final Server server;

    public MonsterTargetTask(@NotNull Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        for (var world : server.getWorlds()) {
            var monsters = world.getEntitiesByClass(Monster.class);
            for (var monster : monsters) {
                if (monster.getType() == EntityType.ENDERMAN && monster.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                    continue;
                }

                Player nearestPlayer = null;
                var nearestDistance = Double.MAX_VALUE;
                for (var player : world.getPlayers()) {
                    var distance = player.getLocation().distance(monster.getLocation());
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestPlayer = player;
                    }
                }

                if (nearestPlayer != null) {
                    monster.setTarget(nearestPlayer);
                }
            }
        }
    }
}
