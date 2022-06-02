package ltd.matrixstudios.framework.tasks

import ltd.matrixstudios.framework.FrameworkManager
import ltd.matrixstudios.framework.FrameworkShared
import ltd.matrixstudios.framework.data.redis.RedisDetails
import ltd.matrixstudios.framework.game.GameService
import ltd.matrixstudios.framework.instance.GameServer
import ltd.matrixstudios.framework.instance.GameServerService
import java.util.concurrent.TimeUnit

class CheckServersAndGames : Thread() {

    override fun run() {
        while (true) {
            for (server in GameServerService.findAll()) {
                if (System.currentTimeMillis() - server.lastUpdated >= TimeUnit.MINUTES.toMillis(1)) {
                    server.lastUpdated = 0L
                    server.online = false
                    server.currentlyFull = false
                    server.currentlyPlaying.clear()

                    println("[Monitor] Server ${server.id} was found to be unresponsive. Closed the server from the database end")
                    GameServerService.save(server.id, server)
                }
            }

            for (game in GameService.findAll()) {
                if (System.currentTimeMillis() - game.lastUpdated >= TimeUnit.SECONDS.toMillis(30)) {
                    RedisDetails.jedisResource.use {
                        it.hdel("Framework:games:", game.id)
                    }

                    println("[Monitor] Game ${game.id} was found to be unresponsive. Deleted it from the values")
                }
            }

            try {
                sleep(1500L)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}