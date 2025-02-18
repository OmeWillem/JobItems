package dev.willem.jobitems.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import dev.willem.jobitems.JobItems;
import dev.willem.jobitems.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;

/* Not proud of this, but it works */
@CommandAlias("jobutils")
public class JobUtilsCommand extends BaseCommand {

    @Subcommand("trapin|kickin")
    @CommandPermission("jobutils.kickin")
    public void kickIn(Player player) {
        Block block = player.getTargetBlockExact(6);
        if (block == null) {
            player.sendMessage(ChatUtils.parse("<red>Kijk naar een deur."));
            return;
        }

        if (!(block.getBlockData() instanceof Openable openable)) {
            player.sendMessage(ChatUtils.parse("<red>Dit is geen deur."));
            return;
        }

        player.sendMessage(ChatUtils.parse("<green>De deur is opengetrapt!"));
        openable.setOpen(true);
        block.setBlockData(openable);

        Bukkit.getScheduler().runTaskLater(JobItems.getInstance(), () -> {
            openable.setOpen(false);
            block.setBlockData(openable);
        }, 5*20);
    }

    @Subcommand("openkist|openchest")
    @CommandPermission("jobutils.openchest")
    public void openChest(Player player) {
        Block block = player.getTargetBlockExact(6);
        if (block == null) {
            player.sendMessage(ChatUtils.parse("<red>Kijk naar een kist."));
            return;
        }

        if (!(block.getState() instanceof Chest chest)) {
            player.sendMessage(ChatUtils.parse("<red>Dit is geen chest."));
            return;
        }

        player.openInventory(chest.getInventory());
        player.sendMessage(ChatUtils.parse("<green>De chest is open!"));
    }

}
