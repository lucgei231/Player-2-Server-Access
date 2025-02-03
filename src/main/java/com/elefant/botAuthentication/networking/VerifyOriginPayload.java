package com.elefant.botAuthentication.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record VerifyOriginPayload(String input) implements CustomPayload {
    public static final CustomPayload.Id<VerifyOriginPayload> ID = new CustomPayload.Id<>(ElefantNetworkingConstants.VERIFY_ORIGIN);
    public static final PacketCodec<RegistryByteBuf, VerifyOriginPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, VerifyOriginPayload::input, VerifyOriginPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
