package com.github.thedeathlycow.worldguardcustomuse;

import org.bukkit.Material;
import org.bukkit.Tag;

public class DenyInteractWithTrapdoors extends DenyInteractDefinedTypeFlag {

    @Override
    public String getName() {
        return "deny-interact-with-trapdoors";
    }

    @Override
    protected boolean isProhibitedType(Material material) {
        return Tag.TRAPDOORS.isTagged(material);
    }
}
