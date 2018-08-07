package cn.inbs.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description: 第三方访问合约信息H5-访问URL生成
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年06月08-15:12
 * @Author: createBy:zhangmingyang
 **/
public class AssemblyQueryContractInfoUrl {
    private static Logger logger = LoggerFactory.getLogger(AssemblyQueryContractInfoUrl.class);

    /**
     * 访问接口路径
     */
    private static final String BASE_URL = "https://block.mynbf.cn/block-chain-service/contract/thirdQueryContractDetailInfo.do";

    /**
     * 资金端秘钥
     */
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKAmx89L3elA6ebQP/v1b/BHRRNHzHpALQ/nJLQusgprSdGLWKQme5GXZKPR5KQZ32wY9Uqpu8zNQ/leU3JVPQYQR3MTqQjpzJXMI4C+zIu2GTvkUbZbu8eDbqJOD5L4Z+yfZ3041kLOn/xCVkj7tEpArIe3hBORR5SmMZTS7aMlAgMBAAECgYAL0fDrXtkZDx50V6hxSzMg09HIU4NG/peU1eWCmvOvCy4elh9Ltj9mhnr6EFLEvdgcnVb1VMO0oO3EaO7VwG7JdhUiCDT8r52uubGpy10Bv6XXP9pqP+JanEtCQf+OrGjpfWtI4EKZJruCNDV/RE6WD/Zc5RPafaAVXWXNK3wvoQJBAP9u3+WbFSEInCn4XHhB8u6Xn+BEp7QAt/Pk9rQIUn/vnPXdI0yMK4JuEtU/3NtFg8czDbw9hO7ueQlUAr6Niq0CQQCggcVw5fJFOfsK4yMpp5gOgD0jB7TTNBMtofyd5tNyMZuy0HY1kOIkn/JwOdtwaSVJqRBfQ9Pw6W+UcQsoXMFZAkBsXALLisCp5NcqztubgI6oPNZr83QZXnyrEKS/txAYifPSMV+6J3SFvmBq6s6sjo7awIPBKW/tVGkwVd1cLPt5AkEAmJdm/pMNhXS+zrXruvEi5T5TYRc1eMj8JpjmkbNdeCXjcNcwWnJ4G/uXv8uxbCf6FBJPh1HzjwhOVOA3jFLKWQJAH8zGyY3rIS4vKZSb1XwBJbKf28oeT7Ebu6cZxiB5swiGcMTD+5ovoChWpW3Asc+8NBbULe2CUbXbe59v6uaiKQ==";


    /**
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String reqUrl = createUrl("82iiea6SwiivP9Piiea6vP9PThsgD582656545sg",
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD5iH0AFaWQQebJROLa8mriapix04rQTPWOkaPPm2jcMzUkLM+4Wp4yulvHUmM9MdDoKb0vTVrINoEGEAmT0j59nc3WBRXEd4irDj827xMU8Vj4Mh1ZJUh0bsBqP22HyRkyXXB+ksiAF9m1wqyNVTWhv96DF2zirXAwPCZrbVa/9QIDAQAB",
                "1GChC41hvt");
        logger.info("生成URL:[{}]", reqUrl);

    }

    /**
     * 生成访问区块链合约信息URL
     *
     * @param fundsCompanyBlockId    资金端区块ID
     * @param assetsCompanyPublicKey 对接资产端公钥
     * @param contractId             访问合约ID
     * @return
     * @throws Exception
     */
    private static String createUrl(String fundsCompanyBlockId, String assetsCompanyPublicKey, String contractId) throws Exception {
        //生成签名数据(拿原始数据生成数据签名)
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("fundsCompanyBlockId", fundsCompanyBlockId);
        treeMap.put("assetsCompanyPublicKey", assetsCompanyPublicKey);
        treeMap.put("contractId", contractId);
        String sign = sign(treeMap, PRIVATE_KEY);
        logger.info("签名数据:[{}]", sign);

        //切记签名数据特殊符号转换(**********重要*********)
        sign = URLEncoder.encode(sign, "utf-8");

        //组装URL[接口路劲?key=value&key=value&key=value]
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder
                .append("?")
                .append("fundsCompanyBlockId").append("=").append(fundsCompanyBlockId)
                .append("&")
                .append("assetsCompanyPublicKey").append("=").append(URLEncoder.encode(assetsCompanyPublicKey, "utf-8"))//公钥特殊符号转码(**********重要*********)
                .append("&")
                .append("contractId").append("=").append(contractId)
                .append("&")
                .append("sign").append("=").append(sign);
        return urlBuilder.toString();
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param sourceDataMap 源数据Map
     * @param privateKey    私钥
     * @return
     * @throws Exception
     */
    private static String sign(TreeMap<String, String> sourceDataMap, String privateKey) throws Exception {
        try {
            //将map转换为指定的字符串
            String sourceData = mapDataToString(sourceDataMap);

            //数据装换
            byte[] dataBytes = sourceData.getBytes("utf-8");
            byte[] keyBytes = decode(privateKey);

            //生成签名数据
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
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
     * map转换为指定格式的string
     *
     * @param treeMap
     * @return
     */
    private static String mapDataToString(TreeMap<String, String> treeMap) {
        if (null == treeMap || treeMap.size() == 0) {
            throw new NullPointerException("treeMap must be not null and size min is 1");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> keySet = treeMap.keySet();
            int mapSize = 0;
            for (String key : keySet) {
                mapSize++;
                String value = treeMap.get(key);
                stringBuilder
                        .append(key)
                        .append("=")
                        .append(value);
                if (mapSize != treeMap.size()) {
                    stringBuilder.append("&");
                }
            }
            return stringBuilder.toString();
        }
    }

    /**
     * 转码
     *
     * @param strData
     * @return
     */
    private static byte[] decode(String strData) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(strData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转码
     *
     * @param bysData
     * @return
     */
    private static String encode(byte[] bysData) {
        String strEncoded;
        BASE64Encoder encoder = new BASE64Encoder();
        strEncoded = encoder.encode(bysData);
        strEncoded = strEncoded.replaceAll("\r\n", "");
        return strEncoded;
    }
}
