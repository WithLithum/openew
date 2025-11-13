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
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType
import x.withlithum.openew.OpenEW
import x.withlithum.openew.util.EwKey

/**
 * Defines and registers loot functions.
 */
object EwLootFunctions {

    val SET_ID = register("set_id", SetIdFunction.CODEC)
    val SET_OFFHAND_CATEGORY = register("set_offhand_category",
        SetOffhandCategoryFunction.CODEC)

    private fun <T> register(name: String, codec: MapCodec<T>): LootItemFunctionType<T>
            where T : LootItemFunction {
        return Registry.register(
            BuiltInRegistries.LOOT_FUNCTION_TYPE,
            EwKey.of(name),
            LootItemFunctionType(codec)
        )
    }

    fun initialize() {
        OpenEW.LOGGER.info("Initialized OpenEW loot functions")
    }
}