package x.withlithum.openew.util.properties

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ByIdMap
import java.util.function.IntFunction

/**
 * Defins the flags that is used to enable or disable something on the client.
 */
enum class ClientFlags(val id: Int) {
    NONE(0),
    NO_HEALTH_DISPLAY(1);

    companion object {
        /**
         * The integral function that is responsible for casting a value to its [Int] equivalent.
         */
        val BY_ID: IntFunction<ClientFlags> = ByIdMap.continuous(ClientFlags::id,
            entries.toTypedArray(),
            ByIdMap.OutOfBoundsStrategy.ZERO)

        /**
         * The codec that is responsible for encoding or decoding the data from or to across the
         * network.
         */
        val STREAM_CODEC: StreamCodec<ByteBuf, ClientFlags> =
            ByteBufCodecs.idMapper(BY_ID, ClientFlags::id)
    }
}