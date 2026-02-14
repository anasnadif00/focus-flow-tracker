package com.focusflow.boot.web.dto;

public record LoginResponse(String token, String userId, String username) {
}
