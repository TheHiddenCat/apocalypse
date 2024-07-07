package me.hidden.apocalypse.listeners;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public final class MonsterTargetListener implements Listener {
    @EventHandler
    public void onMonsterTargetListener(EntityTargetEvent event) {
        if (!(event.getEntity() instanceof Monster)) {
            return;
        }
        if (!(event.getTarget() instanceof Player)) {
            event.setCancelled(true);
        }
    }
}
