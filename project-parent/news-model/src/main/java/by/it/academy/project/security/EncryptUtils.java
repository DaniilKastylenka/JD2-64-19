package by.it.academy.project.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptUtils {

    private static final String STRING_256 = "%064x";
    private static final int SALT_SIZE = 20;

    public static String getSHA256(String input, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(salt.getBytes());
            byte[] digest = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            return String.format(STRING_256, new BigInteger(1, digest));
        } catch (Exception e) {
            throw new RuntimeException("error while encrypting password" + e);
        }
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return bytesToString(salt);
    }

    private static String bytesToString(byte[] salt) {
        return Base64.getEncoder().encodeToString(salt);
    }



}


