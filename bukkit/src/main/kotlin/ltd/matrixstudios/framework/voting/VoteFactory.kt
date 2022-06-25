package ltd.matrixstudios.framework.voting

import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.game.Game
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.util.Chat
import ltd.matrixstudios.framework.world.map.Map
import ltd.matrixstudios.framework.world.map.MapManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object VoteFactory {
    var votes = hashMapOf<UUID, Map>()

    var votableMaps = arrayListOf<Map>()

    lateinit var finalMap: Map

    fun hasVoted(uuid: UUID) : Boolean {
        return votes.containsKey(uuid)
    }

    fun getVotesForMap(map: Map) : Int {
        val mapvotes = votes.values.filter { it.id == map.id }

        return mapvotes.size
    }

    fun stopVoting() : Map {
        val intMap = hashMapOf<Map, Int>()
        for (map in votableMaps) {
            val amountOfVotes = votes.values.filter { it == map }.size

            intMap[map] = amountOfVotes
        }

        val map = intMap.entries.minByOrNull { it.value }!!.key

        this.finalMap = map

        return map
    }

    fun startVoting() {
        val maps = arrayListOf<Map>()

        val databaseMaps = MapManager.findAll().shuffled()

        for (map in databaseMaps.stream().limit(3)) {
          maps.add(map)
        }

        this.votableMaps = maps

        Bukkit.broadcastMessage(Chat.format("&8[&eServer&8] &eVoting has just begun!"))
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

        if (!votableMaps.contains(map)) {
            player.sendMessage(Chat.format("&cYou cannot vote for this map!"))
            return
        }

        votes[player.uniqueId] = map

        player.sendMessage(Chat.format("&aYou have just voted for &f${map.id}"))
    }
}