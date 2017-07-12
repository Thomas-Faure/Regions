package fr.fallen.regions.listeners.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class EnterListener extends RuleListener {

	public EnterListener() {
		super(RegionRules.ENTER);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onMove(PlayerMoveEvent event){
		if(!isAllowed(event.getPlayer(), event.getTo())){
			event.setCancelled(true);
		}
	}
	
}
