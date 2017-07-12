package fr.fallen.regions;

import org.bukkit.Chunk;
import org.bukkit.Location;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class VirtualChunkSquare extends VirtualSquare {
	
	public VirtualChunkSquare(Location location, int diameter){
		location.setY(64);
		
		int defaultRadius = diameter / 2 + (diameter % 2 - 1);
		
		Location centerLoc = location.clone().add(- location.getBlockX() % 16, 0, - location.getBlockZ() % 16);
		
		Location minLoc = centerLoc.clone().add(- defaultRadius * 16, 0, - defaultRadius * 16); // .add(0,0,0);
		Location maxLoc = centerLoc.clone().add(+ defaultRadius * 16, 0, + defaultRadius * 16).add(15, 0, 15);
		
		if(diameter % 2 == 0){
			int bestExpansion = findBestExpansion(location);
			
			minLoc.add(- (bestExpansion < 2 ? 16 : 0), 0, - (bestExpansion % 2 == 0 ? 16 : 0));
			maxLoc.add(+ (bestExpansion > 1 ? 16 : 0), 0, + (bestExpansion % 2 == 1 ? 16 : 0));
		}
		
		super.setCoords(minLoc, maxLoc);
	}
	
	/**
	 * Retourne la position vers laquelle on s'étendra le plus
	 * Le résultat est retourné sous la forme d'un int de 0 à 3 avec : 
	 * 0 = Extension vers X- et Z-
	 * 1 = Extension vers X- et Z+
	 * 2 = Extension vers X+ et Z-
	 * 3 = Extension vers X+ et Z+
	 */
	private int findBestExpansion(Location location){ // 
		Chunk chunk = location.getChunk();
		Location center = chunk.getBlock(7, 7, 7).getLocation();
		int diffX = location.getBlockX() - center.getBlockX();
		int diffZ = location.getBlockZ() - center.getBlockZ();
		return (diffX >= 0 ? 2 : 0) + (diffZ >= 0 ? 1 : 0);
	}
	
}
