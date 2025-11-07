package x.withlithum.openew.component.predicates

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.core.component.predicates.DataComponentPredicate
import x.withlithum.openew.component.EwDataComponents

@JvmRecord
data class OffhandEquippablePredicate(val value: Boolean) : DataComponentPredicate {
    companion object {
        @JvmField
        val CODEC: Codec<OffhandEquippablePredicate> = RecordCodecBuilder.create {
            it.group(
                Codec.BOOL.optionalFieldOf("value", false)
                        .forGetter(OffhandEquippablePredicate::value)
            )
                .apply(it, ::OffhandEquippablePredicate)
        }
    }

    override fun matches(componentGetter: DataComponentGetter): Boolean {
        return value == (componentGetter.get(EwDataComponents.OFFHAND_EQUIPPABLE) != null)
    }
}