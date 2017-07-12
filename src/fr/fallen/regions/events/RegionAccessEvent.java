package fr.fallen.regions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import fr.fallen.regions.Region;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 12/07/2017
 */

public abstract class RegionAccessEvent extends Event {
	
	private final Player player;
	private final Region region;
	
	protected RegionAccessEvent(Player player, Region region){
		this.player = player;
		this.region = region;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Region getRegion(){
		return region;
	}
	
}
