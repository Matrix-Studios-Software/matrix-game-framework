package ltd.matrixstudios.bedwars.constants

import ltd.matrixstudios.bedwars.Bedwars
import org.bukkit.material.Bed

object BedwarsConstants {

    val MAX_TEAMS: Int = Bedwars.instance.config.getInt("constants.max-teams")
    val PLAYERS_PER_TEAM: Int = Bedwars.instance.config.getInt("constants.players-per-team")
    val MAX_PLAYERS: Int = Bedwars.instance.config.getInt("constants.max-server-players")
}