package x.withlithum.openew.client

import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.chat.Component
import net.minecraft.world.level.GameType
import kotlin.math.ceil

object HealthDisplay {
    private var hide: Boolean = false

    fun changeHide(value: Boolean) {
        hide = value
    }

    fun tick(client: Minecraft) {
        if (hide) {
            return
        }

        val player = client.player

        val mode = player?.gameMode()

        if (player == null
            || client.screen != null
            || mode == GameType.SPECTATOR
            || mode == GameType.CREATIVE
        ) {
            return
        }

        client.gui.setOverlayMessage(
            createComponent(player),
            false
        )
    }

    private fun createComponent(player: LocalPlayer): Component {
        val absorption = player.absorptionAmount

        // Show yellow text to indicate absorption.
        var color = ChatFormatting.RED
        if (absorption > 0) {
            color = ChatFormatting.YELLOW
        }

        val currentHealth = ceil((player.health + absorption) * 5).toInt()
        val maxHealth = ceil(player.maxHealth * 5).toInt()

        val text = String.format("$currentHealth/$maxHealth ❤")
        return Component.literal(text)
            .withStyle(color)
    }
}