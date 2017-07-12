package fr.fallen.regions.listeners.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class MobGriefingListener extends RuleListener {

	public MobGriefingListener() {
		super(RegionRules.MOB_GRIEFING);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityChangeBlock(EntityChangeBlockEvent event){
		if(!isAllowed(event.getBlock().getLocation())){
			event.setCancelled(true);
		}
	}
	
}
