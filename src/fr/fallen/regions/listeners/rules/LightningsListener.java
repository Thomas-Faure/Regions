package fr.fallen.regions.listeners.rules;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLightningStrike;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.LightningStrikeEvent;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class LightningsListener extends RuleListener {

	public LightningsListener() {
		super(RegionRules.LIGHTNINGS);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onLightningStrike(LightningStrikeEvent event){
		LightningStrike lightning = event.getLightning();
		if(!isAllowed(lightning.getLocation())){
			((CraftLightningStrike) lightning).getHandle().isEffect = true;
		}
	}
	
}
