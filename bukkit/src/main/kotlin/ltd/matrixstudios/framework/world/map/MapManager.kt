package ltd.matrixstudios.framework.world.map

import ltd.matrixstudios.framework.data.mongo.MongoRepository

object MapManager : MongoRepository<String, Map>(
    "maps",
    Map::class.java
) {
}