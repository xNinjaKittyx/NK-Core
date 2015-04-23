package io.github.xNinjaKittyx.NKCore.Events;

import io.github.xNinjaKittyx.NKCore.Commands.Cheats;
import io.github.xNinjaKittyx.NKCore.NKCore;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodEvent implements Listener {

	public GodEvent(NKCore plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerDeath(EntityDamageEvent e) {
		Entity ent = e.getEntity();

		if (ent instanceof Player) {
			if (Cheats.god.godToggleList.contains(((Player) ent)
					.getName()))
				e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}

}
