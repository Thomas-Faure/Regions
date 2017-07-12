package fr.fallen.regions.listeners.rules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class DamagesListener extends RuleListener {

	public DamagesListener() {
		super(RegionRules.DAMAGES);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player && !isAllowed(event.getEntity().getLocation())){
			event.setCancelled(true);
		}
	}
	
}
