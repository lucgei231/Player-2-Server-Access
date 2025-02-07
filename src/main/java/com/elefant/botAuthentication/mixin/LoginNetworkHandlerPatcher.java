package com.elefant.botAuthentication.mixin;

import com.elefant.botAuthentication.ElefantLoginPackets;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerLoginNetworkHandler.class)
public interface LoginNetworkHandlerPatcher {

    @Accessor("profileName")
    @Nullable
    public void setProfileName(String name);
}
