package x.withlithum.openew.item

import net.minecraft.world.item.ItemStack
import x.withlithum.openew.component.EwDataComponents

/**
 * Gets a value indicating whether the current instance is marked as equippable in offhand.
 * @see EwDataComponents.OFFHAND_CATEGORY
 */
val ItemStack.isOffhandEquippable: Boolean
    get() = has(EwDataComponents.OFFHAND_CATEGORY)

/**
 * Assists dealing with items.
 */
object EwItems {
    /**
     * Returns the identifier associated with the specified item stack.
     *
     * @return The numeric identifier, or <c>0</c> if it does not have one.
     * @see EwDataComponents.ID
     */
    fun getId(item: ItemStack) : Int {
        val component = item.get(EwDataComponents.ID) ?: return 0
        return component;
    }
}