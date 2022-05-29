package ltd.matrixstudios.framework.world.map

import org.bukkit.Location

data class Map(
    var id: String,
    var displayName: String,
    var firstJoinLocation: Location?
) {
}