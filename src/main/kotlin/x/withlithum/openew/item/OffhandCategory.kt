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

package x.withlithum.openew.item

import com.mojang.serialization.Codec
import net.minecraft.util.StringRepresentable

/**
 * Specifies the action that should be taken with an item regarding the offhand slot.
 */
enum class OffhandCategory : StringRepresentable {
    /**
     * The item does not do anything with offhand and should not be put into offhand.
     */
    NONE,

    /**
     * The currently equipped [SHIELD] item should be placed into offhand when the item is held in
     * main hand.
     */
    MELEE,

    /**
     * The associated ammo should be placed into offhand when the item is held in offhand.
     */
    BOW,

    /**
     * The item is a shield and should be equipped when a [MELEE] item is held in main hand.
     */
    SHIELD,

    /**
     * The item is a reusable potion elixir and its consumable equivalent should be placed into
     * offhand when it is held in main hand.
     */
    REUSABLE_POTION,

    /**
     * A copy of the item from main hand should be duplicated into the offhand, with its category
     * set to [EQUIPPED].
     */
    DUAL_WIELD,

    /**
     * The item is currently equipped in offhand.
     */
    EQUIPPED;

    /**
     * Gets the serialized name of the current instance.
     *
     * @return The name of the current instance, converted to lower case.
     */
    override fun getSerializedName(): String? {
        return this.name.lowercase()
    }

    companion object {
        /**
         * The codec of this instance.
         */
        @JvmField
        val CODEC: Codec<OffhandCategory?> =
            StringRepresentable.fromEnum(OffhandCategory::values)
    }
}