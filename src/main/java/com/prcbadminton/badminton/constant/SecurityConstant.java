package com.prcbadminton.badminton.constant;

public class SecurityConstant {
    public static final String SECRET = "badmintonprojectcloud";
    public static long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "role";

    public static void setExpirationTime(long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }
}
