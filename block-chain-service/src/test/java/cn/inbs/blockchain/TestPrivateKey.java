package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.po.BlockCompany;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年06月11-10:08
 * @Author: createBy:zhangmingyang
 **/
public class TestPrivateKey {
    public static void main(String[] args) throws Exception {
        testRas2();

    }

    private static void testRsa1() throws Exception {
        //生成签名日期
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIoz2uMyO6e3Vo0s9W+kU0RE3vvW\n" +
                "bKTerOkz0NcfycrFDbmfh9Jf/HA6IheqUnumqzI3a96MzM4fmqheWRypGR9Br9IxIAyoljAWghV4\n" +
                "Ay/PjRF8GpjgCdhBdBxxdciNVWB03JjZCyg5fUDaDlwVHFx7RE3cbrBJY48nSOa90MkrAgMBAAEC\n" +
                "gYBzRh1lNBkeI4ZF6yItJNtvLiLv3EJyommHenjPduxR/VsdYTtg9j8RPOwC4UY7bSGOmTuYPcj2\n" +
                "hNXzaf7aMRGKBuCwpN6obc3vxQntWK88XhF78us9O4O4TRFiRgVGJSRhpy15dkP1fg3tzm0A187u\n" +
                "1PIRKoK/rtLV1n86B/NJwQJBAOvUAjvKnwIawQ9j6O+T3Jycy9MX/PvrOSc72F9l8OWzbph3Vxxz\n" +
                "+/N5LnRHGF66sE9+tH2XK9F3LWwh9784f+8CQQCWBhziyqtyc1pCFIjCmNAVUEk8eijqhY7gU/5G\n" +
                "jYTOzzVbS12EtFc8mJp9B/hsFskwOdzS7aYuB7Wln5G1vM6FAkEA58a/xzoWW0AmPNUKC+T77ySf\n" +
                "osGxz3pY+rlzqBcN0wQuD0VDZWCM4LdaM8foKLo9LHQwOjsIQt9p0hv0XYjPGwJAKa5JGDl2qLWj\n" +
                "vfg2htot9b34mvTVqVKx/2FWpANtgofPKy4lLVltY0iCo3ozatRp0RxCXdfo86DwtrM+qF9sgQJB\n" +
                "AJvm0oqLkfhHUtBgzDOyYXo2vfFrLNsn0yN6ZLlNCzTqWah+O/nT4A2cwq4K+udGydEJRxBjaOWW\n" +
                "X1a9Li5GaNE=";
        privateKey = privateKey.replaceAll("\n", "");
        System.err.println(privateKey);

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("name", "张三");
        String sign = RSAUtils.sign(treeMap, privateKey);

        //数据验签
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKM9rjMjunt1aNLPVvpFNERN771myk3qzpM9DX\n" +
                "H8nKxQ25n4fSX/xwOiIXqlJ7pqsyN2vejMzOH5qoXlkcqRkfQa/SMSAMqJYwFoIVeAMvz40RfBqY\n" +
                "4AnYQXQccXXIjVVgdNyY2QsoOX1A2g5cFRxce0RN3G6wSWOPJ0jmvdDJKwIDAQAB";

        publicKey = publicKey.replaceAll("\n", "");
        System.err.println(publicKey);
        boolean verify = RSAUtils.verify(treeMap,
                publicKey,
                sign);
        System.err.println(verify);
    }

    private static void testRsa2() throws Exception {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("name", "张三");


//        Map<String, Object> map = RSAUtils.genKeyPair();
//        String privateKey = RSAUtils.getPrivateKey(map);
//        String publicKey = RSAUtils.getPublicKey(map);
//        System.err.println(privateKey);
//        System.err.println(publicKey);
        String sign = RSAUtils.sign(treeMap, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIFrrXHZJ0NxsL18nBJpR4+5c5GszsXSPIa3dDm67VpxuCfueFY1rtQyAdTKYdGYjC/Z9wCwfzGH8vPQA9cLVG7VYnBOwPl79GsPZRnwH4PPXJg9xmegShSMgIitQdUdXVZh2erpc8e1uKxaTIP7LO8qAT1XVO6r3ejk/pvfx001AgMBAAECgYB/T8JWLUz/fpZjQHJzdhdMXh0+32SkmxSpXUw7AhQ9FYMX3PGJtNNfsDW28sNZIJw+8kaV6gmlWtv3VdiBMYcC+imk1/mSr8R8jAcN9NkyAnBiaP4jcWop2wFbzJAGF0R2b9f6EfKjh4jh//hEcX+q0N9c8nK11eBYoUL8LDV3gQJBANPmSHxjWVAZSvyqosBQinfVxO7+w5YGeft24dR8Upgl+P7iMXbtwqaArXAPKo5FWGAEBPDlw9D0WnTp5OD6OtUCQQCcWwfAEvhZCD7RW2X3IJOZ1tHfa/R15p/Cg6eLw7lsw+Zt2atDez2n8BzF1VeDKkJahnaa5CYltBoiurNijzjhAkEAvSjtvFzT9fUeQ7/v2c4MTj2+t7slzl+ptRnDL3RsJvZ++Mnjbk+BXDGyoj5ntumgotXzszjpPTdlKMlDULMQoQJAPbcAnCPguuOyR9iXqq/FEtfkBxgJafAZ5yEoJNagffnlHj7ohr5CW+M+uCe5XyBzCGBCuGiNVhx8pHClFaYKgQJAePob06cO8RzLtotaqQccfW71G2rgnpwcJoU4Z60lP0RW+9JiIzve3nknK5KtIDIWG7QEMciBqsQH9/DeYSE5PA==");
        boolean verify = RSAUtils.verify(treeMap, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBa61x2SdDcbC9fJwSaUePuXORrM7F0jyGt3Q5uu1acbgn7nhWNa7UMgHUymHRmIwv2fcAsH8xh/Lz0APXC1Ru1WJwTsD5e/RrD2UZ8B+Dz1yYPcZnoEoUjICIrUHVHV1WYdnq6XPHtbisWkyD+yzvKgE9V1Tuq93o5P6b38dNNQIDAQAB", sign);
        System.err.println(verify);

    }

    private static void testRas2() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        BlockCompanyMapper blockCompanyMapper = (BlockCompanyMapper) context.getBean("blockCompanyMapper");
        List<Long> userIdList = new ArrayList<Long>();
        userIdList.add(61L);
        userIdList.add(62L);
        userIdList.add(63L);
        userIdList.add(64L);
        userIdList.add(65L);
        userIdList.add(66L);
        for (Long aLong : userIdList) {
            BlockCompany blockCompany = new BlockCompany();
            blockCompany.setEmployeeId(aLong);
            blockCompany = blockCompanyMapper.selectBlockCompanyByIndex(blockCompany);
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put("name", "张" + aLong);

            String sign = RSAUtils.sign(treeMap, blockCompany.getCompanyPrivateKey());
            boolean verify = RSAUtils.verify(treeMap, blockCompany.getCompanyPublicKey(), sign);
            System.err.println(aLong + ":" + verify);
        }
    }
}
