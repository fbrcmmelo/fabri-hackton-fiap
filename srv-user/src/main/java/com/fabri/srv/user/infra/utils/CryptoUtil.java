package com.fabri.srv.user.infra.utils;

import com.fabri.srv.user.infra.exceptions.CryptoUtilException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class CryptoUtil {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String value, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            log.error("Failed to encrypt value", e);
            throw new CryptoUtilException("Failed to encrypt value", e);
        }
    }

    public static String decrypt(String encrypted, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encrypted);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        } catch (Exception e) {
            log.error("Failed to decrypt value", e);
            throw new CryptoUtilException("Failed to decrypt value", e);
        }
    }

    public static SecretKey generateKey(String key) {
        try {
            return new SecretKeySpec(key.getBytes(), ALGORITHM);
        } catch (Exception e) {
            log.error("Failed to generate encryption key", e);
            throw new CryptoUtilException("Failed to generate encryption key");
        }
    }

}