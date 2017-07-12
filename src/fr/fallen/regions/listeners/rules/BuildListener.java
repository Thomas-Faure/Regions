package fr.fallen.regions.listeners.rules;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class BuildListener extends RuleListener {

	public BuildListener() {
		super(RegionRules.BUILD);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event){
		if(!canBuild(event.getPlayer(), event.getBlock())){
			event.setCancelled(true);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event){
		if(!canBuild(event.getPlayer(), event.getBlock())){
			event.setCancelled(true);
		}
	}
	
	private boolean canBuild(Player player, Block block){
		return isAllowed(player, block.getLocation());
	}
	
}
