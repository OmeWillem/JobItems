package dev.willem.jobitems.utils;

import dev.willem.jobitems.JobItems;
import dev.willem.jobitems.configuration.objects.ConfigItem;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

@UtilityClass
public class ConfigUtils {

    public boolean isLoaded() {
        return JobItems.getInstance().getConfiguration() != null;
    }

    public List<String> getNames(Player player) {
        return JobItems.getInstance().getConfiguration().getItems().entrySet().stream()
                .filter(entry -> player.hasPermission(entry.getValue().permission()))
                .map(Map.Entry::getKey).toList();
    }

    public ConfigItem get(String key) {
        return JobItems.getInstance().getConfiguration().getItems().get(key);
    }

    public boolean exists(String key) {
        return JobItems.getInstance().getConfiguration().getItems().containsKey(key);
    }

    public ConfigItem getByNamespacedId(String namespacedId) {
        return JobItems.getInstance().getConfiguration().getItems().values().stream()
                .filter(configItem -> configItem.namespacedId().equals(namespacedId)).findAny().orElse(null);
    }

    public boolean hasPermission(Player player, String key) {
        return player.hasPermission(get(key).permission());
    }

}
