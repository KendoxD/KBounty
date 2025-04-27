package com.kendo.me.kbounty.utils

import net.md_5.bungee.api.ChatColor

object ChatUtils{
    fun format(msg : String): String{
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}