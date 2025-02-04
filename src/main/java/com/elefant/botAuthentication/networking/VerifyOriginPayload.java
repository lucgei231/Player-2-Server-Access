package com.elefant.botAuthentication.networking;

import net.minecraft.network.NetworkSide;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.listener.ServerCommonPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;

public record VerifyOriginPayload(String input) implements CustomPayload, Packet<PacketListener> {
    public static final CustomPayload.Id<VerifyOriginPayload> ID = new CustomPayload.Id<>(ElefantNetworkingConstants.VERIFY_ORIGIN);
    public static final PacketCodec<RegistryByteBuf, VerifyOriginPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, VerifyOriginPayload::input, VerifyOriginPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }

    @Override
    public PacketType<VerifyOriginPayload> getPacketType() {
        return new PacketType<>(NetworkSide.SERVERBOUND, ID.id());
    }

    @Override
    public void apply(PacketListener listener) {

    }

}
