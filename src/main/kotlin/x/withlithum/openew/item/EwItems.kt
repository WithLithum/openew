package x.withlithum.openew.item

import net.minecraft.world.item.ItemStack
import x.withlithum.openew.component.EwDataComponents

/**
 * Gets a value indicating whether the current instance is marked as equippable in offhand.
 * @see EwDataComponents.OFFHAND_EQUIPPABLE
 */
val ItemStack.isOffhandEquippable: Boolean
    get() = has(EwDataComponents.OFFHAND_EQUIPPABLE)

/**
 * Assists dealing with items.
 */
object EwItems {
    fun getId(item: ItemStack) : Int {
        val component = item.get(EwDataComponents.ID) ?: return -1
        return component;
    }
}