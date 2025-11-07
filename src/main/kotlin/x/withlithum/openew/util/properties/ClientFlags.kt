package x.withlithum.openew.util.properties

import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.util.ByIdMap
import java.util.function.IntFunction

enum class ClientFlags(val id: Int) {
    NONE(0),
    NO_HEALTH_DISPLAY(1);

    companion object {
        val BY_ID: IntFunction<ClientFlags> = ByIdMap.continuous(ClientFlags::id,
            entries.toTypedArray(),
            ByIdMap.OutOfBoundsStrategy.ZERO)

        val STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ClientFlags::id)
    }
}