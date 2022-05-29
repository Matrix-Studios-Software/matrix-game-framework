package ltd.matrixstudios.framework.util

import org.bukkit.ChatColor

object Chat {

    fun format(string: String) : String {
        return ChatColor.translateAlternateColorCodes('&', string)
    }
}