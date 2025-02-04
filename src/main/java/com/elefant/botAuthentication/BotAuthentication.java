package com.elefant.botAuthentication;

import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.impl.networking.PayloadTypeRegistryImpl;
import net.minecraft.network.NetworkPhase;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;


public class BotAuthentication implements ModInitializer {



    @Override
    public void onInitialize() {

        ServerLoginNetworking.registerGlobalReceiver(VerifyOriginPayload.ID.id(), (server, handler,baz,packetByteBuf, loginSynchronizer, packetSender) -> {
            String input = packetByteBuf.readString();
            if (input.equals("Test")) {
                System.out.println("Elefant is trying to join the server!");
            }
            System.out.println("Player " + input + " is trying to join the server!");
        });
    }
}
