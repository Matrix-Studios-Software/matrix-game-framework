package ltd.matrixstudios.framework.game

import ltd.matrixstudios.framework.data.redis.RedisRepository

object GameService : RedisRepository<String, Game>(
    "Framework:games:",
    Game::class.java
) {

}