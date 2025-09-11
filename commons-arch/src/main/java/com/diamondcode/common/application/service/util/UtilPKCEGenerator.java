package com.diamondcode.common.application.service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * A utility class for generating PKCE (Proof Key for Code Exchange) code verifiers and challenges.
 * <p>
 * This is used to secure the Authorization Code Grant flow for public clients, preventing
 * <p>
 * authorization code interception attacks.
 */

public class UtilPKCEGenerator {


    /**
     * Generates a cryptographically secure random code verifier.
     * <p>
     * The verifier is a URL-safe, base64-encoded string between 43 and 128 characters long.
     *
     * @return A randomly generated code verifier string.
     */

    public static String generateCodeVerifier() {

        SecureRandom secureRandom = new SecureRandom();

        byte[] codeVerifier = new byte[32]; // 32 bytes = 256 bits, which is a good length

        secureRandom.nextBytes(codeVerifier);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);

    }


    /**
     * Generates a code challenge from a code verifier using the SHA-256 hash algorithm.
     * <p>
     * The result is a URL-safe, base64-encoded hash of the verifier.
     *
     * @param codeVerifier The code verifier string.
     * @return The SHA-256 based code challenge string.
     * @throws Exception If the SHA-256 algorithm is not available.
     */

    public static String generateCodeChallenge(String codeVerifier) throws Exception {

        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        messageDigest.update(bytes, 0, bytes.length);

        byte[] digest = messageDigest.digest();

        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);

    }

    public static void main(String[] args) throws Exception {

        String codeVerifier = generateCodeVerifier();

        String codeChallenge = generateCodeChallenge(codeVerifier);

        System.out.println("Code Verifier: " + codeVerifier);

        System.out.println("Code Challenge: " + codeChallenge);

    }

}
