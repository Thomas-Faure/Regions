package fr.fallen.regions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class RegionFile extends YmlConfiguration {
	
	private Region region;
	
	public RegionFile(File file){
		super(file);
	}
	
	private RegionFile(String worldName, String regionName) {
		super(new File(new File(RegionsPlugin.getPlugin().getDataFolder(), worldName), regionName + ".yml"));
	}
	
	public RegionFile(Region region){
		this(region.getSquare().getWorld().getName(), region.getName());
		this.region = region;
	}
	
	public Region createRegion(){
		String name = config.getString("name");
		String type = config.getString("type");
		World world = Bukkit.getWorld(file.getParentFile().getName());
		
		VirtualSquare square = new VirtualSquare(world, config.getInt("square.x1"), config.getInt("square.z1"), config.getInt("square.x2"), config.getInt("square.z2"));
		List<String> chunksList = config.getStringList("chunks");
		List<Chunk> chunks = new ArrayList<>();
		for(String chunkToString : chunksList){
			String[] coords = chunkToString.split(",");
			chunks.add(world.getChunkAt(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
		}
		List<String> membersList = config.getStringList("members");
		Set<UUID> members = new HashSet<>();
		for(String member : membersList){
			members.add(UUID.fromString(member));
		}
		List<String> disallowList = config.getStringList("disallow");
		Set<RegionRules> disallow = new HashSet<>();
		for(String disallowToString : disallowList){
			disallow.add(RegionRules.valueOf(disallowToString));
		}
		
		return this.region = new Region(name, type, square, chunks, members, disallow, this);
	}
	
	public void removeRegion(){
		file.delete();
	}
	
	public void saveRegion(boolean saveNow){
		config.set("name", region.getName());
		config.set("type", region.getType());
		config.set("square.x1", region.getSquare().getMinX());
		config.set("square.z1", region.getSquare().getMinZ());
		config.set("square.x2", region.getSquare().getMaxX());
		config.set("square.z2", region.getSquare().getMaxZ());
		List<String> chunksList = new ArrayList<>();
		for(Chunk chunk : region.getChunks()){
			chunksList.add(chunk.getX() + "," + chunk.getZ());
		}
		config.set("chunks", chunksList);
		List<String> membersList = new ArrayList<>();
		for(UUID uuid : region.getMembers()){
			membersList.add(uuid.toString());
		}
		config.set("members", membersList);
		List<String> disallowList = new ArrayList<>();
		for(RegionRules rule : region.getDisallowRules()){
			disallowList.add(rule.name());
		}
		config.set("disallow", disallowList);
		
		if(saveNow)
			saveNow();
		else
			save();
	}
	
}
