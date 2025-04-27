package com.kendo.me.kbounty.utils

import com.kendo.me.kbounty.KBounty
import com.kendo.me.kbounty.database.model.PlayerModel
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class BountyMenu(private val plugin: KBounty) : PaginatedMenu<PlayerModel>("§eProcurados") {

    override fun renderItem(player: PlayerModel): ItemStack {
        val skull = ItemStack(Material.PLAYER_HEAD)
        val meta = skull.itemMeta as SkullMeta
        val offlinePlayer = Bukkit.getOfflinePlayer(player.uuid)
        meta.owningPlayer = offlinePlayer
        meta.setDisplayName(ChatUtils.format("&c${offlinePlayer.name}"))

        meta.lore = listOf(ChatUtils.format("&aBounty: &6$${player.bounty}"))
        skull.itemMeta = meta

        return skull
    }

}