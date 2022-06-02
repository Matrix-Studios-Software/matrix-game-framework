package ltd.matrixstudios.framework

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.CreateContainerResponse
import com.github.dockerjava.api.exception.InternalServerErrorException
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.Ports
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import ltd.matrixstudios.framework.configs.Config
import ltd.matrixstudios.framework.tasks.CheckServersAndGames
import java.util.*


class FrameworkManager {


    init {
        Config.load()

        Runtime.getRuntime().exec("screen -dmS na-sg-1")

        FrameworkShared.startup(Config.mongoURI!!, Config.redisURI!!)


        CheckServersAndGames().start()

    }

    companion object {

        lateinit var instance: FrameworkManager

        @JvmStatic
        fun main(args: Array<String>) {
            instance = FrameworkManager()
        }
    }
}