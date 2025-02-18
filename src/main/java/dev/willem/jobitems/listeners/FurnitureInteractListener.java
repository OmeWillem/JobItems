package dev.willem.jobitems.listeners;

import dev.lone.itemsadder.api.Events.FurnitureInteractEvent;
import dev.willem.jobitems.configuration.objects.ConfigItem;
import dev.willem.jobitems.utils.ChatUtils;
import dev.willem.jobitems.utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FurnitureInteractListener implements Listener {

    @EventHandler
    public void furnitureInteract(final FurnitureInteractEvent event) {
        if (event.getFurniture() == null) return;

        Player player = event.getPlayer();
        if (!player.isSneaking()) return;
        if (!ConfigUtils.isLoaded()) return;

        ConfigItem item = ConfigUtils.getByNamespacedId(event.getFurniture().getNamespacedID());
        if (item == null) return;

        if (!ConfigUtils.hasPermission(player, item.key())) {
            player.sendMessage(ChatUtils.parse("<red>Jij mag dit niet weghalen."));
            return;
        }

        event.getFurniture().remove(false);
        player.sendMessage(ChatUtils.parse("<green>Je hebt <dark_green>" + item.key() + " <green>weggehaald."));
    }

}
