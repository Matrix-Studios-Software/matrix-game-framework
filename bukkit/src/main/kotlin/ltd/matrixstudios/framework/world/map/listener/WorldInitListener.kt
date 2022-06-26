package ltd.matrixstudios.framework.world.map.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.event.world.WorldLoadEvent

class WorldInitListener : Listener {

    @EventHandler
    fun onWorldInit(e: WorldInitEvent) {
        e.world.keepSpawnInMemory = false
    }

    @EventHandler
    fun onWorldLoad(e: WorldLoadEvent) {
        e.world.keepSpawnInMemory = false
    }
}