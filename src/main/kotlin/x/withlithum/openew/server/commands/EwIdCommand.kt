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

package x.withlithum.openew.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.commands.arguments.SlotArgument
import net.minecraft.network.chat.Component
import x.withlithum.openew.component.EwDataComponents

/**
 * Defines a command, `ewid`, that obtains the value of the [`openew:id`][EwDataComponents.ID]
 * data component.
 *
 * The return value of the command is the ID, or `0` if there is no item or that item does not have
 * an ID.
 *
 * @author WithLithum
 */
object EwIdCommand {
    /**
     * Registers this command to the specified dispatcher.
     *
     * @param dispatcher The dispatcher to register to.
     */
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("ewid")
            .requires { it.hasPermission(2) }
            .then(
                Commands.literal("get")
                    .then(
                        Commands.argument("entity", EntityArgument.entity())
                            .then(
                                Commands.argument("slot", SlotArgument.slot())
                                    .executes(this::executeWithSlot)
                            )
                    )
            )
        )
    }

    private fun executeWithSlot(ctx: CommandContext<CommandSourceStack>): Int {
        val victim = EntityArgument.getEntity(ctx, "entity")
        val slot = SlotArgument.getSlot(ctx, "slot")

        val access = victim.getSlot(slot)
        val item = access.get()
        if (item.isEmpty) {
            ctx.source.sendFailure(Component.translatable("openew.commands.ewid.no_item"))
            return 0
        }

        // Get ID. If there is no ID, send command failure.
        val component = item.get(EwDataComponents.ID)
        if (component == null || component == 0) {
            ctx.source.sendFailure(Component.translatable("openew.commands.ewid.no_id"))
            return 0
        }

        // I don't know why Mojang insisted that there **has** to be a lambda here
        ctx.source.sendSuccess({ Component.translatable("openew.commands.ewid.success",
            component) },
            false)
        return component
    }
}