package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.exception.PlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description:RSA加密
 * @Package: cn.inbs.blockchain.common.utils.rsa
 * @ClassName:
 * @Date: 2018年05月07-17:42
 * @Author: createBy:zhangmingyang
 **/
public class RSAUtils {
    private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        try {
            //创建初始化参数
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            //生成并返回秘钥对
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Object> keyMap = new HashMap<String, Object>(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("RSA生成秘钥对异常,cause by:", e);
            }
            throw new Exception("RSA生成秘钥对异常", e);
        }
    }

    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static synchronized String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encode(key.getEncoded()).replaceAll(CommonConstants.NEW_LINE, "");
//        return encode(key.getEncoded());
    }

    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encode(key.getEncoded()).replaceAll(CommonConstants.NEW_LINE, "");
//        return encode(key.getEncoded());
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param sourceDataMap
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(TreeMap<String, String> sourceDataMap, String privateKey) throws Exception {
        try {
            String sourceData = mapDataToString(sourceDataMap);
            byte[] dataBytes = sourceData.getBytes(CommonConstants.COMMON_ENCODING);
            byte[] keyBytes = decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(dataBytes);
            return encode(signature.sign());
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("RSA生成数字签名异常,cause by:", e);
            }
            throw new Exception("RSA生成数字签名异常", e);
        }
    }

    /**
     * 校验数字签名
     *
     * @param sourceDataMap
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(TreeMap<String, String> sourceDataMap, String publicKey, String sign) throws Exception {
        try {
            String sourceData = mapDataToString(sourceDataMap);
            byte[] dataBytes = sourceData.getBytes(CommonConstants.COMMON_ENCODING);
            byte[] keyBytes = decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicK = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicK);
            signature.update(dataBytes);
            return signature.verify(decode(sign));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("RSA校验数字签名异常,cause by:", e);
            }
            throw new Exception("RSA校验数字签名异常", e);
        }
    }

    /**
     * 私钥加密
     *
     * @param sourceDataMap 源数据
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(TreeMap<String, String> sourceDataMap, String privateKey) throws Exception {
        try {
            //源数据转换
            String sourceData = mapDataToString(sourceDataMap);
            byte[] dataBytes = sourceData.getBytes(CommonConstants.COMMON_ENCODING);

            //获取最终私钥
            byte[] keyBytes = decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);

            //参数初始化
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateK);

            //对数据分段加密
            int inputLen = dataBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            int i = 0;
            splitData(dataBytes, cipher, inputLen, out, offSet, i, MAX_ENCRYPT_BLOCK);
            byte[] encryptedData = out.toByteArray();
            out.close();

            return encode(encryptedData);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("RSA数据加密异常,cause by:", e);
            }
            throw new Exception("RSA数据加密异常", e);
        }
    }


    /**
     * 公钥解密
     *
     * @param encryptedData 已加密数据(BASE64编码)
     * @param publicKey     公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptedData, String publicKey) throws Exception {
        try {
            //已加密数据转换
            byte[] encryptedDataByte = decode(encryptedData);

            //获取最终公钥
            byte[] keyBytes = decode(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);

            //参数初始化
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicK);

            //对数据分段解密
            int inputLen = encryptedDataByte.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            int i = 0;
            splitData(encryptedDataByte, cipher, inputLen, out, offSet, i, MAX_DECRYPT_BLOCK);
            byte[] decryptedData = out.toByteArray();
            out.close();
            return new String(decryptedData);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("RSA数据解密异常,cause by:", e);
            }
            throw new Exception("RSA数据解密异常", e);
        }
    }

    /**
     * 数据分段
     *
     * @param data
     * @param cipher
     * @param inputLen
     * @param out
     * @param offSet
     * @param i
     * @param maxEncryptBlock
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static void splitData(byte[] data,
                                  Cipher cipher,
                                  int inputLen,
                                  ByteArrayOutputStream out,
                                  int offSet, int i,
                                  int maxEncryptBlock) throws Exception {
        byte[] cache;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxEncryptBlock) {
                cache = cipher.doFinal(data, offSet, maxEncryptBlock);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxEncryptBlock;
        }
    }

    /**
     * map转换为指定格式的string
     *
     * @param treeMap
     * @return
     */
    private static String mapDataToString(TreeMap<String, String> treeMap) {
        if (MapUtils.isEmpty(treeMap)) {
            throw new NullPointerException("treeMap must be not null and size min is 1");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> keySet = treeMap.keySet();
            int mapSize = 0;
            for (String key : keySet) {
                mapSize++;
                String value = treeMap.get(key);
                if (null != value) {
                    stringBuilder
                            .append(key)
                            .append("=")
                            .append(value);
                    if (mapSize != treeMap.size()) {
                        stringBuilder.append("&");
                    }
                }
            }
            return stringBuilder.toString();
        }
    }

    /**
     * BASE64字符串解码为二进制数据
     *
     * @param base64
     * @return
     */
    private static byte[] decode(String base64) {
        return com.cdoframework.cdolib.security.Base64.decode(base64);
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    private static String encode(byte[] bytes) {
        return com.cdoframework.cdolib.security.Base64.encode(bytes);
    }


    /**
     * `
     * 生成签约数据Map
     *
     * @return
     */
    public static TreeMap<String, String> getSignMap(Object object) {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //获取字段值
            try {
                //取值开关
                field.setAccessible(Boolean.TRUE);

                //获取字段名及字段值
                String fieldName = field.getName();
                Object fieldValue = field.get(object);
                treeMap.put(fieldName, null != fieldValue ? fieldValue.toString() : null);
            } catch (IllegalAccessException e) {
                throw new PlatformException("验签未通过");
            }
        }
        return treeMap;
    }
}
