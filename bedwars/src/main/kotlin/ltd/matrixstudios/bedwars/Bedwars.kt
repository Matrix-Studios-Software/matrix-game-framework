package ltd.matrixstudios.bedwars

import ltd.matrixstudios.bedwars.teams.BedwarsTeamDistributor
import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.instance.GameServer
import org.bukkit.plugin.java.JavaPlugin

class Bedwars : JavaPlugin() {

    companion object {
        lateinit var instance: Bedwars
    }

    override fun onEnable() {
        saveDefaultConfig()
        instance = this
    }

    fun launchGame() {
        val game = FrameworkBukkit.instance.currentGame

        game.startedAt = System.currentTimeMillis()
        game.status = GameServer.GameStatus.STARTED

        BedwarsTeamDistributor.assignTeams()
    }
}