package dev.willem.jobitems.configuration.objects;

import dev.lone.itemsadder.api.CustomFurniture;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public record ConfigItem(String key, String namespacedId, String permission) {

    public void spawn(Block block, Player player) {
        if (!CustomStack.isInRegistry(namespacedId)) return;

        // Spawn the furniture directly at the block
        CustomFurniture furniture = CustomFurniture.spawn(namespacedId, block);

        if (furniture != null) {
            try {
                // Get the player's yaw and block location
                float yaw = player.getLocation().getYaw();
                double x = block.getX() + 0.5;
                double y = block.getY();
                double z = block.getZ() + 0.5;

                // Set the entity's rotation and position
                furniture.getEntity().teleport(new org.bukkit.Location(block.getWorld(), x, y, z, yaw, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
