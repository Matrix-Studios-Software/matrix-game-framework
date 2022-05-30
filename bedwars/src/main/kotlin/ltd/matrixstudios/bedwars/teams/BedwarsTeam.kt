package ltd.matrixstudios.bedwars.teams

import ltd.matrixstudios.bedwars.teams.ColorTeam
import ltd.matrixstudios.framework.team.AbstractTeam
import org.bukkit.Location
import java.util.*

data class BedwarsTeam(
    var uuid: UUID,
    var teamMembers: MutableList<UUID>,
    var aliveMembers: MutableList<UUID>,
    var full: Boolean,
    var bedLocation: Location?,
    var generatorLocation: Location?,
    var teamColor: ColorTeam
) : AbstractTeam(uuid, teamMembers, aliveMembers)
