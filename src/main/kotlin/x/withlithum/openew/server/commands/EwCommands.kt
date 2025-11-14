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
import net.minecraft.commands.CommandSourceStack

/**
 * Registers OpenEW commands centrally.
 */
object EwCommands {
    /**
     * Registers the OpenEW commands to the specified [CommandDispatcher].
     *
     * @param dispatcher The command dispatcher. The associated command source must be
     * [CommandSourceStack].
     */
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        EwIdCommand.register(dispatcher)
    }
}