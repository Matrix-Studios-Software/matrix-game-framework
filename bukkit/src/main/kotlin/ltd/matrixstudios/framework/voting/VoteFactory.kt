package ltd.matrixstudios.framework.voting

import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.game.Game
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.util.Chat
import ltd.matrixstudios.framework.world.map.Map
import org.bukkit.entity.Player
import java.util.*

object VoteFactory {
    var votes = hashMapOf<UUID, Map>()

    fun hasVoted(uuid: UUID) : Boolean {
        return votes.containsKey(uuid)
    }

    fun vote(player: Player, map: Map) {
        if (hasVoted(player.uniqueId)) {
            player.sendMessage(Chat.format("&cYou have already voted"))
            return
        }

        if (FrameworkBukkit.instance.currentGame.status != GameServer.GameStatus.VOTING) {
            player.sendMessage(Chat.format("&cThis game must be in a voting stage to vote"))
            return
        }

        votes[player.uniqueId] = map

        player.sendMessage(Chat.format("&aYou have just voted for &f${map.id}"))
    }
}