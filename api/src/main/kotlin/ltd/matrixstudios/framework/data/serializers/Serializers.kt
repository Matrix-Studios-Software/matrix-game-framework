package ltd.matrixstudios.framework.data.serializers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import java.lang.reflect.Type
import kotlin.reflect.KClass

object Serializers {

    var gson: Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create()

    fun setCustomGSON(gson: Gson) {
        this.gson = gson
    }

    fun <T> serialize(item: T) : String {
        return gson.toJson(item)
    }

    fun <T> deserialize(item: String, from: Class<T>) : T {
        return gson.fromJson(item, from)
    }
}