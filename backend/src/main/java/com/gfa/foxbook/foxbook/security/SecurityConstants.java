package com.gfa.foxbook.foxbook.security;

public class SecurityConstants {
    public static final long JWT_EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public static final byte[] JWT_SECRET = System.getenv("SECRET_KEY").getBytes();
}
