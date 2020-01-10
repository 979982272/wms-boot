package com.tudou.system.platform.base.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * 加密解密
 *
 * @author weihua
 * @create 2017-05-13 16:47
 */
public class AESCoder extends BaseCoder {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY_PWD = "cn.czcxy.www.aes";

    public AESCoder() {
    }

    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, (Key) key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, (byte[]) key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return encrypt(data, (Key) k, cipherAlgorithm);
    }

    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(1, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, (byte[]) key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, (Key) key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        Key k = toKey(key);
        return decrypt(data, (Key) k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(2, key);
        return cipher.doFinal(data);
    }

    public static String encrypt(String data) throws Exception {
        Key k = toKey(KEY_PWD.getBytes(Charset.defaultCharset()));
        return byteToHex(encrypt(data.getBytes(Charset.defaultCharset()), (Key) k));
    }

    public static String decrypt(String data) throws Exception {
        Key k = toKey(KEY_PWD.getBytes(Charset.defaultCharset()));
        return new String(decrypt(hexToByte(data), (Key) k), Charset.defaultCharset());
    }
}
