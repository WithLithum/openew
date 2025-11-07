package x.withlithum.openew.component.predicates

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.advancements.critereon.SingleComponentItemPredicate
import net.minecraft.core.component.DataComponentType
import net.minecraft.util.ExtraCodecs
import x.withlithum.openew.component.EwDataComponents

@JvmRecord
data class IdPredicate(val value: Int) : SingleComponentItemPredicate<Int> {
    companion object {
        @JvmField
        val CODEC: Codec<IdPredicate> = RecordCodecBuilder.create {
            it.group(
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("value")
                    .forGetter(IdPredicate::value)
            )
                .apply(it, ::IdPredicate)
        }
    }

    override fun componentType(): DataComponentType<Int?> {
        return EwDataComponents.ID
    }

    override fun matches(value: Int): Boolean {
        return value == this.value
    }
}