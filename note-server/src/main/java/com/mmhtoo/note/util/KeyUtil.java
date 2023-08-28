package com.mmhtoo.note.util;

import com.mmhtoo.note.NoteApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

public class KeyUtil {

    public static RSAPrivateKey getRSAPrivateKey()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ClassLoader classLoader = NoteApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("rsa.private")).getFile());
        String content = Files.readString(file.toPath());

        String privateKeyPEM = content
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte [] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static RSAPublicKey getRSAPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        ClassLoader classLoader = NoteApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("rsa.public")).getFile());
        String content = Files.readString(file.toPath());

        String privateKeyPEM = content
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte [] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec pkcs8EncodedKeySpec = new X509EncodedKeySpec(encoded);
        return (RSAPublicKey) keyFactory.generatePublic(pkcs8EncodedKeySpec);
    }

}
