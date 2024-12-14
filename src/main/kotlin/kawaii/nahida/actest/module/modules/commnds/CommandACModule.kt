package kawaii.nahida.actest.module.modules.commnds

import kawaii.nahida.actest.ACTest
import kawaii.nahida.actest.ac.ACToggle
import kawaii.nahida.actest.handle.message.MessageExtend.sendCommandResult
import kawaii.nahida.actest.handle.message.MessageExtend.sendMessageWithPrefix
import kawaii.nahida.actest.module.Module
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@Suppress("Unused")
class CommandACModule : Module("CommandAC") {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (event.message.startsWith("/ac", true)) {
            event.isCancelled = true

            val args = event.message.split(" ")

            try {
                if (args.size <= 1) throw Throwable("可选反作弊列表: ${ACTest.acToggleManager.acToggles.joinToString(separator = "${ChatColor.LIGHT_PURPLE}, ") { it.displayName }}")

                mutableListOf<ACToggle>()
                    .also { acs ->
                        args.toMutableList()
                            .apply {
                                removeFirst()
                            }
                            .onEach { arg ->
                                ACTest.acToggleManager.acToggles.firstOrNull { ac ->
                                    ac.name.startsWith(prefix = arg, ignoreCase = true)
                                }
                                    ?.also { ac ->
                                        acs.add(ac)
                                    } ?: throw Throwable("未知的反作弊 $arg")
                            }
                    }
                    .also { acs ->
                        acs.onEach { ac ->
                            if (ac.isSingleActive && acs.size > 1) throw Throwable("反作弊 ${ac.displayName}${ChatColor.LIGHT_PURPLE} 不可以和其它反作弊一起使用")
                        }
                    }
                    .onEach { ac ->
                        ac.onEnable(event.player)
                    }
                    .also { activeACs ->
                        ACTest.acToggleManager.acToggles.onEach { ac ->
                            if (!activeACs.contains(ac)) ac.onDisable(event.player)
                        }
                    }

                event.player.sendMessageWithPrefix("反作弊切换成功")
            } catch (t: Throwable) {
               event.player.sendCommandResult(t, "/ac <反作弊> [更多反作弊..]")
            }
        }
    }
}