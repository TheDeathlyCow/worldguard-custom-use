package com.github.thedeathlycow.worldguardcustomuse;

import org.bukkit.Material;
import org.bukkit.Tag;

public class DenyInteractWithButtons extends DenyInteractDefinedTypeFlag {
    @Override
    protected boolean isProhibitedType(Material material) {
        return Tag.BUTTONS.isTagged(material);
    }

    @Override
    public String getName() {
        return "deny-interact-with-buttons";
    }
}
