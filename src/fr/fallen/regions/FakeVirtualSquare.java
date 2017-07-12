package fr.fallen.regions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class FakeVirtualSquare extends VirtualSquare {
	
	@Override
	public List<Chunk> getChunks(){
		return new ArrayList<>();
	}
	
}
