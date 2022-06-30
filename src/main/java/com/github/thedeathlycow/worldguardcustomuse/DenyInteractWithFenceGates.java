package com.github.thedeathlycow.worldguardcustomuse;

import org.bukkit.Material;
import org.bukkit.Tag;

public class DenyInteractWithFenceGates extends DenyInteractBooleanFlag {
    @Override
    protected boolean isProhibitedType(Material material) {
        return Tag.FENCE_GATES.isTagged(material);
    }

    @Override
    public String getName() {
        return "deny-interact-with-fence-gates";
    }
}
