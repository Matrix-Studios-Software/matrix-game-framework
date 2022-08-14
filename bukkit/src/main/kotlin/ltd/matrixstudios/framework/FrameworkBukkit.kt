package ltd.matrixstudios.framework

import co.aikar.commands.PaperCommandManager
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import com.google.gson.internal.GsonBuildConfig
import ltd.matrixstudios.framework.data.serializers.Serializers
import ltd.matrixstudios.framework.game.Game
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.instance.GameServerService
import ltd.matrixstudios.framework.menu.library.listener.MenuListener
import ltd.matrixstudios.framework.serialize.LocationSerializers
import ltd.matrixstudios.framework.spectator.SpectatorListener
import ltd.matrixstudios.framework.tasks.UpdateInstanceTask
import ltd.matrixstudios.framework.voting.VoteFactory
import ltd.matrixstudios.framework.world.map.MapManager
import ltd.matrixstudios.framework.world.map.commands.MapCommands
import ltd.matrixstudios.framework.world.map.listener.WorldInitListener
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class FrameworkBukkit : JavaPlugin() {

    companion object {
        lateinit var instance: FrameworkBukkit
    }

    lateinit var currentGame: Game

    var bukkitGson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .registerTypeAdapter(Location::class.java, LocationSerializers())
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create()

    override fun onEnable() {
        saveDefaultConfig()
        instance = this

        FrameworkShared.startup(config.getString("mongoURI"), config.getString("jedisURI"))

        Serializers.setCustomGSON(bukkitGson)

        MapManager.findAll().forEach {
            val folders = it.worldFolders

            for (world in folders)
            {
                val worldFolder = File(world).absoluteFile

                if (worldFolder.exists())
                {
                    worldFolder.delete()
                }

                if (Bukkit.getServer().getWorld(world) != null)
                {
                    Bukkit.getServer().unloadWorld(world, false)
                    println("Unloaded an unused world")
                }
            }
        }

        registerListeners()
        createAGameServer()

        createAGame()

        UpdateInstanceTask().runTaskTimerAsynchronously(this, 0L, 20L)

        val commandHandler = PaperCommandManager(this).apply {
            this.registerCommand(MapCommands)
        }

    }


    fun registerListeners() {
        server.pluginManager.registerEvents(MenuListener(), this)
        server.pluginManager.registerEvents(SpectatorListener(), this)
        server.pluginManager.registerEvents(WorldInitListener(), this)
    }

    fun createAGameServer() {
        if (!GameServerService.exists(config.getString("server.serverID"))) {
            val server = GameServer(
                config.getString("server.serverID"),
                System.currentTimeMillis(),
                true,
                arrayListOf(),
                config.getInt("server.port"),
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