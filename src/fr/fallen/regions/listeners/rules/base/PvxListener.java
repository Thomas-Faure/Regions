package fr.fallen.regions.listeners.rules.base;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.projectiles.ProjectileSource;

import fr.fallen.regions.RegionRules;

/**
 * @author Fallen - Noah
 * @version 1.0.0
 * @since 11/07/2017
 */

public abstract class PvxListener extends RuleListener {
	
	protected PvxListener(RegionRules rule) {
		super(rule);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPotionSplash(PotionSplashEvent event){
		ThrownPotion potion = event.getPotion();
		if(!isAllowed(potion.getLocation())){
			ProjectileSource shooter = potion.getShooter();
			if(shooter instanceof Player && !bypass((Player) shooter, potion.getLocation())){
				for(LivingEntity entity : event.getAffectedEntities()){
					if(isConcerned(entity))
						event.setIntensity(entity, 0.0D);
				}
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event){
		Entity entity = event.getEntity();
		if(isAllowed(entity.getLocation()))
			return;
		if(!isConcerned(entity)) // Pas du PvE
			return;
		if(event instanceof EntityDamageByBlockEvent)
			checkAllowed((EntityDamageByBlockEvent) event);
		else if(event instanceof EntityDamageByEntityEvent)
			checkAllowed((EntityDamageByEntityEvent) event);
		else
			checkAllowed(event);
	}
	
	private void checkAllowed(EntityDamageByBlockEvent event){
		checkExplosion(event);
	}
	
	private void checkAllowed(EntityDamageByEntityEvent event){
		Entity damager = event.getDamager();
		if(damager instanceof Player){
			if(!bypass((Player) damager, event.getEntity().getLocation()))
				event.setCancelled(true);
		}else if(damager instanceof Projectile){
			ProjectileSource shooter = ((Projectile) damager).getShooter();
			if(shooter instanceof Player && !bypass((Player) shooter, event.getEntity().getLocation()));
				event.setCancelled(true);
		}else{
			checkExplosion(event);
		}
	}
	
	private void checkAllowed(EntityDamageEvent event){
		checkExplosion(event);
	}
	
	private void checkExplosion(EntityDamageEvent event){
		if(event.getCause() == DamageCause.BLOCK_EXPLOSION || event.getCause() == DamageCause.ENTITY_EXPLOSION){
			event.setCancelled(true);
		}
	}
	
	protected abstract boolean isConcerned(Entity entity);
	
}
