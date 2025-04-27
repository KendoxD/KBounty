package com.kendo.me.kbounty.database

import com.kendo.me.kbounty.KBounty
import com.kendo.me.kbounty.database.model.PlayerModel
import com.kendo.me.kbounty.utils.ChatUtils
import com.kendo.me.kbounty.utils.ConfigManager
import org.bukkit.Bukkit
import java.sql.ResultSet
import java.util.UUID

class PlayerDAO(private val plugin : KBounty, private val player : PlayerModel) {

    val connection = DataBaseConnection(plugin).getConnection();

    fun createPlayer(){
        if(!existsInDB(player.uuid)){
            val ps = connection?.prepareStatement("INSERT INTO bounty (uuid, bounty) VALUES (?, ?)")
            ps?.setString(1, player.uuid.toString())
            ps?.setInt(2, player.bounty)
            ps?.executeUpdate();
            println("[KBounty] Player criado com sucesso na db!");
        }else {
            Bukkit.getLogger().info(ChatUtils.format("[&eKBounty] &cEsse player já existe na db!!!!!"))
        }

    }


     fun existsInDB(uuid : UUID) : Boolean {

        val query = "SELECT * FROM bounty WHERE uuid = ?"
        val ps = connection?.prepareStatement(query);
        ps?.setString(1, uuid.toString());
        val resultSet : ResultSet? = ps?.executeQuery();
        resultSet.let {
            if(it != null) {
                if(it.next()){
                    return true;
                }
            }
        }
        return false;
    }
}