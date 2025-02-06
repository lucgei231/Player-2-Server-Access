package com.elefant.botAuthentication.client.mixin;

import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkState;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.listener.ClientCommonPacketListener;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

/*@Mixin(NetworkStateBuilder.class)
public abstract class NetworkStateBuilderPatcher<T extends ServerLoginPacketListener, B extends RegistryByteBuf> {


    @Shadow
    public static <T extends ServerLoginPacketListener, B extends RegistryByteBuf> NetworkState.Factory<T, B> c2s(NetworkPhase type, Consumer<NetworkStateBuilder<T, B>> registrar) {
        return null;
    }

    @Inject(method = "buildFactory", at = @At(value = "HEAD"))
    void addVerifyOriginPacket(CallbackInfoReturnable<NetworkState.Factory<T, B>> cir) {
        c2s(NetworkPhase.LOGIN, (builder) -> {

            VerifyOriginPayload a = new VerifyOriginPayload("Test");

            PacketType<VerifyOriginPayload> packetType =  a.getPacketType();
            builder.add(packetType, VerifyOriginPayload.CODEC);
        });
    }
}*/
