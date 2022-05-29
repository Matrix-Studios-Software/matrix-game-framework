package ltd.matrixstudios.framework.profiles.data.types.stats

import ltd.matrixstudios.framework.data.serializers.Serializers
import ltd.matrixstudios.framework.profiles.data.ProfileData

class GlobalStatisticEntry(
    var totalKills: Int,
    var totalDeaths: Int,
    var totalGamesWon: Int
) : ProfileData<GlobalStatisticEntry> {

    fun getKDR() : Double {

        val KDR: Double = (totalKills / totalDeaths).toDouble()

        if (KDR.isNaN()) {
            return 0.0
        } else {
            return KDR
        }
    }

    override fun <T> toJson(item: T): String {
        return Serializers.gson.toJson(item)
    }
}