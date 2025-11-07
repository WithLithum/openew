package x.withlithum.openew.network.protocol

import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import x.withlithum.openew.util.EwKey
import x.withlithum.openew.util.properties.ClientFlags

@JvmRecord
data class ClientboundSetClientFlagPayload(
    val flag: ClientFlags,
    val value: Boolean
) : CustomPacketPayload {
    companion object {
        val ID = EwKey.of("change_client_flag")
        val TYPE = CustomPacketPayload.Type<ClientboundSetClientFlagPayload>(ID)
        val CODEC = StreamCodec.composite(ClientFlags.STREAM_CODEC,
            ClientboundSetClientFlagPayload::flag,
            ByteBufCodecs.BOOL,
            ClientboundSetClientFlagPayload::value,
            ::ClientboundSetClientFlagPayload)
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> {
        return TYPE
    }
}