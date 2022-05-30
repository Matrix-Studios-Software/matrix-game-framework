package ltd.matrixstudios.framework.game

import ltd.matrixstudios.framework.instance.GameServer

data class Game(
    var id: String,
    var serverOn: String,
    var createdAt: Long,
    var startedAt: Long,
    var lastUpdated: Long,
    var status: GameServer.GameStatus
) {

}
