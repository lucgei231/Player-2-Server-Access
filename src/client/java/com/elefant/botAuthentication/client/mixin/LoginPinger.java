package com.elefant.botAuthentication.client.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.networking.client.ClientNetworkingImpl;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.ServerCommonPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
@Mixin(targets = "net/minecraft/client/gui/screen/multiplayer/ConnectScreen$1")
public class LoginPinger {


    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;send(Lnet/minecraft/network/packet/Packet;)V"))
    public void insert(ClientConnection instance, Packet<?> packet) {

        CustomPayloadC2SPacket a = new CustomPayloadC2SPacket(new VerifyOriginPayload("Test"));


        instance.send(a);
        instance.send(packet);
        System.out.println("HELLO");

    }
}


