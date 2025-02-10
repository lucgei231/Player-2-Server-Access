package com.elefant.botAuthentication.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;

@Mixin(MinecraftServer.class)
public class ServerBrandingMixin {
    @Inject(method = "getServerMetadata", at = @At("RETURN"), cancellable = true)
    public void test(CallbackInfoReturnable<ServerMetadata> cir) {
        ServerMetadata metadata = cir.getReturnValue();

        ServerMetadata.Version oldVersion = metadata.version().get();
        ServerMetadata.Version version = new ServerMetadata.Version(oldVersion.gameVersion() + " (Player2 Supported)", oldVersion.protocolVersion());
        cir.setReturnValue(new ServerMetadata(metadata.description(), metadata.players(), Optional.of(version), metadata.favicon(), metadata.secureChatEnforced()));
    }
}



