package com.kendo.me.kbounty.events

import com.kendo.me.kbounty.KBounty
import com.kendo.me.kbounty.database.PlayerDAO
import com.kendo.me.kbounty.database.model.PlayerModel
import com.kendo.me.kbounty.utils.ConfigManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLogin : Listener{

    @EventHandler
    private fun onPlayerLogin(e : PlayerLoginEvent) {
        val playerModel = PlayerModel(e.player.uniqueId, ConfigManager.getInitialBounty());
        val playerDAO = PlayerDAO(KBounty.instance, playerModel);
        if(!playerDAO.existsInDB(playerModel.uuid)) {
            playerDAO.createPlayer();
            println("Player adicionado a db com sucesso!")
        }
    }
}