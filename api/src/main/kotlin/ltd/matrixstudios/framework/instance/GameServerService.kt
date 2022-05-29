package ltd.matrixstudios.framework.instance

import ltd.matrixstudios.framework.data.mongo.MongoRepository

object GameServerService : MongoRepository<String, GameServer>(
    "gameservers",
    GameServer::class.java
) {

    fun serverNeedsStarting() : Boolean {
        var index = 0
        val servers = this.findAll()

        for (server in servers) {
            if (server.currentlyFull) {
                index++
            }

            if (index >= servers.size) {
                return true
            }

        }

        return false
    }
}