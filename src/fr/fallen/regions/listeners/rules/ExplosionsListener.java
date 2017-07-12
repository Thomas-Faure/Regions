package fr.fallen.regions.listeners.rules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class ExplosionsListener extends RuleListener {

	public ExplosionsListener() {
		super(RegionRules.EXPLOSIONS);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event){
		filterBlocks(event.blockList());
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockExplode(BlockExplodeEvent event){
		filterBlocks(event.blockList());
	}
	
	private void filterBlocks(List<Block> blocks){
		for(Block block : new ArrayList<>(blocks)){
			if(!isAllowed(block.getLocation()))
				blocks.remove(block);
		}
	}
	
}
