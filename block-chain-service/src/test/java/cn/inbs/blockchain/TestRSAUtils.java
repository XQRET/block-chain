package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年05月15-18:02
 * @Author: createBy:zhangmingyang
 **/
public class TestRSAUtils {
    private static Logger logger = LoggerFactory.getLogger(TestRSAUtils.class);

    /**
     * RSA测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //生成密钥对
            Map<String, Object> stringObjectMap = RSAUtils.genKeyPair();
            String privateKey = RSAUtils.getPrivateKey(stringObjectMap);//私钥
            String publicKey = RSAUtils.getPublicKey(stringObjectMap);//公钥

            TreeMap<String, String> sourceMap = new TreeMap<String, String>();
            sourceMap.put("姓名", "张三");
            if (logger.isDebugEnabled()) {
                logger.debug("私钥:{}", privateKey);
                logger.debug("公钥:{}", publicKey);
            }

            //数据加密
            String encryptSourceData = RSAUtils.encryptByPrivateKey(sourceMap, privateKey);
            if (logger.isDebugEnabled()) {
                logger.debug("加密后数据:{}", encryptSourceData);
            }

            //数据解密
            String decryptData = RSAUtils.decryptByPublicKey(encryptSourceData, publicKey);
            if (logger.isDebugEnabled()) {
                logger.debug("解密后数据:{}", decryptData);
            }

            //数据验签
            String sign = RSAUtils.sign(sourceMap, privateKey);
            boolean verify = RSAUtils.verify(sourceMap, publicKey, sign);
            if (logger.isDebugEnabled()) {
                logger.debug("验签结果:{}", verify);
            }
        } catch (Exception e) {
            logger.error("数据加解密异常,cause by:", e);
        }
    }
}
