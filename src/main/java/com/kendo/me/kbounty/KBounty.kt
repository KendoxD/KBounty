package com.kendo.me.kbounty;

import com.kendo.me.kbounty.database.DataBaseConnection
import com.kendo.me.kbounty.events.PlayerLogin
import com.kendo.me.kbounty.utils.ChatUtils
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin;

class KBounty : JavaPlugin() {



    companion object {
        lateinit var instance: KBounty;
    }

    override fun onEnable() {
        instance = this;
        Bukkit.getLogger().info(ChatUtils.format("&aPlugin inicializado com sucesso!"));
        registerEvents();
        registerCommands();
        saveDefaultConfig();
        DataBaseConnection(this);
    }

    override fun onDisable() {
        Bukkit.getLogger().info(ChatUtils.format("&cPlugin desligado com sucesso!"));
    }


    private fun registerCommands() {

        Bukkit.getLogger().info(ChatUtils.format("&aComandos registrados com sucesso!"));
    }

    private fun registerEvents() {

        server.pluginManager.registerEvents(PlayerLogin(), this);
        Bukkit.getLogger().info(ChatUtils.format("&cEventos registrados com sucesso!!"));
    }
}
