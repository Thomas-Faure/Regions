package fr.fallen.regions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import fr.fallen.regions.Region;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 12/07/2017
 */

public class RegionEnterEvent extends RegionAccessEvent {
	
	private static final HandlerList handlers = new HandlerList();
	
	public RegionEnterEvent(Player player, Region region) {
		super(player, region);
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
}
