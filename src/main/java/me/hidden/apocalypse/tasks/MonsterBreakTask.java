package me.hidden.apocalypse.tasks;

import me.hidden.apocalypse.Settings;
import org.bukkit.Server;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.logging.Logger;

public final class MonsterBreakTask implements Runnable {
    private final Server server;
    private final Random random;
    private final Logger logger;

    public MonsterBreakTask(@NotNull Server server, @NotNull Logger logger) {
        this.server = server;
        this.random = new Random();
        this.logger = logger;
    }

    @Override
    public void run() {
        for (var world : server.getWorlds()) {
            var monsters = world.getEntitiesByClass(Monster.class);
            for (var monster : monsters) {
                var break_roll = this.random.nextInt(Settings.MONSTER_BREAK_BLOCK_ODDS);
                if (break_roll > 0) {
                    continue;
                }
                var block = monster.getTargetBlock(Settings.MONSTER_BREAK_IGNORE_BLOCKS, 2);
                var block_toughness = Settings.MONSTER_BREAK_BLOCK_TOUGHNESS_ODDS.get(block.getType());
                if (block_toughness != null) {
                    var toughness_roll = this.random.nextInt(block_toughness);
                    if (toughness_roll > 0) {
                        continue;
                    }
                }

                block.breakNaturally();
            }
        }
    }
}
