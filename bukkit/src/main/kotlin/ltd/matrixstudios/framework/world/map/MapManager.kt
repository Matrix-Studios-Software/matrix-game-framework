package ltd.matrixstudios.framework.world.map

import ltd.matrixstudios.framework.data.mongo.MongoRepository
import org.bukkit.Chunk
import org.bukkit.ChunkSnapshot

object MapManager : MongoRepository<String, Map>(
    "maps",
    Map::class.java
) {
}