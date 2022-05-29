package ltd.matrixstudios.framework.profiles

import ltd.matrixstudios.framework.data.mongo.MongoRepository
import java.util.*

object GameProfileService : MongoRepository<UUID, GenericGameProfile>(
    "gameprofiles",
    GenericGameProfile::class.java
)