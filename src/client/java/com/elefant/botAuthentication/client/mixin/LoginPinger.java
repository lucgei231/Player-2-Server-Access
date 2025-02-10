package com.elefant.botAuthentication.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import com.elefant.botAuthentication.SignatureVerifier;
@Mixin(targets = "net/minecraft/client/gui/screen/multiplayer/ConnectScreen$1")
public class LoginPinger {
    @Shadow @Final
    MinecraftClient field_33738;

    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;send(Lnet/minecraft/network/packet/Packet;)V"))
    public void insert(ClientConnection instance, Packet<?> packet) throws Exception {

        String signedName = SignatureVerifier.signString(this.field_33738.getSession().getUsername());

        VerifyOriginPayload verifyPacket = new VerifyOriginPayload(signedName);

        instance.send(verifyPacket);
        instance.send(packet);
    }
}


