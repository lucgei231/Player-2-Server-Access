package com.elefant.botAuthentication;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import com.elefant.botAuthentication.networking.ElefantNetworkingConstants;
import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;

public class BotAuthentication implements ModInitializer {

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(VerifyOriginPayload.ID, VerifyOriginPayload.CODEC);
        ServerLoginNetworking.registerGlobalReceiver(VerifyOriginPayload.ID.id(), (server, handler,baz,packetByteBuf, loginSynchronizer, packetSender) -> {
            String input = packetByteBuf.readString();
            if (input.equals("Player468")) {
                System.out.println("Elefant is trying to join the server!");
            }
            System.out.println("Player " + input + " is trying to join the server!");
        });
    }
}
