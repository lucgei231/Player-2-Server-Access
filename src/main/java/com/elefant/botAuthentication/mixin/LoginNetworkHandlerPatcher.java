package com.elefant.botAuthentication.mixin;

import com.elefant.botAuthentication.ElefantLoginPackets;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerLoginNetworkHandler.class)
public class LoginNetworkHandlerPatcher implements ElefantLoginPackets {

    @Mutable
    @Shadow
    @Nullable String profileName;

    @Override
    public void bot_authentication$onVerifyOrigin(VerifyOriginPayload input) {
        this.profileName = input.input();
    }
}
