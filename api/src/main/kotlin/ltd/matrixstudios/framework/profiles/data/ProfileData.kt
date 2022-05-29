package ltd.matrixstudios.framework.profiles.data

interface ProfileData<T> {

    fun <T> toJson(item: T) : String
}