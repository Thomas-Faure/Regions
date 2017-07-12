package fr.fallen.regions;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public class YmlConfiguration {
	
	protected final File file;
	protected final YamlConfiguration config;
	
	public YmlConfiguration(File file){
		this.file = file;
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save(){
		Bukkit.getScheduler().runTaskAsynchronously(RegionsPlugin.getPlugin(), () -> {
			saveNow();
		});
	}
	
	public boolean saveNow(){
		try {
			config.save(file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
