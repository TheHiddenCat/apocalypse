package me.hidden.apocalypse;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public final class Settings {
    public static final int MONSTER_BUILD_BLOCK_ODDS = 5;
    public static final Material MONSTER_BUILD_BLOCK_TYPE = Material.SOUL_SAND;
    public static final int MONSTER_BREAK_BLOCK_ODDS = 5;
    public static final Set<Material> MONSTER_BREAK_IGNORE_BLOCKS = Set.of(
            Material.AIR,
            Material.STONE_BUTTON,
            Material.REDSTONE_WIRE,
            Material.STONE_PRESSURE_PLATE
    );
    public static final Map<Material, Integer> MONSTER_BREAK_BLOCK_TOUGHNESS_ODDS = Map.ofEntries(
            Map.entry(Material.BRICK, 1000),
            Map.entry(Material.BRICKS, 1000),
            Map.entry(Material.BRICK_SLAB, 1000),
            Map.entry(Material.BRICK_STAIRS, 1000),
            Map.entry(Material.BRICK_WALL, 1000),

            Map.entry(Material.MOSSY_STONE_BRICK_SLAB, 1000),
            Map.entry(Material.MOSSY_STONE_BRICK_STAIRS, 1000),
            Map.entry(Material.MOSSY_STONE_BRICKS, 1000),
            Map.entry(Material.MOSSY_STONE_BRICK_WALL, 1000),

            Map.entry(Material.DEEPSLATE_BRICKS, 1000),
            Map.entry(Material.DEEPSLATE_BRICK_SLAB, 1000),
            Map.entry(Material.DEEPSLATE_BRICK_STAIRS, 1000),
            Map.entry(Material.DEEPSLATE_BRICK_WALL, 1000),

            Map.entry(Material.IRON_DOOR, 1500),
            Map.entry(Material.IRON_TRAPDOOR, 1500),

            Map.entry(Material.NETHER_BRICK, 2000),
            Map.entry(Material.NETHER_BRICK_FENCE, 2000),
            Map.entry(Material.NETHER_BRICK_STAIRS, 2000),
            Map.entry(Material.NETHER_BRICK_WALL, 2000),
            Map.entry(Material.NETHER_BRICK_SLAB, 2000),

            Map.entry(Material.RED_NETHER_BRICKS, 3000),
            Map.entry(Material.RED_NETHER_BRICK_SLAB, 3000),
            Map.entry(Material.RED_NETHER_BRICK_WALL, 3000),
            Map.entry(Material.RED_NETHER_BRICK_STAIRS, 3000),

            Map.entry(Material.OBSIDIAN, 3500),
            Map.entry(Material.CRYING_OBSIDIAN, 3500),

            Map.entry(Material.END_STONE, 3000),
            Map.entry(Material.END_STONE_BRICK_SLAB, 5000),
            Map.entry(Material.END_STONE_BRICKS, 5000),
            Map.entry(Material.END_STONE_BRICK_STAIRS, 5000),
            Map.entry(Material.END_STONE_BRICK_WALL, 5000)
    );

    public static void populateConfig(@NotNull FileConfiguration config) {
        config.set("monster.build.chance", MONSTER_BUILD_BLOCK_ODDS);
        config.set("monster.build.material", MONSTER_BUILD_BLOCK_TYPE.toString());

        config.set("monster.break.chance", MONSTER_BREAK_BLOCK_ODDS);
        config.set("monster.break.ignore", MONSTER_BREAK_IGNORE_BLOCKS
                .stream()
                .map(Material::toString)
                .toArray(String[]::new));
        for (var entry : MONSTER_BREAK_BLOCK_TOUGHNESS_ODDS.entrySet()) {
            Material material = entry.getKey();
            Integer toughness = entry.getValue();
            config.set("monster.break.materials" + material.toString(), toughness);
        }
    }
}
