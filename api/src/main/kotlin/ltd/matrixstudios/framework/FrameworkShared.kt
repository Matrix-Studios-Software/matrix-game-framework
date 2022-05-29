package ltd.matrixstudios.framework

import ltd.matrixstudios.framework.data.mongo.MongoDetails
import ltd.matrixstudios.framework.data.redis.RedisDetails

object FrameworkShared {

    fun startup(mongoURI: String, jedisUri: String) {
        MongoDetails.use(mongoURI)
        RedisDetails.use(jedisUri)
    }
}