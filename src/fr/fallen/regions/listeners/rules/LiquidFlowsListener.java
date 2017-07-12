package fr.fallen.regions.listeners.rules;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class LiquidFlowsListener extends RuleListener {

	public LiquidFlowsListener() {
		super(RegionRules.LIQUID_FLOWS);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onLiquidFlow(BlockFromToEvent event){
		Block from = event.getBlock();
		if(from.getType() == Material.WATER || from.getType() == Material.STATIONARY_WATER || from.getType() == Material.LAVA || from.getType() == Material.STATIONARY_LAVA){
			if(isAllowed(from.getLocation()) && !isAllowed(event.getToBlock().getLocation())){
				event.setCancelled(true);
			}
		}
	}
	
}
