package com.elefant.botAuthentication.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(MinecraftServer.class)
public class ServerBrandingMixin {
    @Shadow private PlayerManager playerManager;

    @Inject(method = "getServerMetadata", at = @At("RETURN"), cancellable = true)
    public void addServerBrand(CallbackInfoReturnable<ServerMetadata> cir) {
        ServerMetadata metadata = cir.getReturnValue();

        if (metadata.version().isPresent()) {
            ServerMetadata.Version oldVersion = metadata.version().get();
            ServerMetadata.Version version = new ServerMetadata.Version(oldVersion.gameVersion() + " (Player2 Supported)", oldVersion.protocolVersion());

            Optional<ServerMetadata.Players> players = metadata.players();
            if (players.isEmpty()) {
                cir.setReturnValue(new ServerMetadata(metadata.description(), Optional.of(new ServerMetadata.Players(0, 0, new ArrayList<>())), Optional.of(version), metadata.favicon(), metadata.secureChatEnforced()));
            } else {
                List<GameProfile> gameProfiles = new ArrayList<>();
                for (ServerPlayerEntity player : this.playerManager.getPlayerList()) {
                    gameProfiles.add(player.getGameProfile());
                }
                ServerMetadata.Players fixedPlayers = new ServerMetadata.Players(players.get().max(), players.get().online(), gameProfiles);
                cir.setReturnValue(new ServerMetadata(metadata.description(), Optional.of(fixedPlayers), Optional.of(version), metadata.favicon(), metadata.secureChatEnforced()));
            }
        }
    }
}



