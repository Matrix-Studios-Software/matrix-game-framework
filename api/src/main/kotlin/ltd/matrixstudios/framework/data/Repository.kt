package ltd.matrixstudios.framework.data

interface Repository<K, T> {

    fun <T> save(key: K, value: T)

    fun findAll() : List<T>

    fun findById(id: K) : T?
}