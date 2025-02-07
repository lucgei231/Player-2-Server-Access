package com.elefant.botAuthentication.mixin;

import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerLoginNetworkHandler.class)
public interface LoginNetworkHandlerPatcher {

    @Accessor("profileName")
    @Nullable
    public void setProfileName(String name);
}
