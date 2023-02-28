package com.system.bumblebee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// This class provides a method for hashing passwords using the SHA-256 algorithm.
public class PasswordEncrypt {
    private static final Logger logger = LoggerFactory.getLogger(PasswordEncrypt.class);

    // Hashes the given password using the SHA-256 algorithm.
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Error while encrypting password.", ex);
            return "Something went wrong";
        }
    }
}
