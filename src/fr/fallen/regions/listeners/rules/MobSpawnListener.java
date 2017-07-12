package fr.fallen.regions.listeners.rules;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.RuleListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class MobSpawnListener extends RuleListener {

	public MobSpawnListener() {
		super(RegionRules.MOB_SPAWN);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		if(!isAllowed(event.getLocation()) && event.getSpawnReason() != SpawnReason.CUSTOM && event.getSpawnReason() != SpawnReason.DEFAULT){
			if(event.getEntity() instanceof Monster || event.getEntity() instanceof Slime)
				event.setCancelled(true);
		}
	}
	
}
