package com.github.thedeathlycow.worldguardcustomuse;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;
import java.util.UUID;

public class PlayerListener implements Listener {

    private WorldguardCustomInteract plugin;

    private static final UUID silentWareUUID;

    static {
        silentWareUUID = UUID.fromString("c9471857-6feb-44a1-96d1-46f149686ea1");
    }

    /**
     * Construct the object;
     *
     * @param plugin
     */
    public PlayerListener(WorldguardCustomInteract plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Material clickedMaterial = block.getType();
            ApplicableRegionSet regionSet =
                    WorldGuard.getInstance()
                            .getPlatform()
                            .getRegionContainer()
                            .createQuery()
                            .getApplicableRegions(BukkitAdapter.adapt(block.getLocation()));

            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);

            for (ProtectedRegion region : regionSet.getRegions()) {
                Set<Material> disallowedMaterials = region.getFlag(WorldguardCustomInteract.CUSTOM_INTERACT);

                if (!region.isMember(localPlayer) &&  disallowedMaterials != null
                        && disallowedMaterials.contains(clickedMaterial)) {
                    event.setCancelled(true);
                    player.sendMessage(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "Hey!"
                            + ChatColor.RESET + "" + ChatColor.GRAY + " You cannot do that here!"});

                    if (player.getUniqueId().compareTo(silentWareUUID) == 0) {
                        player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Hey silent, I made this specifically for you.");
                    }
                    return;
                }
            }
            event.setCancelled(false);
        }
    }
}
