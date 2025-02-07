package com.elefant.botAuthentication;

import com.elefant.botAuthentication.networking.ElefantNetworkingConstants;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl;
import net.minecraft.network.*;
import net.minecraft.network.listener.ServerLoginPacketListener;
import net.minecraft.network.packet.CookiePackets;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.LoginPackets;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.c2s.common.CookieResponseC2SPacket;
import net.minecraft.network.packet.c2s.login.EnterConfigurationC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginKeyC2SPacket;
import net.minecraft.network.packet.c2s.login.LoginQueryResponseC2SPacket;
import net.minecraft.network.state.LoginStates;


public class BotAuthentication implements ModInitializer {






    @Override
    public void onInitialize() {

        ServerLoginNetworking.registerGlobalReceiver(ElefantNetworkingConstants.VERIFY_ORIGIN, (server, handler, baz, packetByteBuf, loginSynchronizer, packetSender) -> {
            String input = packetByteBuf.readString();
            if (input.equals("Test")) {
                System.out.println("Elefant is trying to join the server!");
            }
            System.out.println("Player " + input + " is trying to join the server!");
        });
    }
}
