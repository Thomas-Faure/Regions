package fr.fallen.regions.listeners.rules;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import fr.fallen.regions.RegionRules;
import fr.fallen.regions.listeners.rules.base.PvxListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class PvpListener extends PvxListener {

	public PvpListener() {
		super(RegionRules.PVP);
	}

	@Override
	protected boolean isConcerned(Entity entity) {
		return entity instanceof Player;
	}
	
}
