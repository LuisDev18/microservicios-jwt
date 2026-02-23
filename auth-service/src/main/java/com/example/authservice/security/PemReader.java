package com.example.authservice.security;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class PemReader {

    public PrivateKey getPrivateKey() {
        try {
            InputStream inputStream = new ClassPathResource("certs/private_key.pem").getInputStream();
            String privatePemKey = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] binaryEncoded = Base64.getDecoder().decode(privatePemKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(binaryEncoded);
            return keyFactory.generatePrivate(spec);

        } catch (Exception e) {
            throw new RuntimeException("Error loading private key", e);
        }
    }

    public PublicKey getPublicKey() {
        try {
            InputStream inputStream = new ClassPathResource("certs/public_key.pem").getInputStream();
            String publicPemKey = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] binaryEncoded = Base64.getDecoder().decode(publicPemKey);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(binaryEncoded);
            return keyFactory.generatePublic(spec);

        } catch (Exception e) {
            throw new RuntimeException("Error loading public key", e);
        }
    }
}
