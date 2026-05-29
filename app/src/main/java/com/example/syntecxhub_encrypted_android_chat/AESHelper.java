package com.example.syntecxhub_encrypted_android_chat;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESHelper {

    private static final String SECRET_KEY = "1234567890123456";

    public static String encrypt(String message) throws Exception {

        SecretKeySpec keySpec =
                new SecretKeySpec(
                        SECRET_KEY.getBytes(),
                        "AES"
                );

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                keySpec
        );

        byte[] encryptedBytes =
                cipher.doFinal(
                        message.getBytes()
                );

        return Base64.encodeToString(
                encryptedBytes,
                Base64.DEFAULT
        );
    }

    public static String decrypt(String encryptedMessage)
            throws Exception {

        SecretKeySpec keySpec =
                new SecretKeySpec(
                        SECRET_KEY.getBytes(),
                        "AES"
                );

        Cipher cipher =
                Cipher.getInstance("AES");

        cipher.init(
                Cipher.DECRYPT_MODE,
                keySpec
        );

        byte[] decodedBytes =
                Base64.decode(
                        encryptedMessage,
                        Base64.DEFAULT
                );

        byte[] decryptedBytes =
                cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }
}