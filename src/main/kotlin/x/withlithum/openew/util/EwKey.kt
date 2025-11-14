package x.withlithum.openew.util

import net.minecraft.resources.ResourceLocation
import x.withlithum.openew.OpenEW

/**
 * Provides utility methods regarding [ResourceLocation].
 */
object EwKey {
    /**
     * Creates a new instance of [ResourceLocation] with the namespace set to [OpenEW.MOD_ID] and
     * with the path set to the specified value.
     */
    @JvmStatic
    fun of(path: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(OpenEW.MOD_ID, path)
    }
}