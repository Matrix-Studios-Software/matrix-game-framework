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
        player.gameMode = GameMode.CREATIVE
        player.sendMessage(Chat.format("&7&oYou have become a spectator"))

        Bukkit.getOnlinePlayers().filter {
            !spectators.contains(it.uniqueId)
        }.forEach {
            it.hidePlayer(player)
        }
    }
}