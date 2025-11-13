package x.withlithum.openew.component.predicates;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import x.withlithum.openew.component.EwDataComponents;
import x.withlithum.openew.item.OffhandCategory;

public class PredicateCodecTests {
    @BeforeAll
    static void beforeAll() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        EwDataComponents.initialize();
        EwDataComponentPredicates.initialize();
    }

    @Test
    void testIdPredicateCodecDecodes() {
        // Arrange
        final var gson = new Gson();
        final var element = gson.fromJson("{\"value\":23}", JsonObject.class);

        // Act
        final var result = IdPredicate.CODEC.decode(JsonOps.INSTANCE, element);

        // Assert
        Assertions.assertEquals(23, result.getOrThrow().getFirst().value());
    }

    @Test
    void testOffhandEquippableCodecDecodes() {
        // Arrange
        final var gson = new Gson();
        final var element = gson.fromJson("{\"value\":\"reusable_potion\"}", JsonObject.class);

        // Act
        final var result = OffhandCategoryPredicate.CODEC.decode(JsonOps.INSTANCE, element);

        // Assert
        Assertions.assertEquals(OffhandCategory.REUSABLE_POTION,
                result.getOrThrow().getFirst().value());
    }
}
