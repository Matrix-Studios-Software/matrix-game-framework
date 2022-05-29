package ltd.matrixstudios.framework.data.mongo

import com.mongodb.MongoOptions
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import ltd.matrixstudios.framework.data.Repository
import ltd.matrixstudios.framework.data.serializers.Serializers
import org.bson.Document
import java.util.concurrent.CompletableFuture

abstract class MongoRepository<K, T>(collectionName: String, var type: Class<T>) : Repository<K, T> {

    private val internalCollection: MongoCollection<Document> = MongoDetails.database.getCollection(collectionName)

    override fun <T> save(key: K, value: T) {
        CompletableFuture.runAsync {
            val parsed = Document.parse(Serializers.serialize(value))

            internalCollection.updateOne(
                Filters.eq(
                    "_id",
                    key
                ), Document("\$set", parsed), UpdateOptions().upsert(true)
            )
        }
    }

    override fun findAll(): List<T> {
        val list = arrayListOf<T>()

        val mappedList = internalCollection.find().into(arrayListOf()).map {
            Serializers.deserialize(it.toJson(), type)!!
        }

        list.addAll(mappedList)

        return list

    }

    override fun findById(id: K): T? {
        val item = internalCollection.find(Filters.eq("_id", id)).first() ?: return null

        return Serializers.deserialize(item.toJson(), type)
    }

    override fun exists(id: K): Boolean {
        val document = internalCollection.find(Filters.eq("_id", id)).first()

        return document != null
    }
}