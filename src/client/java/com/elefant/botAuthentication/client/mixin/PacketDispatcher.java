package com.elefant.botAuthentication.client.mixin;

import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.handler.PacketCodecDispatcher;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(PacketCodecDispatcher.Builder.class)
public abstract class PacketDispatcher<B extends ByteBuf, V, T> {

    @Shadow public abstract PacketCodecDispatcher.Builder<B, V, T> add(T id, PacketCodec<? super B, ? extends V> codec);



    @Inject(method = "build", at = @At(value = "HEAD"))
    private void addVerifyOriginPacket(CallbackInfoReturnable<PacketCodecDispatcher<B, V, T>> cir) {
        System.out.println("Hello from PacketDispatcher");
        CustomPayloadC2SPacket a = new CustomPayloadC2SPacket(new VerifyOriginPayload("Test"));

        PacketType packetType = a.getPacketType();

        Identifier id = packetType.id();
        ;
        this.add((T) packetType.id(), (PacketCodec<? super B, ? extends V>) VerifyOriginPayload.CODEC);
    }
}


