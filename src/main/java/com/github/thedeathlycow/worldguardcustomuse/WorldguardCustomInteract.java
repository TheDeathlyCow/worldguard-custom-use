package com.github.thedeathlycow.worldguardcustomuse;

import com.google.common.collect.ImmutableList;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class WorldguardCustomInteract extends JavaPlugin {

    private final List<DenyInteractFlag> flags;

    public WorldguardCustomInteract() {
        this.flags = List.of(
                new DenyInteractWithAny(),
                new DenyInteractWithDoors(),
                new DenyInteractWithTrapdoors(),
                new DenyInteractWithFenceGates(),
                new DenyInteractWithButtons()
        );
    }

    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        for (DenyInteractFlag flag : this.flags) {
            flag.registerFlag(registry, this.getLogger());
        }
        this.getLogger().info("Plugin loaded!");
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
