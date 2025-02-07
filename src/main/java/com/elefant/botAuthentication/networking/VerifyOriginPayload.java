package com.elefant.botAuthentication.networking;

import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.server.network.ServerLoginNetworkHandler;

public record VerifyOriginPayload(String input) implements Packet<ServerLoginPacketListener> {
    public static final PacketCodec<PacketByteBuf, VerifyOriginPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, VerifyOriginPayload::input, VerifyOriginPayload::new);


    @Override
    public PacketType<VerifyOriginPayload> getPacketType() {
        return TYPE;
    }

    public static final PacketType<VerifyOriginPayload> TYPE = new PacketType<>(NetworkSide.SERVERBOUND, ElefantNetworkingConstants.VERIFY_ORIGIN);

    @Override
    public void apply(ServerLoginPacketListener listener) {
        System.out.println("Hi, you have reached the apply method!");
        ((ServerLoginNetworkHandler)listener.getClass().getGenericSuperclass()).profileName = this.input;
        System.out.println(((ServerLoginNetworkHandler) listener.getClass().getGenericSuperclass()).getConnectionInfo());
        //((LoginNetworkListenerPatcher) listener).onVerifyOrigin(this);
    }

}
