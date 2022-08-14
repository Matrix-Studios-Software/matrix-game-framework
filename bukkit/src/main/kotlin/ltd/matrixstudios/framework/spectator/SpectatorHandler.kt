package ltd.matrixstudios.framework.spectator

import ltd.matrixstudios.framework.util.Chat
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.*

object SpectatorHandler {

    var spectators = arrayListOf<UUID>()

    fun addSpectator(player: Player) {
        spectators.add(player.uniqueId)
    }

    fun handleSpectatorToggle(player: Player) {
        addSpectator(player)
        player.gameMode = GameMode.SURVIVAL
        player.sendMessage(Chat.format("&7&oYou have become a spectator"))

        Bukkit.getOnlinePlayers().filter {
            !spectators.contains(it.uniqueId)
        }.forEach {
            it.hidePlayer(player)
        }

        Bukkit.getOnlinePlayers().filter {
            spectators.contains(it.uniqueId)
        }.forEach {
            player.showPlayer(it)
        }

        player.allowFlight = true
        player.isFlying = true
        player.health = 20.0
        player.foodLevel = 10
        player.exp = 0f
    }
}