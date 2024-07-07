package me.hidden.apocalypse.tasks;

import me.hidden.apocalypse.Settings;
import org.bukkit.Server;
import org.bukkit.entity.Monster;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class MonsterBuildTask implements Runnable {
    private final Server server;
    private final Random random;

    public MonsterBuildTask(@NotNull Server server) {
        this.server = server;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (var world : this.server.getWorlds()) {
            var monsters = world.getEntitiesByClass(Monster.class);
            for (var monster : monsters) {
                var target = monster.getTarget();
                if (target == null) {
                    continue;
                }
                if (target.getLocation().getBlockY() < monster.getLocation().getBlockY()) {
                    continue;
                }
                var roll = this.random.nextInt(Settings.MONSTER_BUILD_BLOCK_ODDS);
                if (roll == 0) {
                    var location = monster.getLocation();
                    if (location.getBlock().getType() == Settings.MONSTER_BUILD_BLOCK_TYPE) {
                        location.add(new Vector(0d, 1d, 0d));
                        monster.setJumping(true);
                    }
                    location.getBlock().setType(Settings.MONSTER_BUILD_BLOCK_TYPE);
                }
            }
        }
    }
}
