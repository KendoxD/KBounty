package com.kendo.me.kbounty.commands

import com.kendo.me.kbounty.KBounty
import com.kendo.me.kbounty.database.PlayerDAO
import com.kendo.me.kbounty.utils.BountyMenu
import com.kendo.me.kbounty.utils.ChatUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class WantedMenu(private val plugin : KBounty) :  CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String?>
    ): Boolean {
        if(sender is Player) {
            val player = sender;
            val allPlayersWithBounty = PlayerDAO(plugin).getAllPlayersWithBounty();
            val menu = BountyMenu(plugin)
            menu.open(player, allPlayersWithBounty);
            return true;
        }

        sender.sendMessage(ChatUtils.format("&cApenas jogadores podem utilizar esse comando!"))
        return false;
    }

}