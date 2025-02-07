package com.elefant.botAuthentication;

import com.elefant.botAuthentication.networking.VerifyOriginPayload;

public interface ElefantLoginPackets {
    default void bot_authentication$onVerifyOrigin(VerifyOriginPayload input) {
        return;
    }
}
