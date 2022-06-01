package ltd.matrixstudios.bedwars

import io.github.thatkawaiisam.assemble.Assemble
import io.github.thatkawaiisam.assemble.AssembleBoard
import io.github.thatkawaiisam.assemble.AssembleStyle
import ltd.matrixstudios.bedwars.scoreboard.BedwarsScoreboard
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

        BedwarsTeamDistributor.loadTeams()

        val assemble = Assemble(this, BedwarsScoreboard)
        assemble.ticks = 2
        assemble.assembleStyle = AssembleStyle.MODERN

    }

}