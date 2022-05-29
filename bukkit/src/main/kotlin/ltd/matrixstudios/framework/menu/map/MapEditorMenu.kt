package ltd.matrixstudios.framework.menu.map

import ltd.matrixstudios.framework.menu.buttons.GenericButton
import ltd.matrixstudios.framework.menu.library.Button
import ltd.matrixstudios.framework.menu.library.pagination.PaginatedMenu
import ltd.matrixstudios.framework.menu.map.setting.MapEditorSettingsMenu
import ltd.matrixstudios.framework.world.map.MapManager
import org.bukkit.Material
import org.bukkit.entity.Player

class MapEditorMenu(var opener: Player) : PaginatedMenu(18, opener) {

    override fun getPagesButtons(player: Player): MutableMap<Int, Button> {
        val buttons = hashMapOf<Int, Button>()
        var index = 0

        for (map in MapManager.findAll()) {
            buttons[index++] = GenericButton(Material.WOOL,
                5,
                "&a${map.displayName}",
                arrayListOf()).setAction {
                    player, i, clickType ->
                MapEditorSettingsMenu(player, map).updateMenu()
            }
        }

        return buttons
    }

    override fun getTitle(player: Player): String {
        return "Edit a Map "
    }
}