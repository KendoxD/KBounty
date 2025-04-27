package com.kendo.me.kbounty.events

import com.kendo.me.kbounty.KBounty
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryClick(private val plugin : KBounty) : Listener {

    @EventHandler
    fun wantedMenuClick(e: InventoryClickEvent){
        // terminar
    }
}