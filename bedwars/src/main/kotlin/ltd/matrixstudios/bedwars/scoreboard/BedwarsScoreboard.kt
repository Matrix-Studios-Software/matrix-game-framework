package ltd.matrixstudios.bedwars.scoreboard

import io.github.thatkawaiisam.assemble.AssembleAdapter
import ltd.matrixstudios.bedwars.constants.BedwarsConstants
import ltd.matrixstudios.bedwars.countdowns.VoteCountdown
import ltd.matrixstudios.bedwars.util.TimeUtil
import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.util.Chat
import ltd.matrixstudios.framework.voting.VoteFactory
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object BedwarsScoreboard : AssembleAdapter {

    override fun getTitle(p0: Player?): String {
        return Chat.format("&a&lMatrix &7[Bedwars]")
    }

    override fun getLines(p0: Player?): MutableList<String> {
        val lines = arrayListOf<String>()

        lines.add(Chat.format("&r&7&m----------------------"))

        when (FrameworkBukkit.instance.currentGame.status) {
            GameServer.GameStatus.VOTING -> {
                lines.add(Chat.format("&aVoting:"))
                for (map in VoteFactory.votableMaps) {
                    lines.add(Chat.format("&f${map.displayName} &7[${VoteFactory.getVotesForMap(map)}]"))
                }
                lines.add(" ")
                lines.add(Chat.format("&aEnds In: &f${TimeUtil.formatIntoDetailedString(VoteCountdown.seconds)}s"))
            }

            GameServer.GameStatus.WAITING -> {
                lines.add(Chat.format("&aPlayers: &f${Bukkit.getOnlinePlayers().size}/${BedwarsConstants.MAX_PLAYERS}"))
            }
        }

        lines.add(" ")
        lines.add(Chat.format("&7Matrix Studios"))
        lines.add(Chat.format("&7&m----------------------"))

        return lines
    }
}