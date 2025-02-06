package com.elefant.botAuthentication.client;

import com.elefant.botAuthentication.mixin.LoginStatesPatcher;
import com.elefant.botAuthentication.networking.ElefantNetworkingConstants;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CookiePackets;
import net.minecraft.network.packet.LoginPackets;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.c2s.common.CookieResponseC2SPacket;
import net.minecraft.network.packet.c2s.login.EnterConfigurationC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.util.Identifier;

public class BotAuthenticationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        System.out.println("Hello from the client side!");

    }
}