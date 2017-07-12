package fr.fallen.regions.listeners.rules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class InteractListener extends RuleListener {

	public InteractListener() {
		super(RegionRules.INTERACT);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(!isAllowed(player, player.getLocation())){
			event.setCancelled(true);
		}
	}
	
}
