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
import net.minecraft.util.Unit
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import x.withlithum.openew.component.EwDataComponents

/**
 * Defines a [loot function](https://minecraft.wiki/w/Item_modifier#Function_types) that adds or
 * removes [`openew:offhand_equippable`][EwDataComponents.OFFHAND_EQUIPPABLE] component based on
 * the provided [switch][value].
 */
class SetOffhandEquippableFunction(
    predicates: MutableList<LootItemCondition>,
    val value: Boolean
) :
    LootItemConditionalFunction(predicates) {

    override fun getType(): LootItemFunctionType<out LootItemConditionalFunction?> {
        return EwLootFunctions.SET_OFFHAND_EQUIPPABLE
    }

    override fun run(
        stack: ItemStack,
        context: LootContext
    ): ItemStack {
        if (value && !stack.has(EwDataComponents.OFFHAND_EQUIPPABLE)) {
            stack.set(EwDataComponents.OFFHAND_EQUIPPABLE, Unit.INSTANCE)
        } else if (value && stack.has(EwDataComponents.OFFHAND_EQUIPPABLE)) {
            stack.remove(EwDataComponents.OFFHAND_EQUIPPABLE)
        }

        return stack
    }

    companion object {
        val CODEC: MapCodec<SetOffhandEquippableFunction> = RecordCodecBuilder.mapCodec {
            commonFields(it)
                .and<Boolean>(
                    Codec.BOOL.fieldOf("value").forGetter { instance: SetOffhandEquippableFunction ->
                        instance.value
                    })
                .apply(it, ::SetOffhandEquippableFunction)
        }
    }
}