package ltd.matrixstudios.framework.tasks

import ltd.matrixstudios.framework.FrameworkBukkit
import ltd.matrixstudios.framework.instance.GameServerService
import org.bukkit.scheduler.BukkitRunnable

class UpdateInstanceTask: BukkitRunnable() {

    override fun run() {
        val currentServer = GameServerService.findById(FrameworkBukkit.instance.config.getString("server.serverID"))
        val currentGame = FrameworkBukkit.instance.currentGame

        if (currentServer != null) {
            currentServer.lastUpdated = System.currentTimeMillis()
        }

        currentGame.lastUpdated = System.currentTimeMillis()
    }
}