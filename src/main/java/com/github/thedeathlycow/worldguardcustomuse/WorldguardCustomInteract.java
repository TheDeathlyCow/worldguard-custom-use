package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldguardCustomInteract extends JavaPlugin {

    public static SetFlag<Material> CUSTOM_INTERACT;

    @Override
    public void onLoad() {
        System.out.println("[WorldGuard Custom Interact] Custom interact loaded!");
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            SetFlag<Material> flag = new SetFlag<Material>("deny-interact-with", new MaterialFlag(null));
            registry.register(flag);
            CUSTOM_INTERACT = flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("deny-interact-with");
            if (existing instanceof SetFlag<?>) {
                CUSTOM_INTERACT = (SetFlag<Material>) existing;
            } else {
                throw e;
            }
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
