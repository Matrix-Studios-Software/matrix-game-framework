package ltd.matrixstudios.bedwars.countdowns

import ltd.matrixstudios.bedwars.Bedwars
import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.util.Chat
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

object VoteCountdown {

    var seconds = 60

    fun start() {
        val game = FrameworkBukkit.instance.currentGame

        game.status = GameServer.GameStatus.VOTING

        GameService.save(game.id, game)
        object : BukkitRunnable() {

            override fun run() {
                when (seconds) {
                    60 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eVoting&8] &fVoting ends in &c1 minute"))
                    }
                    30 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eVoting&8] &fVoting ends in &c30 seconds"))
                    }
                    10 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eVoting&8] &fVoting ends in &c10 seconds"))
                    }
                    0 -> {
                        Bukkit.broadcastMessage(Chat.format("&8[&eVoting&8] &fVoting ends &cNow"))

                        cancel()
                    }
                }
                seconds--
            }

        }.runTaskTimer(Bedwars.instance, 0L, 19L)
    }
}