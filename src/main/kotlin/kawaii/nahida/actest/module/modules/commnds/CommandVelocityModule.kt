package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.handle.message.MessageExtend.sendCommandResult
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageCommandUsage
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.util.Vector

@Suppress("Unused")
class CommandVelocityModule : Module("CommandVelocity") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/velocity", true)) {
            event.isCancelled = true

            val args = event.message.split(" ")

            try {
                // 长度异常
                if (args.size < 4) throw Throwable()

                val motionX = args[1].toDoubleOrNull()?.takeIf { it in -5.0..5.0 } ?: run { throw Throwable("无效的 MotionX (合法范围 -5 ~ 5)") }
                val motionY = args[2].toDoubleOrNull()?.takeIf { it in -5.0..5.0 } ?: run { throw Throwable("无效的 MotionY (合法范围 -5 ~ 5)") }
                val motionZ = args[3].toDoubleOrNull()?.takeIf { it in -5.0..5.0 } ?: run { throw Throwable("无效的 MotionZ (合法范围 -5 ~ 5)") }

                if (args.size >= 5) {
                    val damage = args[4].toDoubleOrNull()?.takeIf { it >= 0 } ?: run { throw Throwable("无效的 Damage") }

                    event.player.damage(damage)
                }

                event.player.velocity = Vector(motionX, motionY, motionZ)
                event.player.sendMessageWithPrefix("执行 Velocity 成功")
            } catch (t: Throwable) {
                event.player.sendCommandResult(t ,"/velocity <MotionX> <MotionY> <MotionZ> [Damage]")
            }
        }
    }
}