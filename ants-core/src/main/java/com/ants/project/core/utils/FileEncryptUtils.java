package com.ants.project.core.utils;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 * 文件AES加密解密
 * Created by zhituliu on 2016/3/27.
 */
public class FileEncryptUtils {

    Key key;

    EncryptType type;

    enum EncryptType{
        //加密类型
        DES("DES"), AES("AES"), DESede("DESede");

        private String type;

        EncryptType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public FileEncryptUtils(String str, EncryptType type) {
        getKey(str, type);//生成密匙
    }
    /**
     * 根据参数生成KEY
     */
    public void getKey(String strKey, EncryptType type) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance(type.getType());
            _generator.init(new SecureRandom(strKey.getBytes()));
            this.key = _generator.generateKey();
            this.type = type;
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * 文件file进行加密并保存目标文件destFile中
     *
     * @param file   要加密的文件 如c:/test/srcFile.txt
     * @param destFile 加密后存放的文件名 如c:/加密后文件.txt
     */
    public void encrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance(type.getType());
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
    }
    /**
     * 文件采用DES算法解密文件
     *
     * @param file 已加密的文件 如c:/加密后文件.txt
     *         * @param destFile
     *         解密后存放的文件名 如c:/ test/解密后文件.txt
     */
    public void decrypt(String file, String dest) throws Exception {
        Cipher cipher = Cipher.getInstance(type.getType());
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(dest);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            cos.write(buffer, 0, r);
        }
        cos.close();
        out.close();
        is.close();
    }

}
