package ltd.matrixstudios.framework.spectator

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent

class SpectatorListener : Listener {

    @EventHandler
    fun breakBlock(event: BlockBreakEvent) {
        if (
            SpectatorHandler.spectators.contains(event.player.uniqueId)
            &&
            !event.player.hasPermission("framework.spectators.bypass")
        ) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun placeBlock(event: BlockPlaceEvent) {
        if (
            SpectatorHandler.spectators.contains(event.player.uniqueId)
            &&
            !event.player.hasPermission("framework.spectators.bypass")
        ) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if (
            SpectatorHandler.spectators.contains(event.player.uniqueId)
            &&
            !event.player.hasPermission("framework.spectators.bypass")
        ) {
            event.isCancelled = true
        }
    }
}