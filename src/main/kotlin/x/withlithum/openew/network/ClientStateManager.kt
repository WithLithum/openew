package x.withlithum.openew.network

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.MinecraftServer
import net.minecraft.server.ServerScoreboard
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.scores.ScoreHolder
import x.withlithum.openew.OpenEW
import x.withlithum.openew.network.protocol.ClientboundSetClientFlagPayload
import x.withlithum.openew.util.properties.ClientFlags

object ClientStateManager {
    fun attachEvents() {
        ServerPlayerEvents.JOIN.register(this::onPlayerJoin)
    }

    private fun getNoHudSetting(scoreboard: ServerScoreboard): Boolean {
        val constants = scoreboard.getObjective("constant") ?: return false

        val noHud = scoreboard.getPlayerScoreInfo(
            ScoreHolder.forNameOnly("no_hud"),
            constants
        )

        return noHud != null && noHud.value() >= 1
    }

    private fun onPlayerJoin(player: ServerPlayer) {
        if (player.level().isClientSide) {
            return
        }

        val server = player.level().server
        broadcastClientFlag(server, ClientFlags.NO_HEALTH_DISPLAY,
            getNoHudSetting(server.scoreboard))
    }

    @JvmStatic
    fun broadcastClientFlag(server: MinecraftServer, flag: ClientFlags, value: Boolean) {
        val payload = ClientboundSetClientFlagPayload(flag, value)

        for (it in PlayerLookup.all(server)) {
            ServerPlayNetworking.send(it, payload)
        }
    }
}