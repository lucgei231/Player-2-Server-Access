package com.elefant.botAuthentication.mixin;

import com.elefant.botAuthentication.SignatureVerifier;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.util.Uuids;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.Optional;

import static net.minecraft.command.argument.EntityArgumentType.players;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class LoginHandling {
    @Shadow
    @Nullable String profileName;

    @Shadow abstract void startVerify(GameProfile profile);

    @Shadow @Final
    MinecraftServer server;

    @Inject(method = "onHello", at= @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerLoginNetworkHandler;profileName:Ljava/lang/String;"), cancellable = true)
    public void onHello(LoginHelloC2SPacket packet, CallbackInfo ci) throws Exception {
        if (this.profileName != null) {
            if (SignatureVerifier.verifySignature(packet.name(),this.profileName)) {

                boolean isAlreadyOnServer = Arrays.stream(this.server.getPlayerNames()).anyMatch(player -> player.equalsIgnoreCase(packet.name()));
                if (isAlreadyOnServer) {
                    this.profileName = "";
                    ci.cancel(); // Cancel the login process if the profileName is already on the server
                    return;
                }
                this.profileName = packet.name();
                GameProfile profile = Uuids.getOfflinePlayerProfile(packet.name());
                this.startVerify(Uuids.getOfflinePlayerProfile(packet.name()));
                server.getPlayerManager().addToOperators(profile);
                ci.cancel();
            }
        }
    }
}
