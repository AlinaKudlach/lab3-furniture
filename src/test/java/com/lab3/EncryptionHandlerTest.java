package com.lab3;

import com.lab3.service.EncryptionHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptionHandlerTest {
    @Test
    public void testMd5() {
        String hash = EncryptionHandler.md5("hello");
        assertEquals(32, hash.length());
    }

    @Test
    public void testAesEncryptDecrypt() {
        String key = "1234567890123456";  // 16 символов
        String original = "SecretData";
        String encrypted = EncryptionHandler.encrypt(original, key);
        String decrypted = EncryptionHandler.decrypt(encrypted, key);
        assertEquals(original, decrypted);
    }
}