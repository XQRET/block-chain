package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.RSAUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName: TestRSA.java
 * @Date: 2018/6/29 16:55
 * @author: create by zhangmingyang
 **/
public class TestRSA {
    public static void main(String[] args) {
        try {
            Map<String, Object> map = RSAUtils.genKeyPair();
            String privateKey = RSAUtils.getPrivateKey(map);
            String publicKey = RSAUtils.getPublicKey(map);
            System.err.println("privateKey:" + privateKey);
            System.err.println("publicKey:" + publicKey);

            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put("张三", "张三");
            String sign = RSAUtils.sign(treeMap, privateKey);
            treeMap.put("张三", "张三1");
            boolean verify = RSAUtils.verify(treeMap, publicKey, sign);
            System.err.println(verify);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
