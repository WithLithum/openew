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

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import x.withlithum.openew.component.EwDataComponents

/**
 * Defines a [loot function](https://minecraft.wiki/w/Item_modifier#Function_types) that adds, or
 * sets the value of, the [`openew:id`][EwDataComponents.ID] component.
 */
class SetIdFunction(
    predicates: MutableList<LootItemCondition>,
    val value: Int
) :
    LootItemConditionalFunction(predicates) {

    override fun getType(): LootItemFunctionType<out LootItemConditionalFunction?> {
        return EwLootFunctions.SET_ID
    }

    override fun run(
        stack: ItemStack,
        context: LootContext
    ): ItemStack {
        stack.set(EwDataComponents.ID, value)
        return stack
    }

    companion object {
        val CODEC: MapCodec<SetIdFunction> = RecordCodecBuilder.mapCodec {
            commonFields(it)
                .and<Int>(
                    Codec.INT.fieldOf("value").forGetter { instance: SetIdFunction ->
                        instance.value
                    })
                .apply(it, ::SetIdFunction)
        }
    }
}