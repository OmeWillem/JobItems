package dev.willem.jobitems;

import co.aikar.commands.PaperCommandManager;
import dev.willem.jobitems.commands.JobItemsCommand;
import dev.willem.jobitems.commands.JobUtilsCommand;
import dev.willem.jobitems.configuration.DefaultConfiguration;
import dev.willem.jobitems.listeners.FurnitureInteractListener;
import dev.willem.jobitems.listeners.ItemsAdderLoadListener;
import dev.willem.jobitems.utils.ConfigUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class JobItems extends JavaPlugin {

    @Getter
    private static JobItems instance;
    private DefaultConfiguration configuration;

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new JobItemsCommand());
        commandManager.registerCommand(new JobUtilsCommand());

        /* Tab Completion */
        commandManager.getCommandCompletions().registerCompletion("jobitems",
                c -> ConfigUtils.getNames(c.getPlayer()));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ItemsAdderLoadListener(), this);
        Bukkit.getPluginManager().registerEvents(new FurnitureInteractListener(), this);
    }

}