package x.withlithum.openew.network.protocol

import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import x.withlithum.openew.util.EwKey
import x.withlithum.openew.util.properties.ClientFlags

/**
 * Instructs the client to set the specified [flag] to the specified [value].
 *
 * The client will not reset this flag, and it is of the responsibility of the server to send the
 * current server-side state upon join.
 */
@JvmRecord
data class ClientboundSetClientFlagPayload(
    val flag: ClientFlags,
    val value: Boolean
) : CustomPacketPayload {
    companion object {
        /**
         * The resource identifier of this custom payload.
         */
        val ID = EwKey.of("change_client_flag")

        /**
         * The type of the custom payload.
         */
        val TYPE = CustomPacketPayload.Type<ClientboundSetClientFlagPayload>(ID)

        /**
         * The codec used to encode the custom payload.
         */
        val CODEC: StreamCodec<ByteBuf, ClientboundSetClientFlagPayload>
            = StreamCodec.composite(ClientFlags.STREAM_CODEC,
            ClientboundSetClientFlagPayload::flag,
            ByteBufCodecs.BOOL,
            ClientboundSetClientFlagPayload::value,
            ::ClientboundSetClientFlagPayload)
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> {
        return TYPE
    }
}