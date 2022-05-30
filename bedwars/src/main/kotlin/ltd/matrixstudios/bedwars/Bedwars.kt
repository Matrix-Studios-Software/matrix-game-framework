package ltd.matrixstudios.bedwars

import org.bukkit.plugin.java.JavaPlugin

class Bedwars : JavaPlugin() {

    companion object {
        lateinit var instance: Bedwars
    }

    override fun onEnable() {
        saveDefaultConfig()
        instance = this
    }
}