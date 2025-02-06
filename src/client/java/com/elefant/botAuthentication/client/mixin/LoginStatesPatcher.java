package com.elefant.botAuthentication.client.mixin;

import net.minecraft.network.NetworkState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.state.LoginStates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

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


