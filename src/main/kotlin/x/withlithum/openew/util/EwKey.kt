package x.withlithum.openew.util

import net.minecraft.resources.ResourceLocation
import x.withlithum.openew.OpenEW

object EwKey {
    @JvmStatic
    fun of(path: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(OpenEW.MOD_ID, path)
    }
}