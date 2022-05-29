package ltd.matrixstudios.framework.menu.buttons

import ltd.matrixstudios.framework.menu.library.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

class GenericButton(
    var material: Material,
    var data: Short,
    var displayName: String,
    var desc: MutableList<String>,
) : Button()  {

    lateinit var onClick: (Player, Int, ClickType) -> Unit?

    fun setAction(unit: (Player, Int, ClickType) -> Unit) : GenericButton {
        return this.apply {
            onClick = unit
        }
    }

    override fun getMaterial(player: Player): Material {
        return material
    }

    override fun getDescription(player: Player): MutableList<String>? {
        return desc.map { ChatColor.translateAlternateColorCodes('&', it) }.toCollection(mutableListOf())
    }

    override fun getDisplayName(player: Player): String? {
        return ChatColor.translateAlternateColorCodes('&', displayName)
    }

    override fun getData(player: Player): Short {
        return data
    }

    override fun onClick(player: Player, slot: Int, type: ClickType) {
        if (onClick != null) {
            onClick.invoke(player, slot, type)
        } else {
            return
        }
    }
}