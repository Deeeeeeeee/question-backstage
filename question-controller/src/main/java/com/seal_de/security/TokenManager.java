package com.seal_de.security;

/**
 * Created by seal_de on 2017/4/14.
 */
public interface TokenManager {
    String createToken(String username);
    boolean checkToken(String token);
    void removeToken(String token);
    String getUsername(String token);
}
