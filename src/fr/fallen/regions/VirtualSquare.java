package fr.fallen.regions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class VirtualSquare {
	
	private World world;
	private int minX;
	private int minZ;
	private int maxX;
	private int maxZ;
	
	public VirtualSquare(){}
	
	public VirtualSquare(World world, int x1, int z1, int x2, int z2){
		setWorld(world);
		setCoords(x1, z1, x2, z2);
	}
	
	public VirtualSquare(int x1, int z1, int x2, int z2){
		setCoords(x1, z1, x2, z2);
	}
	
	public VirtualSquare(Location loc1, Location loc2){
		setCoords(loc1, loc2);
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public void setCoords(int x1, int z1, int x2, int z2){
		setXCoords(x1, x2);
		setZCoords(z1, z2);
	}
	
	public void setCoords(Location loc1, Location loc2){
		setWorld(loc1.getWorld());
		setCoords(loc1.getBlockX(), loc1.getBlockZ(), loc2.getBlockX(), loc2.getBlockZ());
	}
	
	public void setXCoords(int x1, int x2){
		this.minX = Math.min(x1, x2);
		this.maxX = Math.max(x1, x2);
	}
	
	public void setZCoords(int z1, int z2){
		this.minZ = Math.min(z1, z2);
		this.maxZ = Math.max(z1, z2);
	}
	
	public World getWorld(){
		return world;
	}
	
	public int getMinX(){
		return minX;
	}
	
	public int getMaxX(){
		return maxX;
	}
	
	public int getMinZ(){
		return minZ;
	}
	
	public int getMaxZ(){
		return maxZ;
	}
	
	public List<Chunk> getChunks(){
		List<Chunk> chunks = new ArrayList<>();
		for(int x = getMinX() ; x <= getMaxX() ; x += 16){
			for(int z = getMinZ() ; z <= getMaxZ() ; z += 16){
				chunks.add(new Location(world, x, 64, z).getChunk());
			}
		}
		return chunks;
	}
	
	public boolean collideWith(VirtualSquare square){
		if(getMinX() <= square.getMinX() && square.getMinX() <= getMaxX())
			if(getMinZ() <= square.getMinZ() && square.getMinZ() <= getMaxZ())
				return true;
		if(getMinX() <= square.getMaxX() && square.getMaxX() <= getMaxX())
			if(getMinZ() <= square.getMaxZ() && square.getMaxZ() <= getMaxZ())
				return true;
		if(square.getMinX() <= getMinX() && getMinX() <= square.getMaxX())
			if(square.getMinZ() <= getMinZ() && getMinZ() <= square.getMaxZ())
				return true;
		if(square.getMinX() <= getMaxX() && getMaxX() <= square.getMaxX())
			if(square.getMinZ() <= getMaxZ() && getMaxZ() <= square.getMaxZ())
				return true;
		return false;
	}
	
	public VirtualSquare expandOfChunks(int chunksRadius){
		minX -= chunksRadius;
		minZ -= chunksRadius;
		maxX += chunksRadius;
		maxZ += chunksRadius;
		return this;
	}
	
	public VirtualSquare clone(){
		VirtualSquare clone = new VirtualSquare(getMinX(), getMinZ(), getMaxX(), getMaxZ());
		clone.setWorld(getWorld());
		return clone;
	}
	
}
