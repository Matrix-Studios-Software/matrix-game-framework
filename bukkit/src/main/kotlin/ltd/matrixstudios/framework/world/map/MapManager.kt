package ltd.matrixstudios.framework.world.map

import ltd.matrixstudios.framework.data.mongo.MongoRepository
import ltd.matrixstudios.framework.util.FileUtils
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.generator.ChunkGenerator
import java.io.File
import java.util.*
import kotlin.concurrent.thread


object MapManager : MongoRepository<String, Map>(
    "maps",
    Map::class.java
) {

    fun loadMap(map: Map) {
        val worldName = "${map.displayName}-${UUID.randomUUID().toString().substring(4)}"
        val worldFolder = File(worldName).absoluteFile

        worldFolder.mkdir()

        map.world = worldName
        map.worldFolders.add(worldName)

        try {
            thread {
                org.apache.commons.io.FileUtils.copyDirectory(
                    File("${Bukkit.getServer().worldContainer.path}/${map.id}"),
                        worldFolder
                )
            }
        } catch (e: Exception)
        {
            println("Issue loading map.")
        }


        try {
            val world = Bukkit.createWorld(WorldCreator.name(worldName))
        } catch (e: Exception)
        {
            println("Issue creating world.")
        }


        MapManager.save(map.id, map)
    }
}