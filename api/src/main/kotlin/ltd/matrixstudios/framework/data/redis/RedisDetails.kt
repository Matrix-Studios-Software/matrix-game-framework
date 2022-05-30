package ltd.matrixstudios.framework.data.redis

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import java.net.URI

object RedisDetails {

    lateinit var jedisPool: JedisPool
    lateinit var uri: String

    lateinit var jedisResource: Jedis

    lateinit var pubsubPool: JedisPool
    lateinit var pubsubResource: Jedis

    fun use(uri: String) {
        this.uri = uri
        this.jedisPool = JedisPool(URI(uri))
        this.jedisResource = jedisPool.resource

        this.pubsubPool = JedisPool(URI(uri))
        this.pubsubResource = pubsubPool.resource
    }
}
