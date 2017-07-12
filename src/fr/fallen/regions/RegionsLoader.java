package fr.fallen.regions;

import java.io.File;

import org.bukkit.Bukkit;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class RegionsLoader {
	
	public void loadAll(File initialFolder){
		for(int i = 0 ; i < 3 ; i++){
			String worldName = Bukkit.getWorlds().get(i).getName();
			File worldFolder = new File(initialFolder, worldName);
			if(!worldFolder.isDirectory()){
				worldFolder.delete();
			}
			if(!worldFolder.exists()){
				worldFolder.mkdirs();
			}
			
			RegionsManager manager = RegionsManager.getManagers()[i];
			
			for(File file : worldFolder.listFiles()){
				try{
					manager.registerRegion(new RegionFile(file).createRegion());
				}catch(Exception ex){
					System.out.println("[ERROR] Error when trying to load region '" + file.getName() + "' of the world '" + worldName + "'");
					ex.printStackTrace();
				}
			}
		}
	}
	
}
