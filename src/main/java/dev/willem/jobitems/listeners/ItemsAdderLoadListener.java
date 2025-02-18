package dev.willem.jobitems.listeners;

import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import dev.willem.jobitems.JobItems;
import dev.willem.jobitems.configuration.DefaultConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ItemsAdderLoadListener implements Listener {

    @EventHandler
    public void itemsAdderLoad(ItemsAdderLoadDataEvent event) {
        JobItems.getInstance().setConfiguration(new DefaultConfiguration(JobItems.getInstance().getDataFolder()));
    }

}
