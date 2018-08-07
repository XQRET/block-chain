package cn.inbs.blockchain;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.HttpClientUtil;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.controller.privatechain.ExecuteContractController;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Description: 合约执行
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年06月05-16:08
 * @Author: createBy:zhangmingyang
 **/
public class TestContractExecute extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(TestContractExecute.class);

    public static void main(String[] args) throws Exception {
        testCheck();
        executeContract();
    }

    /**
     * 合约检测
     *
     * @throws Exception
     */
    private static void testCheck() throws Exception {
        HttpResponse httpResponse = HttpClientUtil.doPostRequest(YU_MING + "/contract/executeCheckContract.do", getCheckParam());
        String result = EntityUtils.toString(httpResponse.getEntity());
        logger.info("返回结果{}", result);

        JSONObject bigResultJson = JSONObject.parseObject(result);
        String resultCode = bigResultJson.getString("resultCode");
        if (!"0".equals(resultCode)) {
            logger.error("请求失败:{}", bigResultJson.getString("resultMessage"));
        } else {
            String resultDate = bigResultJson.getString("resultDate");
            JSONObject resultDateJsonObject = JSONObject.parseObject(resultDate);

            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            String companyBlockId = resultDateJsonObject.getString(ExecuteContractController.CHECK_RESULT_COMPANY_BLOCK_ID);
            String companyName = resultDateJsonObject.getString(ExecuteContractController.CHECK_RESULT_COMPANY_NAME);
            String isZj = resultDateJsonObject.getString(ExecuteContractController.CHECK_RESULT_IS_RELATION_ZJ);
            String sign = resultDateJsonObject.getString(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);

            treeMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_BLOCK_ID, companyBlockId);
            treeMap.put(ExecuteContractController.CHECK_RESULT_COMPANY_NAME, companyName);
            treeMap.put(ExecuteContractController.CHECK_RESULT_IS_RELATION_ZJ, isZj);
            boolean verify = RSAUtils.verify(treeMap,
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvgDnBAq1VRxYrYxhC0fhEWChKwiAyrbFJNFK5DVYiHZ0IIkB2y0BSFmFBjS68ll2NJK54nVG0LC4B/sGxIkff+ToT5I0EKHr0ZGTJXMUj+UrRFKaMqxww8u24W/GyHMko9HvShjr8Yb968p8+owDHJH60SdV0ivlGGqJqx3RgxwIDAQAB",
                    sign);

            if (verify) {
                logger.info("面签通过,响应数据如下:\n======================公司区块链ID:{};\n======================公司名称:{};\n======================是否关联资金:{};\n",
                        companyBlockId,
                        companyName,
                        isZj);
            } else {
                logger.error("验签失败");
            }
        }
    }

    /**
     * 合约执行放款
     *
     * @throws Exception
     */
    private static void executeContract() throws Exception {
        HttpResponse httpResponse = HttpClientUtil.doPostRequest("http://localhost:8081/block-chain-service/contract/executeContract.do", getCheckParam());
        String result = EntityUtils.toString(httpResponse.getEntity());
        logger.info("返回结果{}", result);

        JSONObject bigResultJson = JSONObject.parseObject(result);
        String resultCode = bigResultJson.getString("resultCode");
        if (!"0".equals(resultCode)) {
            logger.error("请求失败:{}", bigResultJson.getString("resultMessage"));
        } else {
            String resultDate = bigResultJson.getString("resultDate");
            JSONObject resultDateJsonObject = JSONObject.parseObject(resultDate);

            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            String remark = resultDateJsonObject.getString(ExecuteContractController.EXECUTE_REMARK);
            String sign = resultDateJsonObject.getString(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);
            treeMap.put(ExecuteContractController.EXECUTE_REMARK, remark);

            boolean verify = RSAUtils.verify(treeMap,
                    "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvgDnBAq1VRxYrYxhC0fhEWChKwiAyrbFJNFK5DVYiHZ0IIkB2y0BSFmFBjS68ll2NJK54nVG0LC4B/sGxIkff+ToT5I0EKHr0ZGTJXMUj+UrRFKaMqxww8u24W/GyHMko9HvShjr8Yb968p8+owDHJH60SdV0ivlGGqJqx3RgxwIDAQAB",
                    sign);


            if (verify) {
                logger.info("面签通过,响应数据如下:\n======================备注:{};",
                        remark);
            } else {
                logger.error("验签失败");
            }
        }
    }

    /**
     * 请求参数
     *
     * @return
     * @throws Exception
     */
    private static List<NameValuePair> getCheckParam() throws Exception {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put(ExecuteContractController.COMPANY_BLOCK_ID, "82Swea6ThvP9PfDSwsgD5Thea6Th82656545sgD5");//公司区块ID
        treeMap.put(ExecuteContractController.CONTRACT_BLOCK_ID, "91t6rThsgD5ea6CCt6rea6hGii91656545SwiiTh");//合约区块ID
        treeMap.put(ExecuteContractController.CONTRACT_ID, "tCet6hh68r");//合约ID

        //签名数据
        String sign = RSAUtils.sign(treeMap, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJOf9LCBf4/qTnJ4hwrtO76/q8Mz/0ikRrXpByqGyEfImZKhEyPCZNLXTOXjFVkz2C8EaAaYFSsvvTpLczqFlAWgxi07Vf5EZYduiiz/+g0o2ZlwDuJe5oVca5kJCFNFIIJ7b5FnI2vrl9W/9+57x0nTeBe4sg99JMxEQ+WKPPP9AgMBAAECgYBrZ0f+Ful5Ck5yyC/wCjOajxbnWXBVKHftp5G4JgGXAGKDJL+pF6iFIXFoG/QPR0H0yy7oBUP5fK0euYAnRsIuzq160diN5NxF1jAsOnKAAv0cqNQLbZIE196hoPVPohvJmFMGd+tyIg3+Xcqw5VttvZvwLeSQf4hSMOIKUy01IQJBAM5gkLOFx/A4drfb2jONEq8vUVaaVtpwJEkfZ1+x2ff9vrk6EUT34zrZh8gIFHL1ZWqjpA6gAIU2CxttFeYOBukCQQC3HuuahxfPIYYGK052YMGOQpH89qakzY8dgydnSzpaTGGqhz6w24Is03TLAjnkgIu2kdAPhPrqKX43saVKFT/1AkAmYvPHUJT166q5XB1kBVN6XlTExQRusPku2LaDqDMg24PENDbOG0Waxqdo34feMiLhCPsU9VHjdT/vpAS0u9sxAkBXnRekq5YcqDFzqiy0Pr7czcUDfw9kMmWS/TlRFpEouvrPmD3KaHvz7ogmyT3hsg8mnsUpZ7qVZpzZEMnAlEIxAkEAtG6pFOVFQc5FpO1Qe9J2gmwjzxSUKY/00knkXnmpatRgvskrsJA+DsbtDP0QjUadch03t3dUEDwzC22GeYqNIA==");

        param.add(new BasicNameValuePair(ExecuteContractController.COMPANY_BLOCK_ID, "82Swea6ThvP9PfDSwsgD5Thea6Th82656545sgD5"));
        param.add(new BasicNameValuePair(ExecuteContractController.CONTRACT_BLOCK_ID, "91t6rThsgD5ea6CCt6rea6hGii91656545SwiiTh"));
        param.add(new BasicNameValuePair(ExecuteContractController.CONTRACT_ID, "tCet6hh68r"));
        param.add(new BasicNameValuePair(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, sign));
        return param;
    }
}
