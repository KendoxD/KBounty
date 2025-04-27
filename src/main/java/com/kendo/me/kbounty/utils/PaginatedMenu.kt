package com.kendo.me.kbounty.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

abstract class PaginatedMenu<T>(
    val title: String,
    val itemsPerPage: Int = 45
) {
    protected var page = 0
    val maxPage = (allItems.size - 1) / itemsPerPage
    protected lateinit var viewer: Player
    protected lateinit var allItems: List<T>

    fun open(player: Player, items: List<T>, startPage: Int = 0) {
        this.viewer = player
        this.allItems = items
        this.page = startPage

        player.openInventory(buildInventory())
    }

    open fun buildInventory(): Inventory {

        val inv = Bukkit.createInventory(null, 54, "$title (Página ${page + 1}/${maxPage + 1})")

        val start = page * itemsPerPage
        val end = minOf(start + itemsPerPage, allItems.size)
        val pageItems = allItems.subList(start, end)

        for ((index, item) in pageItems.withIndex()) {
            inv.setItem(index, renderItem(item))
        }

        if (page > 0) inv.setItem(45, navItem(Material.ARROW, ChatUtils.format("&cPágina anterior")))
        if (page < maxPage) inv.setItem(53, navItem(Material.ARROW, ChatUtils.format("&aPróxima página")))

        return inv
    }

    fun nextPage(): Inventory {
        page++
        return buildInventory()
    }

    fun prevPage(): Inventory {
        page--
        return buildInventory()
    }

    abstract fun renderItem(item: T): ItemStack

    fun navItem(material: Material, name: String): ItemStack {
        val item = ItemStack(material)
        item.itemMeta = item.itemMeta?.apply { setDisplayName(name) }
        return item
    }
}