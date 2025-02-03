package com.elefant.botAuthentication.client.mixin;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.s2c.login.LoginHelloS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
@Mixin(targets = "net/minecraft/client/gui/screen/ConnectScreen$1")
public class LoginPinger {
    @Inject(method = "connect(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;Lnet/minecraft/client/network/CookieStorage;)V", at = @At(value = "INVOKE", target = ""))
    public void insert(PacketByteBuf buf, CallbackInfo ci) {
        System.out.println("HELLO");
    }
}


