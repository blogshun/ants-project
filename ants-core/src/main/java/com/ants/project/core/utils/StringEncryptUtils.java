package com.ants.project.core.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * BASE64 （可逆加密 常用于登入用户token加密）
 * MD5 、SHA 、HMAC （非可逆加密）
 * Created by xiang.li on 2015/2/27.
 */
public class StringEncryptUtils {

    //必须是大于或等于8位的秘钥
    private static final String KEY = "ants_shunblog";

    private static final char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String MD5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes());
            // 获得密文
            byte[] result = md5.digest();
            // 把密文转换成十六进制的字符串形式
            int j = result.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = result[i];
                str[k++] = hexDigits[b >>> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 根据键值进行加密
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(), KEY.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * 根据键值进行解密
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, KEY.getBytes());
        return new String(bt);
    }

    /**
     * 根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }

    /**
     * 根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = cipherInit(data, key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }

    private static Cipher cipherInit(byte[] data, byte[] key, int cipherValue) throws Exception {
        /** 生成一个可信任的随机数源**/
        SecureRandom sr = new SecureRandom();
        /** 从原始密钥数据创建DESKeySpec对象**/
        DESKeySpec dks = new DESKeySpec(key);
        /** 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象**/
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        /** Cipher对象实际完成加密或解密操作**/
        Cipher cipher = Cipher.getInstance("DES");
        /**用密钥初始化Cipher对象**/
        cipher.init(cipherValue, securekey, sr);
        return cipher;
    }
}