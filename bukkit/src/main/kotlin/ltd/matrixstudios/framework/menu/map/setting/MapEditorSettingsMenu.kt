package ltd.matrixstudios.framework.menu.map.setting

import ltd.matrixstudios.framework.menu.buttons.GenericButton
import ltd.matrixstudios.framework.menu.library.Button
import ltd.matrixstudios.framework.menu.library.Menu
import ltd.matrixstudios.framework.menu.library.pagination.PaginatedMenu
import ltd.matrixstudios.framework.util.Chat
import ltd.matrixstudios.framework.world.map.Map
import ltd.matrixstudios.framework.world.map.MapManager
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerEvent

class MapEditorSettingsMenu(opener: Player, var map: Map) : PaginatedMenu(9, opener) {

    override fun getPagesButtons(player: Player): MutableMap<Int, Button> {
        val buttons = hashMapOf<Int, Button>()

        buttons[0] = GenericButton(Material.EMERALD,
            0,
            "&aSet Spawn Location",
            arrayListOf()
        ).setAction { player, i, clickType ->
            println("azsd")
            map.firstJoinLocation = player.location
            player.sendMessage(
                Chat.format("&aUpdated the spawn location of ${map.displayName}"))

            MapManager.save(map.id, map)
        }

        return buttons
    }

    override fun getTitle(player: Player): String {
        return "Editing a map"
    }
}