package me.hidden.apocalypse;

import me.hidden.apocalypse.listeners.MonsterSpawnListener;
import me.hidden.apocalypse.listeners.MonsterTargetListener;
import me.hidden.apocalypse.tasks.MonsterBreakTask;
import me.hidden.apocalypse.tasks.MonsterBuildTask;
import me.hidden.apocalypse.tasks.MonsterTargetTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class Apocalypse extends JavaPlugin {
    @Override
    public void onEnable() {
//        Settings.populateConfig(getConfig());
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new MonsterTargetTask(getServer()), 0L, 120L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new MonsterBuildTask(getServer()), 0L, 80L);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new MonsterBreakTask(getServer(), getLogger()), 0L, 120L);
        getServer().getPluginManager().registerEvents(new MonsterSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new MonsterTargetListener(), this);
    }
}
