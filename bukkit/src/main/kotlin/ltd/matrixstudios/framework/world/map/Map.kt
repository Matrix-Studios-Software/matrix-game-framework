package ltd.matrixstudios.framework.world.map

import org.bukkit.Location
import org.omg.CORBA.StringHolder

data class Map(
    var id: String,
    var displayName: String,
    var world: String?,
    var x: Int,
    var y: Int,
    var z: Int,
    var worldFolders: MutableList<String>,
    var zippedWorldDirectory: String?
) {
}