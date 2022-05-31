package ltd.matrixstudios.bedwars.teams

import ltd.matrixstudios.bedwars.teams.ColorTeam
import ltd.matrixstudios.framework.team.AbstractTeam
import org.bukkit.Location
import java.util.*

data class BedwarsTeam(
    var full: Boolean,
    var bedLocation: Location?,
    var spawnLocation: Location?,
    var generatorLocation: Location?,
    var teamColor: ColorTeam
) : AbstractTeam(UUID.randomUUID(), arrayListOf(), arrayListOf())
