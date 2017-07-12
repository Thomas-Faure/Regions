package fr.fallen.regions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 09/07/2017
 */

public class DefaultRegion extends Region {

	public DefaultRegion() {
		this(new HashSet<>());
	}
	
	public DefaultRegion(Set<RegionRules> disallow){
		super("default", "default", new FakeVirtualSquare(), new ArrayList<>(), new HashSet<>(), disallow, null);
	}
	
}
