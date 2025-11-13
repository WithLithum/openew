/*
 * Copyright (c) 2025 WithLithum & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package x.withlithum.openew.item.loot.functions

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import x.withlithum.openew.component.EwDataComponents
import x.withlithum.openew.item.OffhandCategory

/**
 * Defines a [loot function](https://minecraft.wiki/w/Item_modifier#Function_types) that adds or
 * removes [`openew:offhand_category`][EwDataComponents.OFFHAND_CATEGORY] component based on the
 * provided [value][value].
 */
class SetOffhandCategoryFunction(
    predicates: MutableList<LootItemCondition>,
    val value: OffhandCategory
) :
    LootItemConditionalFunction(predicates) {

    override fun getType(): LootItemFunctionType<out LootItemConditionalFunction?> {
        return EwLootFunctions.SET_OFFHAND_CATEGORY
    }

    override fun run(
        stack: ItemStack,
        context: LootContext
    ): ItemStack {
        stack.set(EwDataComponents.OFFHAND_CATEGORY, value)

        return stack
    }

    companion object {
        val CODEC: MapCodec<SetOffhandCategoryFunction> = RecordCodecBuilder.mapCodec {
            commonFields(it)
                .and<OffhandCategory>(
                    OffhandCategory.CODEC.fieldOf("value").forGetter {
                        instance -> instance.value
                    })
                .apply(it, ::SetOffhandCategoryFunction)
        }
    }
}