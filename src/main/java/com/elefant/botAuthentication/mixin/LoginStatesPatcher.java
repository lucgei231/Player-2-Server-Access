package com.elefant.botAuthentication.mixin;

import net.minecraft.network.NetworkState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.state.LoginStates;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LoginStates.class)
public interface LoginStatesPatcher {


    @Accessor("C2S_FACTORY")
    static void setFactory(NetworkState.Factory<ServerLoginPacketListener, PacketByteBuf> factory) {
        throw new AssertionError();

    }

    @Accessor("C2S_FACTORY")
    public static NetworkState.Factory<ServerLoginPacketListener, PacketByteBuf> getFactory() {
        throw new AssertionError();

    }

    @Accessor("C2S")
    public static void setNetworkState(NetworkState<ServerLoginPacketListener> networkState) {
        throw new AssertionError();

    }
}


