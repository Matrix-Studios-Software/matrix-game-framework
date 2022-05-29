package ltd.matrixstudios.framework.team

import java.util.*

//going to use the actual repository on the bukkit implementation
abstract class AbstractTeam(
    var id: UUID,
    var members: MutableList<UUID>,
    var alive: MutableList<UUID>
) {

    fun teamIsGone() : Boolean {
        return alive.size == 0
    }
}