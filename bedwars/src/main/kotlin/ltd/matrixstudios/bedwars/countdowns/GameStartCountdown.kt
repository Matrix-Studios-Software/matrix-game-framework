package ltd.matrixstudios.bedwars.countdowns

import ltd.matrixstudios.bedwars.Bedwars
import ltd.matrixstudios.bedwars.teams.BedwarsTeamDistributor
import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.util.Chat
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

object GameStartCountdown {

    var seconds = 60

    fun start() {
        val game = FrameworkBukkit.instance.currentGame

        game.status = GameServer.GameStatus.STARTED

        GameService.save(game.id, game)
        object : BukkitRunnable() {

            override fun run() {
                when (seconds) {
                    60 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eGame&8] &fGame starts in &a1 minute"))
                    }
                    30 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eGame&8] &fGame starts in &a30 seconds"))
                    }
                    10 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eGame&8] &fGame starts in &a10 seconds"))
                    }
                    0 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eGame&8] &fGame starts &aNow"))

                        BedwarsTeamDistributor.assignTeams()

                        BedwarsTeamDistributor.teleportAllTeamMembers()
                        
                        cancel()
                    }

                }
                seconds--
            }

        }.runTaskTimer(Bedwars.instance, 0L, 19L)
    }
}