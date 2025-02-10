package com.elefant.botAuthentication.mixin;


import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkState;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.CookiePackets;
import net.minecraft.network.packet.LoginPackets;
import net.minecraft.network.packet.c2s.common.CookieResponseC2SPacket;
import net.minecraft.network.packet.c2s.login.EnterConfigurationC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.state.LoginStates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;


@Mixin(LoginStates.class)
public class LoginChecker {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkStateBuilder;c2s(Lnet/minecraft/network/NetworkPhase;Ljava/util/function/Consumer;)Lnet/minecraft/network/NetworkState$Factory;"))
    private static NetworkState.Factory<ServerLoginPacketListener, PacketByteBuf> init(NetworkPhase type, Consumer<NetworkStateBuilder<ServerLoginPacketListener, PacketByteBuf>> registrar) {
        return NetworkStateBuilder.c2s(
                NetworkPhase.LOGIN,
                builder -> builder.add(LoginPackets.HELLO_C2S, LoginHelloC2SPacket.CODEC)
                        .add(LoginPackets.KEY, LoginKeyC2SPacket.CODEC)
                        .add(LoginPackets.CUSTOM_QUERY_ANSWER, LoginQueryResponseC2SPacket.CODEC)
                        .add(LoginPackets.LOGIN_ACKNOWLEDGED, EnterConfigurationC2SPacket.CODEC)
                        .add(CookiePackets.COOKIE_RESPONSE, CookieResponseC2SPacket.CODEC)
                        .add(VerifyOriginPayload.TYPE, VerifyOriginPayload.CODEC)
        );
    }
}