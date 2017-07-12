package fr.fallen.regions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.fallen.regions.listeners.RegionChangeListener;
import fr.fallen.regions.listeners.rules.AnimalSpawnListener;
import fr.fallen.regions.listeners.rules.BuildListener;
import fr.fallen.regions.listeners.rules.DamagesListener;
import fr.fallen.regions.listeners.rules.DropListener;
import fr.fallen.regions.listeners.rules.EnderPearlsListener;
import fr.fallen.regions.listeners.rules.EnterListener;
import fr.fallen.regions.listeners.rules.ExplosionsListener;
import fr.fallen.regions.listeners.rules.FireListener;
import fr.fallen.regions.listeners.rules.InteractEntitiesListener;
import fr.fallen.regions.listeners.rules.InteractListener;
import fr.fallen.regions.listeners.rules.LightningsListener;
import fr.fallen.regions.listeners.rules.LiquidFlowsListener;
import fr.fallen.regions.listeners.rules.MobGriefingListener;
import fr.fallen.regions.listeners.rules.MobSpawnListener;
import fr.fallen.regions.listeners.rules.PortalsListener;
import fr.fallen.regions.listeners.rules.PveListener;
import fr.fallen.regions.listeners.rules.PvpListener;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class RegionsPlugin extends JavaPlugin {
	
	private static RegionsPlugin plugin;
	
	public static RegionsPlugin getPlugin(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		
		new RegionsLoader().loadAll(getDataFolder());
		
		//API Listeners
		new RegionChangeListener();
		
		// Rules Listeners
		new AnimalSpawnListener();
		new BuildListener();
		new DamagesListener();
		new DropListener();
		new EnderPearlsListener();
		new EnterListener();
		new ExplosionsListener();
		new FireListener();
		new InteractListener();
		new InteractEntitiesListener();
		new LightningsListener();
		new LiquidFlowsListener();
		new MobGriefingListener();
		new MobSpawnListener();
		new PortalsListener();
		new PveListener();
		new PvpListener();
	}
	
	@Override
	public void onDisable(){
		for(int i = 0 ; i < 3 ; i++){
			for(Region region : RegionsManager.getManager(Bukkit.getWorlds().get(i)).getRegions())
				region.getFile().saveRegion(true);
		}
	}
	
}
