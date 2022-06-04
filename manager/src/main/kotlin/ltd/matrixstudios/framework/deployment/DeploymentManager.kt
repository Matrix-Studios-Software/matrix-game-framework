package ltd.matrixstudios.framework.deployment

object DeploymentManager {

    fun dock(image: String, port: Int) {
        Runtime.getRuntime().exec("docker run -p $port:$port $image")
    }

}