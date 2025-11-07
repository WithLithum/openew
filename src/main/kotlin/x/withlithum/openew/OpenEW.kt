package x.withlithum.openew

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import x.withlithum.openew.component.EwDataComponents
import x.withlithum.openew.component.predicates.EwDataComponentPredicates
import x.withlithum.openew.item.loot.functions.EwLootFunctions
import x.withlithum.openew.network.ClientStateManager
import x.withlithum.openew.network.protocol.ClientboundSetClientFlagPayload
import x.withlithum.openew.util.EwLog

object OpenEW : ModInitializer {
    @JvmField
    val LOGGER = EwLog.getLogger<OpenEW>()

    const val MOD_ID = "openew"

	override fun onInitialize() {
		LOGGER.info("Initializing OpenEW common")

        EwDataComponents.initialize()
        EwDataComponentPredicates.initialize()
        EwLootFunctions.initialize()

        PayloadTypeRegistry.playS2C().register(ClientboundSetClientFlagPayload.TYPE,
            ClientboundSetClientFlagPayload.CODEC)

        ClientStateManager.attachEvents()
	}
}