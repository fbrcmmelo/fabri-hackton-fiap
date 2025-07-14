package com.fabri.srv.oauth.application.dto;

import java.time.Instant;

public record AuthOutput(String accessToken,
                         String refreshToken,
                         Instant expiresAt) {
}
