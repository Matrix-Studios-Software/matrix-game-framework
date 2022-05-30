package ltd.matrixstudios.framework.timer.types

import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.timer.Timer
import ltd.matrixstudios.framework.util.Chat
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

object VotingCountdown : Timer() {

    var seconds = 60

    override fun start() {
        val game = FrameworkBukkit.instance.currentGame

        game.status = GameServer.GameStatus.VOTING

        GameService.save(game.id, game)

        object : BukkitRunnable() {

            override fun run() {
                when (seconds) {
                    60 -> Bukkit.broadcastMessage(Chat.format("&eVoting closes in &c60s"))
                    45 -> Bukkit.broadcastMessage(Chat.format("&eVoting closes in &c45s"))
                    30 -> Bukkit.broadcastMessage(Chat.format("&eVoting closes in &c30s"))
                    15 -> Bukkit.broadcastMessage(Chat.format("&eVoting closes in &c15s"))

                    0 -> {
                        Bukkit.broadcastMessage(Chat.format("&eVoting closes in &cNow"))

                        GameStartCountdown.start()
                        cancel()
                    }
                }

                seconds--
            }

        }.runTaskTimer(FrameworkBukkit.instance, 0L, 19L)
    }
}