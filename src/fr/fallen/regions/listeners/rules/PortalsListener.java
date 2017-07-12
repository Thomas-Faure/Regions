package fr.fallen.regions.listeners.rules;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.PortalCreateEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class PortalsListener extends RuleListener {

	public PortalsListener() {
		super(RegionRules.PORTALS);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPortalCreate(PortalCreateEvent event){
		if(cancelCreation(event.getBlocks())){
			event.setCancelled(true);
		}
	}
	
	private boolean cancelCreation(List<Block> blocks){
		for(Block block : blocks){
			if(!isAllowed(block.getLocation())){
				return true;
			}
		}
		return false;
	}
	
}
