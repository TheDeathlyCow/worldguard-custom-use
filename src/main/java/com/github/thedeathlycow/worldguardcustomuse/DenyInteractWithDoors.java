package com.github.thedeathlycow.worldguardcustomuse;

import org.bukkit.Material;
import org.bukkit.Tag;

public class DenyInteractWithDoors extends DenyInteractDefinedTypeFlag {

    @Override
    public String getName() {
        return "deny-interact-with-doors";
    }

    @Override
    protected boolean isProhibitedType(Material material) {
        return Tag.DOORS.isTagged(material);
    }
}
