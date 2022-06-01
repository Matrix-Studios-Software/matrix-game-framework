package ltd.matrixstudios.bedwars.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Subcommand
import ltd.matrixstudios.bedwars.countdowns.VoteCountdown
import org.bukkit.entity.Player

@CommandAlias("game")
object GameCommands : BaseCommand(){

    @Subcommand("start")
    @CommandPermission("bedwars.game.forcestart")
    fun forcestart(player: Player) {
        VoteCountdown.start()
    }
}