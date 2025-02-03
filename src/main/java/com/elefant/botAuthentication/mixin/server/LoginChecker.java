package com.elefant.botAuthentication.mixin.server;


import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.server.MinecraftServer;


@Mixin(ServerLoginNetworkHandler.class)
public class LoginChecker {
    @Shadow
    @Nullable String profileName;

    @Redirect(method = "onHello", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;isOnlineMode()Z", opcode = Opcodes.GETFIELD))
    private boolean test(MinecraftServer instance) {
        if (this.profileName != null && this.profileName.startsWith("Player")) {
            System.out.println("Elefant is trying to join the server!");
            return false;
        } else {
            return instance.isOnlineMode();
        }
    }
}