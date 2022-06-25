package ltd.matrixstudios.framework.data.redis

import ltd.matrixstudios.framework.data.Repository
import ltd.matrixstudios.framework.data.serializers.Serializers
import java.util.concurrent.CompletableFuture

abstract class RedisRepository<K, T>(var redisKey: String, var type: Class<T>) : Repository<K, T> {

    override fun findById(id: K): T? {
        var item: T? = null

        RedisDetails.jedisResource.use {
            val redisItem = it.hget(redisKey, id.toString())

            item = Serializers.deserialize(redisItem, type)
        }

        return item
    }

    override fun findAll(): List<T> {
        val items = arrayListOf<T>()

        RedisDetails.jedisResource.use { jedis ->
            val jedisItems = jedis.hgetAll(redisKey).values.map { Serializers.deserialize(it, type) }

            items.addAll(jedisItems)
        }

        return items
    }

    override fun exists(id: K): Boolean {
        return RedisDetails.jedisResource.use { it.hexists(redisKey, id.toString()) }
    }

    override fun <T> save(key: K, value: T) {

        RedisDetails.jedisResource.use {
            it.hset(
                redisKey,
                key.toString(),
                Serializers.serialize(value)
            )
        }
    }
}