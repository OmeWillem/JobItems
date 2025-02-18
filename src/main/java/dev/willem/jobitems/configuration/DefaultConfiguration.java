package dev.willem.jobitems.configuration;

import dev.willem.jobitems.JobItems;
import dev.willem.jobitems.configuration.objects.ConfigItem;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Getter
public class DefaultConfiguration extends ConfigurateConfig {

    private final Map<String, ConfigItem> items;

    public DefaultConfiguration(File file) {
        super(file, "config.yml", "default-config.yml");

        this.items = new HashMap<>();
        rootNode.node("items").childrenMap().forEach((key, value) -> {
            String namespacedId = value.node("itemsadder_id").getString();
            if (namespacedId == null) return;

            String permission = value.node("permission").getString();
            if (permission == null) return;

            items.put((String) key, new ConfigItem((String) key, namespacedId, permission));
        });

        JobItems.getInstance().getLogger().info("Loaded " + items.size() + " items");
    }

}
