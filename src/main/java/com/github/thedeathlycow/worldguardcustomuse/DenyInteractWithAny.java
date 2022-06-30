package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Material;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DenyInteractWithAny extends DenyInteractFlag {

    private SetFlag<Material> flag;

    @Override
    @SuppressWarnings("unchecked")
    public void registerFlag(FlagRegistry registry, Logger logger) {
        try {
            flag = new SetFlag<>("deny-interact-with", new MaterialFlag(null));
            registry.register(flag);
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get(this.getName());
            if (existing instanceof SetFlag<?>) {
                try {
                    flag = (SetFlag<Material>) existing;
                } catch (ClassCastException classCastException) {
                    logger.log(Level.SEVERE, this.getName() + " flag is not an instance of SetFlag<Material>");
                    classCastException.printStackTrace();
                }
            } else {
                logger.log(Level.SEVERE, this.getName() + " flag is not an instance of SetFlag");
                throw e;
            }
        }
        super.registerFlag(registry, logger);
    }

    @Override
    public String getName() {
        return "deny-interact-with";
    }

    @Override
    public boolean shouldCancelAction(LocalPlayer player, ProtectedRegion region, Material material) {
        Set<Material> disallowedMaterials = region.getFlag(flag);
        return !(region.isMember(player))
                && disallowedMaterials != null
                && disallowedMaterials.contains(material);
    }


}
