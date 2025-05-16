package dev.willem.jobitems.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.willem.jobitems.JobItems;
import dev.willem.jobitems.configuration.DefaultConfiguration;
import dev.willem.jobitems.configuration.objects.ConfigItem;
import dev.willem.jobitems.utils.ChatUtils;
import dev.willem.jobitems.utils.ConfigUtils;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@CommandAlias("jobitem|jobitems")
public class JobItemsCommand extends BaseCommand {

    @Subcommand("spawn")
    @Syntax("<key>")
    @CommandCompletion("@jobitems")
    public void spawn(Player player, String key) {
        if (!ConfigUtils.isLoaded()) {
            player.sendMessage(ChatUtils.parse("<red>De configuratie is nog aan het laden..."));
            return;
        }

        if (!ConfigUtils.exists(key)) {
            player.sendMessage(ChatUtils.parse("<red>Dit item bestaat niet."));
            return;
        }

        if (!ConfigUtils.hasPermission(player, key)) {
            player.sendMessage(ChatUtils.parse("<red>Je hebt geen toegang tot dit item!"));
            return;
        }

        ConfigItem item = ConfigUtils.get(key);
        Block block = player.getLocation().getBlock();

        item.spawn(block, player);
        player.sendMessage(ChatUtils.parse("<dark_green>" + item.key() + " <green>is geplaatst."));
    }

    @Subcommand("reload")
    @CommandPermission("jobitems.reload")
    public void reload(Player player) {
        JobItems.getInstance().setConfiguration(new DefaultConfiguration(JobItems.getInstance().getDataFolder()));
        player.sendMessage(ChatUtils.parse("<green>Configuratie is herladen."));
    }

}
