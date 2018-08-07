package cn.inbs.blockchain.common.utils;


import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils
 * @ClassName:
 * @Date: 2018年05月05-15:03
 * @Author: createBy:zhangmingyang
 * @deprecated
 **/
public class RSAUtilsDeprecated {
    private static Logger logger = LoggerFactory.getLogger(RSAUtilsDeprecated.class);

    private static final String RSA_INSTANCE = "RSA";
    private static final String RSA_PUBLIC_KEY = "public";
    private static final String RSA_PRIVATE_KEY = "private";
    private static final Integer KEY_SIZE = 1024;

    /**
     * 生成密钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> createRSAKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSAUtilsDeprecated.RSA_INSTANCE);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Object> keyMap = new HashMap<String, Object>();
            keyMap.put(RSA_PUBLIC_KEY, publicKey);
            keyMap.put(RSA_PRIVATE_KEY, privateKey);

            if (logger.isDebugEnabled()) {
                logger.debug("生成密钥对成功");
            }
            return keyMap;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("生成密钥对失败,cause by:", e);
            }
            throw new BusinessException(e);
        }
    }

    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     */
    public static String getRSAPublicKeyBASE64(Map<String, Object> keyMap) {
        Key publicKey = (Key) keyMap.get(RSA_PUBLIC_KEY);
        byte[] encoded = publicKey.getEncoded();
        return (new BASE64Encoder()).encodeBuffer(encoded);
    }

    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     */
    public static String getRSAPrivateKeyBASE64(Map<String, Object> keyMap) {
        Key publicKey = (Key) keyMap.get(RSA_PRIVATE_KEY);
        byte[] encoded = publicKey.getEncoded();
        return (new BASE64Encoder()).encodeBuffer(encoded);
    }

    /**
     * 私钥加密数据
     *
     * @param data
     * @param rsaPrivateKeyStr
     * @return
     * @throws Exception
     */
    public static String encryptDataByRSAPrivateKey(String data,
                                                    String rsaPrivateKeyStr) throws Exception {
        //base64转RSAPrivateKey
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(rsaPrivateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtilsDeprecated.RSA_INSTANCE);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

        //初始化Cipher
        Cipher cipher = Cipher.getInstance(RSAUtilsDeprecated.RSA_INSTANCE);
        cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);

        //数据加密
        byte[] encryptBytes = cipher.doFinal(data.getBytes());
        return (new BASE64Encoder()).encodeBuffer(encryptBytes);
    }

    /**
     * 公钥解密数据
     *
     * @param data
     * @param rsaPublicKeyStr
     * @return
     * @throws Exception
     */
    public static String decryptDataByRSAPublicKey(String data,
                                                   String rsaPublicKeyStr) throws Exception {
        //base64转RSAPublicKey
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(rsaPublicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSAUtilsDeprecated.RSA_INSTANCE);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance(RSAUtilsDeprecated.RSA_INSTANCE);
        cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);

        return new String(cipher.doFinal((new BASE64Decoder()).decodeBuffer(data)), CommonConstants.COMMON_ENCODING);
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //创建秘钥对
        Map<String, Object> rsaKey = createRSAKey();

        //私钥数据加密
        String encryptDataByRSAPrivateKey = encryptDataByRSAPrivateKey(" 张三", getRSAPrivateKeyBASE64(rsaKey));

        //公钥解密数据
        String decryptDataByRSAPublicKey = decryptDataByRSAPublicKey(encryptDataByRSAPrivateKey, getRSAPublicKeyBASE64(rsaKey));

        if (logger.isInfoEnabled()) {
            logger.info("数据对称加解密:{}", decryptDataByRSAPublicKey);
        }
    }
}
