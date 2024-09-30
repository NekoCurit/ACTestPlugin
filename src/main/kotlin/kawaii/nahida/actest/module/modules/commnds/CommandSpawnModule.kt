package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandSpawnModule : Module("CommandSpawn") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/spawn", true)) {
            event.isCancelled = true
            event.player.teleport(event.player.world.spawnLocation)
            event.player.sendMessageWithPrefix("传送成功")
        }
    }
}