package ltd.matrixstudios.framework

import ltd.matrixstudios.framework.configs.Config
import ltd.matrixstudios.framework.tasks.CheckServersAndGames
import kotlin.concurrent.thread

class FrameworkManager {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Config.load()

            FrameworkShared.startup(Config.mongoURI!!, Config.redisURI!!)

            CheckServersAndGames().start()
        }
    }
}