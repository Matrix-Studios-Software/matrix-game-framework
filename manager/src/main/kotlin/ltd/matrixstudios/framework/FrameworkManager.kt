package ltd.matrixstudios.framework

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import com.github.dockerjava.core.DockerClientConfig
import ltd.matrixstudios.framework.configs.Config
import ltd.matrixstudios.framework.tasks.CheckServersAndGames
import kotlin.concurrent.thread

class FrameworkManager {

    var dockerClient: DockerClient

    init {
        Config.load()

        val dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://172.18.0.1:2375").build()
        val dockerClient = DockerClientBuilder.getInstance(dockerClientConfig).build()

        this.dockerClient = dockerClient

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