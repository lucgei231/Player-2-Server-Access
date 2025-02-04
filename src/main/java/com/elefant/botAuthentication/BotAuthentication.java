package com.elefant.botAuthentication;

import net.fabricmc.api.ModInitializer;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayNetworkHandler;


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
