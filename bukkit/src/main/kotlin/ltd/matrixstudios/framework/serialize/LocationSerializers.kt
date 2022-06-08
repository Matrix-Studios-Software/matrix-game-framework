package ltd.matrixstudios.framework.serialize

import com.google.gson.*
import ltd.matrixstudios.framework.FrameworkBukkit
import org.bukkit.Location
import org.bukkit.World
import java.lang.reflect.Type


class LocationSerializers : JsonDeserializer< Location?>, JsonSerializer<org.bukkit.Location?> {

    override fun serialize(src: org.bukkit.Location?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return toJson(src)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): org.bukkit.Location? {
        return fromJson(json)
    }

    companion object {
        fun toJson(location: Location?): JsonObject? {
            if (location == null) {
                return null
            }
            val jsonObject = JsonObject()
            jsonObject.addProperty("world", location.getWorld().getName())
            jsonObject.addProperty("x", location.getX())
            jsonObject.addProperty("y", location.getY())
            jsonObject.addProperty("z", location.getZ())
            jsonObject.addProperty("yaw", location.getYaw())
            jsonObject.addProperty("pitch", location.getPitch())
            return jsonObject
        }

        fun fromJson(jsonElement: JsonElement?): org.bukkit.Location? {
            if (jsonElement == null || !jsonElement.isJsonObject()) {
                return null
            }
            val jsonObject: JsonObject = jsonElement.getAsJsonObject()
            val world: World = FrameworkBukkit.instance.server.getWorld(jsonObject["world"].asString)
            val x = jsonObject["x"].asDouble
            val y = jsonObject["y"].asDouble
            val z = jsonObject["z"].asDouble
            val yaw = jsonObject["yaw"].asFloat
            val pitch = jsonObject["pitch"].asFloat
            return org.bukkit.Location(world, x, y, z, yaw, pitch)
        }
    }
}