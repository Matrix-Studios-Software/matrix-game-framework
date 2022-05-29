package ltd.matrixstudios.framework

import co.aikar.commands.PaperCommandManager
import ltd.matrixstudios.framework.menu.library.listener.MenuListener
import ltd.matrixstudios.framework.world.map.commands.MapCommands
import org.bukkit.plugin.java.JavaPlugin

class FrameworkBukkit : JavaPlugin() {

    companion object {
        lateinit var instance: FrameworkBukkit
    }

    override fun onEnable() {
        saveDefaultConfig()
        instance = this

        FrameworkShared.startup(config.getString("mongoURI"), config.getString("jedisURI"))

        registerListeners()

        val commandHandler = PaperCommandManager(this).apply {
            this.registerCommand(MapCommands)
        }

    }

    fun registerListeners() {
        server.pluginManager.registerEvents(MenuListener(), this)
    }
}