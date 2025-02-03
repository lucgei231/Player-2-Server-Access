package com.elefant.botAuthentication.mixin.client;

import net.minecraft.network.listener.ServerHandshakePacketListener;
import net.minecraft.network.listener.ServerLoginPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
@Mixin(HandshakeC2SPacket.class)
public class LoginPinger {
    @Inject(method = "apply(Lnet/minecraft/network/listener/ServerHandshakePacketListener;)V", at = @At("HEAD"))
    public void ping(ServerHandshakePacketListener serverHandshakePacketListener, CallbackInfo ci) {
        System.out.println("Pinging server...");
    }
}
