package com.basic.zyz.common.utils;

/**
 * Created by yao on 2018/12/17.
 */
public class SecretUtils {

    public static final String HASH_ALGORITHM = "SHA-1";

    public static final int SALT_SIZE = 8;

    public static final int HASH_INTERATIONS = 1024;

    /**
     * 生成密码，生成随机数的16为盐值并且经过1024次 SHA-1 Hash
     * @param plainPassword
     * @return
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /**
     * @param planinPassword 明文密码
     * @param password 加密的密码
     * @return 验证结果
     */
    public static boolean validatePassword(String planinPassword, String password) {
        String plain = Encodes.unescapeHtml(planinPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

}
