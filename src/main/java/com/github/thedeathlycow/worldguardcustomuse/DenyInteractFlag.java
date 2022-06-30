package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

import java.util.logging.Logger;

public abstract class DenyInteractFlag implements PlayerListener.InteractListener {

    public void registerFlag(FlagRegistry registry, Logger logger) {
        PlayerListener.registerInteractListener(this);
    }

    public abstract String getName();

}
