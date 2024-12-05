package com.example.pfm.util;

import com.example.pfm.model.User;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "your-secret-key"; // Use a more secure key in a real application

    // Method to generate JWT token
    public String generateToken(User user) {
        // Header
        String header = "{\"alg\": \"HS256\", \"typ\": \"JWT\"}";
        
        // Payload
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expirationTime = new Date(nowMillis + 1000 * 60 * 60); // Token expiration (1 hour)
        
        String payload = String.format("{\"sub\":\"%s\",\"iat\":%d,\"exp\":%d}",
                user.getUsername(), now.getTime() / 1000, expirationTime.getTime() / 1000);

        // Base64 encode the header and payload
        String encodedHeader = base64UrlEncode(header.getBytes());
        String encodedPayload = base64UrlEncode(payload.getBytes());

        // Signature
        String signature = generateSignature(encodedHeader, encodedPayload);

        // Return the complete JWT token
        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    // Method to generate signature using HMAC SHA256
    private String generateSignature(String encodedHeader, String encodedPayload) {
        try {
            String data = encodedHeader + "." + encodedPayload;

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);

            byte[] signedBytes = sha256_HMAC.doFinal(data.getBytes());
            return base64UrlEncode(signedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while signing the JWT", e);
        }
    }

    // Method to Base64 URL encode a byte array
    private String base64UrlEncode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    // Method to extract username from the token
    public String extractUsername(String token) {
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        // Extract "sub" field from payload
        return payload.split(",")[0].split(":")[1].replace("\"", "");
    }

    // Method to check if token is expired
    public boolean isTokenExpired(String token) {
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        long expirationTime = Long.parseLong(payload.split(",")[2].split(":")[1]);
        return new Date().getTime() / 1000 > expirationTime;
    }
}
