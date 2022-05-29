package ltd.matrixstudios.framework.data.mongo

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase

object MongoDetails {

    lateinit var client: MongoClient
    lateinit var database: MongoDatabase
    lateinit var uri: String

    fun use(uri: String) {
        this.uri = uri
        this.client = MongoClient(MongoClientURI(uri))
        this.database = client.getDatabase("MatrixFramework")
    }
}