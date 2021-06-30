package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.FlagContext;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import org.bukkit.Material;

public class MaterialFlag extends Flag<Material> {

    protected MaterialFlag(String name) {
        super(name);
    }

    @Override
    public Material parseInput(FlagContext context) throws InvalidFlagFormat {
        String input = context.getUserInput();
        try {
            return Material.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidFlagFormat("Expected a valid block ID, but got '" + input + "'");
        }
    }

    @Override
    public Material unmarshal(Object o) {
        assert o != null;
        String string = o.toString();
        try {
            return Material.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Object marshal(Material o) {
        return o.toString().toLowerCase();
    }
}
