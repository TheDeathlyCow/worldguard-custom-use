package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Material;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DenyInteractDefinedTypeFlag extends DenyInteractFlag {

    protected BooleanFlag flag;

    @Override
    public void registerFlag(FlagRegistry registry, Logger logger) {
        try {
            flag = new BooleanFlag(this.getName());
            registry.register(flag);
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get(this.getName());
            if (existing instanceof BooleanFlag booleanFlag) {
                flag = booleanFlag;
            } else {
                logger.log(Level.SEVERE, this.getName() + " flag is not an instance of " + BooleanFlag.class);
                throw e;
            }
        }

        super.registerFlag(registry, logger);
    }

    @Override
    public boolean shouldCancelAction(LocalPlayer player, ProtectedRegion region, Material material) {
        return !(region.isMember(player))
                && (Boolean.TRUE.equals(region.getFlag(flag)) && this.isProhibitedType(material));
    }

    protected abstract boolean isProhibitedType(Material material);
}
