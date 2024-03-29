package ltd.matrixstudios.framework.instance

import javafx.beans.binding.BooleanBinding
import java.util.*

class GameServer(
    var id: String,
    var lastUpdated: Long,
    var online: Boolean,
    var currentlyPlaying: MutableList<UUID>,
    var port: Int,
    var quickId: String,
    var gamemode: String,
    var currentlyFull: Boolean
) {


    enum class GameStatus(
        var color: String,
        var displayableName: String
    ) {
        WAITING("&e", "Starting"),
        STARTED("&c", "Started"),
        DOCKING("&6", "Docking"),
        ENDING("&c", "Ending"),
        ERRORED("&4", "Errored"),
        VOTING("&d", "Voting")

    }


}