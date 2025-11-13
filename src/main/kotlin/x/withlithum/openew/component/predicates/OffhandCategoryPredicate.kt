package x.withlithum.openew.component.predicates

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.component.DataComponentGetter
import net.minecraft.core.component.predicates.DataComponentPredicate
import x.withlithum.openew.component.EwDataComponents
import x.withlithum.openew.item.OffhandCategory

@JvmRecord
data class OffhandCategoryPredicate(val value: OffhandCategory?) : DataComponentPredicate {
    companion object {
        @JvmField
        val CODEC: Codec<OffhandCategoryPredicate> = RecordCodecBuilder.create {
            it.group(
                OffhandCategory.CODEC.fieldOf("value")
                        .forGetter(OffhandCategoryPredicate::value)
            )
                .apply(it, ::OffhandCategoryPredicate)
        }
    }

    override fun matches(componentGetter: DataComponentGetter): Boolean {
        val itemCategory = componentGetter.getOrDefault(EwDataComponents.OFFHAND_CATEGORY,
            OffhandCategory.NONE)

        if (value == null && (itemCategory == null || itemCategory == OffhandCategory.NONE))
        {
            return true
        }

        return value == itemCategory
    }
}