package com.kendo.me.kbounty.utils

import com.kendo.me.kbounty.KBounty

object ConfigManager {

    val plugin = KBounty.instance;

    fun getInitialBounty() : Int{
        return plugin.config.getInt("bounty.inicial");
    }


    fun getMenuName() : String? {
        return plugin.config.getString("bounty.menu-name");
    }
}