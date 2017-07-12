package fr.fallen.regions.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.fallen.regions.Region;
import fr.fallen.regions.RegionsManager;
import fr.fallen.regions.RegionsPlugin;
import fr.fallen.regions.events.RegionEnterEvent;
import fr.fallen.regions.events.RegionLeaveEvent;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 12/07/2017
 */

public class RegionChangeListener implements Listener {
	
	private final Map<UUID, Region> currentRegion = new HashMap<>();
	
	public RegionChangeListener(){
		Bukkit.getPluginManager().registerEvents(this, RegionsPlugin.getPlugin());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onMove(PlayerMoveEvent event){
		if(event.getFrom().equals(event.getTo()))
			return;
		Player player = event.getPlayer();
		Location to = event.getTo();
		Region oldRegion = currentRegion.get(player.getUniqueId());
		Region newRegion = RegionsManager.getManager(to.getWorld()).getRegion(to.getChunk());
		if(oldRegion != newRegion){
			if(oldRegion != null)
				Bukkit.getPluginManager().callEvent(new RegionLeaveEvent(player, oldRegion));
			Bukkit.getPluginManager().callEvent(new RegionEnterEvent(player, newRegion));
			currentRegion.put(player.getUniqueId(), newRegion);
		}
	}
	
}
