package fr.fallen.regions.listeners.rules;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class EnderPearlsListener extends RuleListener {

	public EnderPearlsListener() {
		super(RegionRules.ENDER_PEARLS);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onTeleport(PlayerTeleportEvent event){
		if(!isAllowed(event.getPlayer(), event.getTo())){
			event.setCancelled(true);
		}
	}
	
}
