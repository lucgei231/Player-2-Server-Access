package com.elefant.botAuthentication.client.mixin;

import com.elefant.botAuthentication.networking.ElefantNetworkingConstants;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import com.llamalad7.mixinextras.sugar.Local;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.handler.PacketCodecDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PacketCodecDispatcher.Builder.class)
public abstract class PacketDispatcherPatcher<B extends ByteBuf, V, T> {
    @Shadow public abstract PacketCodecDispatcher.Builder<B, V, T> add(T id, PacketCodec<? super B, ? extends V> codec);

    @Inject(method = "*", at = @At(value = "FIELD", target = "Lnet/minecraft/network/handler/PacketCodecDispatcher$Builder;packetTypes:Ljava/util/List;"))
    private void addVerifyOriginPacket(CallbackInfoReturnable<PacketCodecDispatcher<B, V, T>> cir, @Local Object2IntOpenHashMap object2IntOpenHashMap) {
        System.out.println("HI COULD REPLACE");

        this.add((T) ElefantNetworkingConstants.VERIFY_ORIGIN, (PacketCodec<? super B, ? extends V>) VerifyOriginPayload.CODEC);

        // VerifyOriginPayload a = new VerifyOriginPayload("Test");
        // PacketType<VerifyOriginPayload> packetType =  a.getPacketType();
        // builder.add(packetType, VerifyOriginPayload.CODEC);
    }
}
