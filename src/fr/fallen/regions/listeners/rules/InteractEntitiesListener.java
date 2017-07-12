package fr.fallen.regions.listeners.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 12/07/2017
 */

public class InteractEntitiesListener extends RuleListener {

	public InteractEntitiesListener() {
		super(RegionRules.INTERACT_ENTITIES);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
		if(!isAllowed(event.getPlayer(), event.getRightClicked().getLocation())){
			event.setCancelled(true);
		}
	}
	
}
