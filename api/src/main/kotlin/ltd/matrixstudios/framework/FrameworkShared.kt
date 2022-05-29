package ltd.matrixstudios.framework

import ltd.matrixstudios.framework.data.mongo.MongoDetails

object FrameworkShared {

    fun startup(mongoURI: String, jedisUri: String) {
        MongoDetails.use(mongoURI)
    }
}