package com.pm.suresecure;

import javax.crypto.*;

public class encryption {

    private String UNICODE = "UTF-8";


    SecretKey k = generateKey("AES");

    public static Cipher ciph() {
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("AES");
            return cipher;
        } catch (Exception e) {
            return null;
        }
    }




        public static SecretKey generateKey(String encryptionType) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(encryptionType);
            SecretKey key = kg.generateKey();
            return key;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] encryptString(String dataToEncrypt, SecretKey myKey, Cipher cipher) {
        try {
            byte[] text = dataToEncrypt.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, myKey);
            byte[] textEncrypted = cipher.doFinal(text);
            return textEncrypted;
        } catch (Exception e) {
            return null;
        }

    }

    public static String decryptString(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, myKey);
            byte[] textDecrypted = cipher.doFinal(dataToDecrypt);
            String result = new String(textDecrypted);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
