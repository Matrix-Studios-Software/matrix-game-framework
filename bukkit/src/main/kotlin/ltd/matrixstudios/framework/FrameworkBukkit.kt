package ltd.matrixstudios.framework

import co.aikar.commands.PaperCommandManager
import ltd.matrixstudios.framework.game.Game
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.instance.GameServerService
import ltd.matrixstudios.framework.menu.library.listener.MenuListener
import ltd.matrixstudios.framework.servers.commands.ServerCommands
import ltd.matrixstudios.framework.world.map.commands.MapCommands
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class FrameworkBukkit : JavaPlugin() {

    companion object {
        lateinit var instance: FrameworkBukkit
    }

    lateinit var currentGame: Game

    override fun onEnable() {
        saveDefaultConfig()
        instance = this

        FrameworkShared.startup(config.getString("mongoURI"), config.getString("jedisURI"))

        registerListeners()
        createAGameServer()

        createAGame()

        val commandHandler = PaperCommandManager(this).apply {
            this.registerCommand(MapCommands)
            this.registerCommand(ServerCommands)
        }

    }

    fun registerListeners() {
        server.pluginManager.registerEvents(MenuListener(), this)
    }

    fun createAGameServer() {
        if (!GameServerService.exists(config.getString("server.serverID"))) {
            val server = GameServer(
                config.getString("server.serverID"),
                System.currentTimeMillis(),
                true,
                arrayListOf(),
                config.getString("server.quickID"),
                false
            )

            GameServerService.save(server.id, server)
        }
    }

    fun createAGame() {
        val game = Game("game-${UUID.randomUUID().toString().substring(4)}",
            config.getString("server.serverID"),
            System.currentTimeMillis(),
            0L,
            System.currentTimeMillis(),
            GameServer.GameStatus.WAITING
        )

        this.currentGame = game
    }

}