package ltd.matrixstudios.framework.team

import ltd.matrixstudios.framework.data.redis.RedisRepository
import java.util.*

object BukkitTeamHandler : RedisRepository<UUID, BukkitTeam>(
    "Framework:bukkitteams:",
    BukkitTeam::class.java
) {

}