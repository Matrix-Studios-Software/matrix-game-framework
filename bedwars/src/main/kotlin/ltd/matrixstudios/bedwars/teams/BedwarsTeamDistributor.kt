package ltd.matrixstudios.bedwars.teams

import ltd.matrixstudios.bedwars.constants.BedwarsConstants
import ltd.matrixstudios.framework.util.Chat
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object BedwarsTeamDistributor {

    var teams = hashMapOf<ColorTeam, BedwarsTeam>()

    fun loadTeams() {
        for (int in 0 until BedwarsConstants.MAX_TEAMS.minus(1)) {
            val colorTeam = ColorTeam.values()[int]

            teams[colorTeam] = BedwarsTeam(false, null, null, null, colorTeam)
        }
    }

    fun teleportAllTeamMembers() {
        for (team in teams.values)
        {
            for (player in team.members)
            {
                val player = Bukkit.getPlayer(player)

                if (player != null) {
                    player.sendMessage(Chat.format("&6You are being teleported..."))
                    player.teleport(team.spawnLocation)

                    return
                }
            }
        }
    }

    fun assignTeams() {
        for (player in Bukkit.getOnlinePlayers()) {
            assignPlayerTeam(player)
        }
    }

    fun assignPlayerTeam(player: Player) {
        val randomTeam = teams.values.firstOrNull { !it.full }

        if (randomTeam == null) {
            player.sendMessage(Chat.format("&cNo open team could be found!"))
            return
        }

        randomTeam.members.add(player.uniqueId)
    }
}