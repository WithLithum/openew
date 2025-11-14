package x.withlithum.openew.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import x.withlithum.openew.network.protocol.ClientboundSetClientFlagPayload
import x.withlithum.openew.util.ewLogger
import x.withlithum.openew.util.properties.ClientFlags

/**
 * Implements client protocol and functions.
 */
object OpenEWClient : ClientModInitializer {
    private val LOGGER = ewLogger<OpenEWClient>()

    /**
     * Serves as the client-sided entry point of the OpenEW mod.
     */
	override fun onInitializeClient() {
        LOGGER.info("Initializing OpenEW client")

        ClientTickEvents.END_CLIENT_TICK.register(HealthDisplay::tick)

        ClientPlayNetworking.registerGlobalReceiver(ClientboundSetClientFlagPayload.TYPE,
            this::handleSetFlag)
    }

    private fun handleSetFlag(payload: ClientboundSetClientFlagPayload,
                              context: ClientPlayNetworking.Context) {
        when (payload.flag) {
            ClientFlags.NONE -> {}
            ClientFlags.NO_HEALTH_DISPLAY -> {
                HealthDisplay.changeHide(payload.value)
            }
        }
    }
}