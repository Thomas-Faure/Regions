package fr.fallen.regions.listeners.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBurnEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class FireListener extends RuleListener {

	public FireListener() {
		super(RegionRules.FIRE);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBurn(BlockBurnEvent event){
		if(!isAllowed(event.getBlock().getLocation())){
			event.setCancelled(true);
		}
	}
	
}
