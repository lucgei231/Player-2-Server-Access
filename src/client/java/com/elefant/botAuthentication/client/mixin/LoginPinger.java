package com.elefant.botAuthentication.client.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
@Mixin(targets = "net/minecraft/client/gui/screen/multiplayer/ConnectScreen$1")
public class LoginPinger {


    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;send(Lnet/minecraft/network/packet/Packet;)V"))
    public void insert(ClientConnection instance, Packet<?> packet) throws InterruptedException {

        ((ConnectionFixer) instance).channel().writeAndFlush(new VerifyOriginPayload("Test")).await();

        instance.send(packet);
        System.out.println("HELLO");

    }
}


