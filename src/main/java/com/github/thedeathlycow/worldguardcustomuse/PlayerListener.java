package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerListener implements Listener {

    private static final UUID silentWareUUID;
    private static final Set<InteractListener> interactListeners = new HashSet<>();

    public static void registerInteractListener(InteractListener listener) {
        interactListeners.add(listener);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getClickedBlock();
        if (block != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            final Material clickedMaterial = block.getType();
            final ApplicableRegionSet regionSet =
                    WorldGuard.getInstance()
                            .getPlatform()
                            .getRegionContainer()
                            .createQuery()
                            .getApplicableRegions(BukkitAdapter.adapt(block.getLocation()));

            final LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);

            for (ProtectedRegion region : regionSet.getRegions()) {
                for (InteractListener listener : interactListeners) {
                    if (listener.shouldCancelAction(localPlayer, region, clickedMaterial)) {
                        event.setCancelled(true);
                        player.sendMessage(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "Hey!"
                                + ChatColor.RESET + "" + ChatColor.GRAY + " You cannot do that here!"});

                        if (player.getUniqueId().equals(silentWareUUID)) {
                            player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Hey silent, I made this specifically for you.");
                        }
                        return;
                    }
                }
            }
        }
    }

    @FunctionalInterface
    public interface InteractListener {

        /**
         * Applies the interaction listener.
         *
         * @param player {@link LocalPlayer} that attempted to interact
         * @param region {@link ProtectedRegion} of the block that was clicked
         * @param clickedMaterial {@link Material} that was clicked
         * @return Returns true if the interaction should be cancelled.
         */
        boolean shouldCancelAction(LocalPlayer player, ProtectedRegion region, Material clickedMaterial);

    }

    static {
        silentWareUUID = UUID.fromString("c9471857-6feb-44a1-96d1-46f149686ea1");
    }
}
