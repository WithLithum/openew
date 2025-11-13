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

enum class OffhandCategory() {
    NON_EQUIPPABLE,
    SHIELD,
    REUSABLE_POTION,
    EQUIPPED;

    companion object {
        @JvmField
        val CODEC: Codec<OffhandCategory?> = Codec.STRING.xmap(this::valueOf, this::nameOf)

        private fun nameOf(value: OffhandCategory): String {
            return value.name.lowercase()
        }

        private fun valueOf(value: String): OffhandCategory {
            return when(value) {
                "non_equippable" -> NON_EQUIPPABLE
                "shield" -> SHIELD
                "reusable_potion" -> REUSABLE_POTION
                "equipped" -> EQUIPPED
                else -> NON_EQUIPPABLE
            }
        }
    }
}