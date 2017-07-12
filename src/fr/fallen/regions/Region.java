package fr.fallen.regions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Chunk;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class Region {
	
	private final String name;
	private final String type;
	private final VirtualSquare square;
	private final List<Chunk> chunks;
	private final Set<UUID> members;
	private final Set<RegionRules> disallow;
	private final RegionFile file;
	
	public Region(String name, String type, VirtualSquare square){
		this(name, type, square, square.getChunks(), new HashSet<>(), new HashSet<>(), null);
	}
	
	public Region(String name, String type, VirtualSquare square, List<Chunk> chunks, Set<UUID> members, Set<RegionRules> disallow, RegionFile file){
		this.name = name;
		this.type = type;
		this.square = square;
		this.chunks = chunks;
		this.members = members;
		this.disallow = disallow;
		this.file = (this instanceof DefaultRegion ? null : file == null ? new RegionFile(this) : file);
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public VirtualSquare getSquare(){
		return square;
	}
	
	public List<Chunk> getChunks(){
		return chunks;
	}
	
	public Set<UUID> getMembers(){
		return members;
	}
	
	public boolean isMember(UUID uuid){
		return getMembers().contains(uuid);
	}
	
	public Set<RegionRules> getDisallowRules(){
		return disallow;
	}
	
	public boolean allow(RegionRules rule){
		return !disallow.contains(rule);
	}
	
	public void setAllowed(RegionRules rule, boolean allowed){
		if(allowed){
			disallow.remove(rule);
		}else{
			disallow.add(rule);
		}
	}
	
	public RegionFile getFile(){
		return file;
	}
	
}
