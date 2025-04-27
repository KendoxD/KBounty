package com.kendo.me.kbounty.database

import com.kendo.me.kbounty.KBounty
import com.kendo.me.kbounty.database.model.HuntModel
import java.util.UUID

class HuntDAO(private val plugin : KBounty, ) {

    val connection = DataBaseConnection(plugin).getConnection();


    fun addHunt(huntModel: HuntModel) {
        val query = "INSERT INTO hunts (target_uuid, hunter_uuid, started_at) VALUES (?, ?, ?)"
        val ps = connection?.prepareStatement(query);
        ps?.setString(1, huntModel.target.toString());
        ps?.setString(2, huntModel.hunter.toString())
        ps?.setLong(3, huntModel.startedAt)
        ps?.executeUpdate();
    }

    fun removeHunt(huntModel: HuntModel) {
        val query = "DELETE FROM hunts WHERE target_uuid = ? AND hunter_uuid = ?"
        val ps = connection?.prepareStatement(query);
        ps?.setString(1, huntModel.target.toString());
        ps?.setString(2, huntModel.hunter.toString());
        ps?.executeUpdate();
    }



    fun getHuntersOfTarget(huntModel: HuntModel): List<UUID> {
        val query = "SELECT hunter_uuid FROM hunts WHERE target_uuid = ?"
        connection?.prepareStatement(query).use { ps ->
            ps?.setString(1, huntModel.target.toString())
            ps?.executeQuery().use { rs ->
                val hunters = mutableListOf<UUID>()
                while (rs?.next() == true) {
                    hunters.add(UUID.fromString(rs.getString("hunter_uuid")))
                }
                return hunters
            }
        }
    }
}