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

package x.withlithum.openew.component.predicates;

import com.google.gson.JsonPrimitive;
import com.mojang.serialization.JsonOps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import x.withlithum.openew.item.OffhandCategory;

public class ValueCodecTests {
    @Test
    void offhandCategoryCodec_upperCase_fail() {
        // Arrange
        final var value = new JsonPrimitive("SHIELD");

        // Act
        final var result = OffhandCategory.CODEC.decode(JsonOps.INSTANCE, value);

        // Assert
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    void offhandCategoryCodec_correctValue_success() {
        // Arrange
        final var value = new JsonPrimitive("dual_wield");

        // Act
        final var result = OffhandCategory.CODEC.decode(JsonOps.INSTANCE, value);

        // Assert
        Assertions.assertEquals(OffhandCategory.DUAL_WIELD, result.getOrThrow().getFirst());
    }
}
