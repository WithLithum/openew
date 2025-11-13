package x.withlithum.openew.component.predicates

import com.mojang.serialization.Codec
import net.minecraft.core.Registry
import net.minecraft.core.component.predicates.DataComponentPredicate
import net.minecraft.core.registries.BuiltInRegistries
import x.withlithum.openew.OpenEW
import x.withlithum.openew.util.EwKey

@Suppress("unused")
object EwDataComponentPredicates {
    @JvmField
    val ID = register("id", IdPredicate.CODEC)

    @JvmField
    val OFFHAND_CATEGORY = register("offhand_category", OffhandCategoryPredicate.CODEC)

    private fun <T> register(name: String, codec: Codec<T>): DataComponentPredicate.Type<T>
            where T : DataComponentPredicate {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_PREDICATE_TYPE,
            EwKey.of(name),
            DataComponentPredicate.Type(codec)
        )
    }

    @JvmStatic
    fun initialize() {
        OpenEW.LOGGER.info("Initializing OpenEW data component predicates")
    }
}