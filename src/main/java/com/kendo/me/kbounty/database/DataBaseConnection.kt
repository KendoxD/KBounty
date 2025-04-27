package com.kendo.me.kbounty.database

import com.kendo.me.kbounty.KBounty
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DataBaseConnection (private val plugin : KBounty){
    private val databaseFile: File = File(plugin.dataFolder, "database.db")
    private var activeConnection: Connection? = null
    private val table  = "CREATE TABLE IF NOT EXISTS bounty (uuid TEXT PRIMARY KEY, bounty INT);";

    init{
        setup();
        println("Database iniciada com sucesso!")
    }

    private fun setup() {
        Class.forName("org.sqlite.JDBC")
        plugin.dataFolder.mkdirs();
        databaseFile.createNewFile();
        val connection = getConnection();
        val statement = connection!!.createStatement()
        statement.execute(table);
        statement.execute("CREATE TABLE IF NOT EXISTS hunts (target_uuid TEXT NOT NULL, hunter_uuid TEXT NOT NULL, started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (target_uuid, hunter_uuid))")
    }

     fun getConnection(): Connection? {
        try {
            if (activeConnection != null && !activeConnection!!.isClosed) return this.activeConnection
            Class.forName("org.sqlite.SQLiteDataSource")
            this.activeConnection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseFile.getPath())
        } catch (e: SQLException) {
            throw RuntimeException(e)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }
        return this.activeConnection
    }
}