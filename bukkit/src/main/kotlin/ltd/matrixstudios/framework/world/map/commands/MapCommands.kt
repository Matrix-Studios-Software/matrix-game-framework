package ltd.matrixstudios.framework.world.map.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Name
import co.aikar.commands.annotation.Subcommand
import ltd.matrixstudios.framework.menu.map.MapEditorMenu
import ltd.matrixstudios.framework.util.Chat
import ltd.matrixstudios.framework.world.map.Map
import ltd.matrixstudios.framework.world.map.MapManager
import org.bukkit.entity.Player

@CommandAlias("map")
object MapCommands : BaseCommand() {

    @Subcommand("create")
    @CommandPermission("framework.map.admin")
    fun create(sender: Player, @Name("name") name: String) {
        if (MapManager.exists(name)) {
            sender.sendMessage(Chat.format("&cMap already exists"))
            return
        }

        val map = Map(name.toLowerCase(), name, null, arrayListOf(), "/home/kira/Games/SG/na-sg-1/Classic.zip")

        MapManager.save(map.id, map)
        sender.sendMessage(Chat.format("&aCreated a map with the name &f$name"))
    }

    @Subcommand("editor")
    @CommandPermission("framework.map.admin")
    fun editor(sender: Player) {
        MapEditorMenu(sender).updateMenu()
    }

    @Subcommand("load")
    @CommandPermission("framework.map.admin")
    fun load(sender: Player, @Name("name") name: String) {
        if (!MapManager.exists(name)) {
            sender.sendMessage(Chat.format("&cMap doesnt exists"))
            return
        }

        val map = MapManager.findById(name)

        MapManager.loadMap(map!!)
        sender.sendMessage(Chat.format("&aLoaded a map with the name &f$name"))
    }

}