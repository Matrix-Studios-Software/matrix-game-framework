package ltd.matrixstudios.framework.configs

import java.io.*
import java.util.*


object Config {

    var redisURI: String? = null
    var mongoURI: String? = null

    fun load() {
        val file = File("config.properties")
        if (!file.exists()) {
            try {
                file.createNewFile()
                val output = FileOutputStream(file)
                output.write("redisURI=redis://172.18.0.1:6379/0\n".toByteArray())
                output.write("mongoURI=mongodb://localhost:27017\n".toByteArray())
                output.flush()
                output.close()
            } catch (io: IOException) {
                io.printStackTrace()
            }
        }
        val prop = Properties()
        var input: InputStream? = null
        try {
            input = FileInputStream("config.properties")
            prop.load(input)
            redisURI = prop.getOrDefault("redisURI", "redis://172.18.0.1:6379/0").toString()
            mongoURI = prop.getOrDefault("mongoURI", "mongodb://localhost:27017").toString()
        } catch (io: IOException) {
            io.printStackTrace()
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}