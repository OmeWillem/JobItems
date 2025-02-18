package dev.willem.jobitems.configuration.objects;

import dev.lone.itemsadder.api.CustomFurniture;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.block.Block;

public record ConfigItem(String key, String namespacedId, String permission) {

    public void spawn(Block block) {
        if (!CustomStack.isInRegistry(namespacedId)) return;
        CustomFurniture.spawn(namespacedId, block);
    }

}
