package fr.fallen.regions.listeners.rules.base;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.fallen.regions.Region;
import fr.fallen.regions.RegionRules;
import fr.fallen.regions.RegionsManager;
import fr.fallen.regions.RegionsPlugin;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class RuleListener implements Listener {
	
	private final RegionRules rule;
	
	public RuleListener(RegionRules rule){
		this.rule = rule;
		
		Bukkit.getPluginManager().registerEvents(this, RegionsPlugin.getPlugin());
	}
	
	public boolean isAllowed(Player player, Location location){
		Region region = getRegion(location);
		return isAllowed(region) || bypass(player, region);
	}
	
	public boolean isAllowed(Location location){
		return isAllowed(getRegion(location));
	}
	
	private boolean isAllowed(Region region){
		return region.allow(rule);
	}
	
	public boolean bypass(Player player, Location location){
		return bypass(player, getRegion(location));
	}
	
	private boolean bypass(Player player, Region region){
		return bypass(player) || isMember(player, region);
	}
	
	private boolean bypass(Player player){
		return player.getGameMode() == GameMode.CREATIVE;
	}
	
	private boolean isMember(Player player, Region region){
		return region.isMember(player.getUniqueId());
	}
	
	private Region getRegion(Location location){
		return RegionsManager.getManager(location.getWorld()).getRegion(location.getChunk());
	}
	
}
