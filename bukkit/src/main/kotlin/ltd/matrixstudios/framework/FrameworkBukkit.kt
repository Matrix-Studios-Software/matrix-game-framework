package ltd.matrixstudios.framework

import ltd.matrixstudios.framework.menu.library.listener.MenuListener
import org.bukkit.plugin.java.JavaPlugin

class FrameworkBukkit : JavaPlugin() {

    companion object {
        lateinit var instance: FrameworkBukkit
    }

    override fun onEnable() {
        saveDefaultConfig()
        instance = this

        FrameworkShared.startup(config.getString("mongoURI"), config.getString("jedisURI"))


    }

    fun registerListeners() {
        server.pluginManager.registerEvents(MenuListener(), this)
    }
}