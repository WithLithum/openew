package x.withlithum.openew.component

import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.ExtraCodecs
import x.withlithum.openew.OpenEW
import x.withlithum.openew.item.OffhandCategory
import x.withlithum.openew.util.EwKey.of

object EwDataComponents {
    /**
     * Definition for the data component that stores the numeric identifier of an item.
     */
    val ID: DataComponentType<Int?> = register(
        "id"
    ) { it.persistent(ExtraCodecs.POSITIVE_INT)
            .networkSynchronized(ByteBufCodecs.VAR_INT)
    }

    val OFFHAND_CATEGORY: DataComponentType<OffhandCategory?> = register(
        "offhand_category"
    ) { it.persistent(OffhandCategory.CODEC) }

    private fun <T> register(name: String,
                             action: (it: DataComponentType.Builder<T>) -> Unit) : DataComponentType<T> {
        val builder = DataComponentType.builder<T>()
        action(builder)

        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
            of(name),
            builder.build())
    }

    @JvmStatic
    fun initialize() {
        OpenEW.LOGGER.info("Initializing OpenEW data components")
    }
}