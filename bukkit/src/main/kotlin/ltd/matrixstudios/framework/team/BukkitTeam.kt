package ltd.matrixstudios.framework.team

import org.bukkit.Location
import java.util.*

data class BukkitTeam(
    var uniqueId: UUID,
    var teamName: String,
    var teamMembers: MutableList<UUID>,
    var aliveMembers: MutableList<UUID>,
    var teamSpawnLocation: Location?
) : AbstractTeam(uniqueId, teamMembers, aliveMembers) {
}