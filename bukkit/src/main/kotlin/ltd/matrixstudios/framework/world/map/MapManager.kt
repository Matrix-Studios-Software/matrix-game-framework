package ltd.matrixstudios.framework.world.map

import ltd.matrixstudios.framework.data.mongo.MongoRepository
import ltd.matrixstudios.framework.util.FileUtils
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import org.bukkit.util.FileUtil
import java.io.File
import java.util.*
import org.bukkit.Chunk
import org.bukkit.ChunkSnapshot

object MapManager : MongoRepository<String, Map>(
    "maps",
    Map::class.java
) {

    fun loadMap(map: Map) {
        val worldName = "{map.displayName}-${UUID.randomUUID().toString().substring(4)}"
        val worldFolder = File(worldName).absoluteFile

        worldFolder.mkdir()

        map.world = worldName
        map.worldFolders.add(worldName)

        FileUtils.unzip(map.zippedWorldDirectory!!, worldFolder.absolutePath)

        val worldCreator = WorldCreator(worldFolder.name)

        Bukkit.createWorld(worldCreator)
    }

    fun unloadMap(map: Map) {
        Bukkit.unloadWorld(map.world, false)

        File(map.world!!).delete()
    }
}