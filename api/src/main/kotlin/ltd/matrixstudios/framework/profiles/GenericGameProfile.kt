package ltd.matrixstudios.framework.profiles

import ltd.matrixstudios.framework.profiles.data.types.stats.GlobalStatisticEntry
import java.util.*

data class GenericGameProfile(
    var uuid: UUID,
    var username: String,
    var globalStatisticEntry: GlobalStatisticEntry
) {
}