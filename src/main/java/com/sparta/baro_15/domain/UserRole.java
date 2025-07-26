package com.sparta.baro_15.domain;

public enum UserRole {
    USER("사용자"),
    ADMIN("관리자");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
