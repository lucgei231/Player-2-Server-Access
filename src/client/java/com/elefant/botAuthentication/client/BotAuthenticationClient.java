package com.elefant.botAuthentication.client;

import net.fabricmc.api.ClientModInitializer;

public class BotAuthenticationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        System.out.println("Hello from the client side!");

    }
}