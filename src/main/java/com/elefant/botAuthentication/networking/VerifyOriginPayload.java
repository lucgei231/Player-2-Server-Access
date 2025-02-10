package com.elefant.botAuthentication.networking;

import com.elefant.botAuthentication.mixin.LoginNetworkHandlerPatcher;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public record VerifyOriginPayload(String signature) implements Packet<ServerLoginPacketListener> {
    public static final PacketCodec<PacketByteBuf, VerifyOriginPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, VerifyOriginPayload::signature, VerifyOriginPayload::new);


    @Override
    public PacketType<VerifyOriginPayload> getPacketType() {
        return TYPE;
    }

    public static final PacketType<VerifyOriginPayload> TYPE = new PacketType<>(NetworkSide.SERVERBOUND, ElefantNetworkingConstants.VERIFY_ORIGIN);

    @Override
    public void apply(ServerLoginPacketListener listener) {
        ((LoginNetworkHandlerPatcher)listener).setProfileName(this.signature());
    }

}
