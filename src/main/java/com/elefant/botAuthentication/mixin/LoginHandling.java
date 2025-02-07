package com.elefant.botAuthentication.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.util.Uuids;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class LoginHandling {
    @Shadow private @Nullable String profileName;

    @Shadow abstract void startVerify(GameProfile profile);

    @Shadow @Final private MinecraftServer server;

    @Inject(method = "onHello", at= @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerLoginNetworkHandler;profileName:Ljava/lang/String;"), cancellable = true)
    public void onHello(LoginHelloC2SPacket packet, CallbackInfo ci) {
        System.out.println(this.profileName);
        if (this.profileName.equals("Secret")) {
            this.profileName = packet.name();
            this.startVerify(Uuids.getOfflinePlayerProfile(packet.name()));
            ci.cancel();
        }

    }
}
