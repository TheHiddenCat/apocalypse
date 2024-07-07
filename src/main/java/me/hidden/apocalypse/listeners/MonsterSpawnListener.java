package me.hidden.apocalypse.listeners;

import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Random;

public final class MonsterSpawnListener implements Listener {
    private final Random random;

    public MonsterSpawnListener() {
        this.random = new Random();
    }

    @EventHandler
    public void onMonsterSpawnListener(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Monster monster) {
            var attribute = monster.getAttribute(Attribute.GENERIC_FOLLOW_RANGE);
            var attribute2 = monster.getAttribute(Attribute.GENERIC_SCALE);
            if (attribute != null) {
                attribute.setBaseValue(100.0);
            }
            if (attribute2 != null) {
                attribute2.setBaseValue(this.random.nextDouble(0.5, 1.0) + 0.2);
            }
            var monsterType = monster.getType();
            var spawn_amount = this.random.nextInt(2);
            for (int i = 0; i < spawn_amount; i++) {
                monster.getWorld().spawnEntity(monster.getLocation(), monsterType, true);
            }
        }
    }

    @EventHandler
    public void onSkeletonSpawnListener(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Skeleton skeleton && event.getEntity().getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
            var odd = this.random.nextInt(8);
            if (odd != 0) {
                skeleton.getEquipment().setItemInMainHand(null);
            }
        }
    }
}
