package fr.fallen.regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.World.Environment;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class RegionsManager {
	
	/**
	 * Static methods
	 */
	
	private static final RegionsManager[] managers = new RegionsManager[3];
	
	static{
		for(int i = 0 ; i < 3 ; i++){
			managers[i] = new RegionsManager();
		}
	}
	
	public static RegionsManager[] getManagers(){
		return managers;
	}
	
	public static RegionsManager getManager(World world){
		return getManager(world.getEnvironment());
	}
	
	private static RegionsManager getManager(Environment environment){
		return managers[environment == Environment.NORMAL ? 0 : environment == Environment.NETHER ? 1 : 2];
	}
	
	/**
	 * Class methods
	 */
	
	private final Map<String, Region> regions = new HashMap<>();
	private final List<Region> regionsList = new ArrayList<>();
	private Region defaultRegion = new DefaultRegion();
	
	public List<Region> getRegions(){
		return regionsList;
	}
	
	public Set<Region> getRegions(VirtualSquare square){
		Set<Region> regionsSet = new HashSet<>();
		for(Region region : regionsList){
			if(region.getSquare().collideWith(square))
				regionsSet.add(region);
		}
		return regionsSet;
	}
	
	public Region getRegion(Chunk chunk){
		return regions.getOrDefault(parseChunk(chunk), defaultRegion);
	}
	
	public Region getRegion(String regionName){
		for(Region region : getRegions()){
			if(region.getName().equalsIgnoreCase(regionName))
				return region;
		}
		return null;
	}
	
	public void registerRegion(Region region){
		regionsList.add(region);
		for(Chunk chunk : region.getChunks())
			regions.put(parseChunk(chunk), region);
	}
	
	public void unregisterRegion(Region region){
		regionsList.remove(region);
		for(Entry<String, Region> entry : new HashSet<>(regions.entrySet())){
			if(entry.getValue() == region)
				regions.remove(entry.getKey());
		}
		region.getFile().removeRegion();
	}
	
	public void removeRegion(String regionName){
		Region region = getRegion(regionName);
		if(region != null)
			unregisterRegion(region);
	}
	
	private String parseChunk(Chunk chunk){
		return parseCoords(chunk.getX(), chunk.getZ());
	}
	
	private String parseCoords(int x, int z){
		return x + "," + z;
	}
	
	public void setDefaultRegion(Region region){
		this.defaultRegion = region;
	}
	
}
