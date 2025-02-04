package com.elefant.botAuthentication.client;

import com.elefant.botAuthentication.networking.VerifyOriginPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.impl.networking.AbstractNetworkAddon;
import net.fabricmc.fabric.impl.networking.client.ClientLoginNetworkAddon;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestPayload;
import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Shadow;

public class BotAuthenticationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLoginConnectionEvents.INIT.register( (handler, client) -> {
            LoginQueryRequestS2CPacket a = new LoginQueryRequestS2CPacket(0, new LoginQueryRequestPayload() {
                @Override
                public Identifier id() {
                    return VerifyOriginPayload.ID.id();
                }

                @Override
                public void write(PacketByteBuf buf) {
                    buf.writeString("Test");
                }
            });
        });
        System.out.println("Hello from the client side!");

    }
}


//public class Test extends AbstractNetworkAddon<ClientLoginNetworking.LoginQueryRequestHandler>