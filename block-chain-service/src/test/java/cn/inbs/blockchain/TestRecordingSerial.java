package cn.inbs.blockchain;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.HttpClientUtil;
import cn.inbs.blockchain.common.utils.RSAUtils;
import cn.inbs.blockchain.controller.privatechain.RecordingContractExecuteSerial;
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
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年06月07-15:22
 * @Author: createBy:zhangmingyang
 **/
public class TestRecordingSerial extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(TestRecordingSerial.class);

    public static void main(String[] args) throws Exception {
        HttpResponse httpResponse = HttpClientUtil.doPostRequest(YU_MING + "/contract/recordingContractExecuteSerial.do", getCheckParam());
        String result = EntityUtils.toString(httpResponse.getEntity());
        logger.info("返回结果{}", result);

    }

    private static List<NameValuePair> getCheckParam() throws Exception {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put(RecordingContractExecuteSerial.REQ_COMPANY_BLOCK_ID, "82sgD5SwhGfDea6SwCCiiiisgD582656545ea6ea");
        treeMap.put(RecordingContractExecuteSerial.REQ_CONTRACT_BLOCK_ID, "91iihGea6iivP9PSwCCea6CCsgD591656545Swii");
        treeMap.put(RecordingContractExecuteSerial.REQ_CONTRACT_ID, "85Swegisi6");
        treeMap.put(RecordingContractExecuteSerial.REQ_CONTRACT_SERIAL_STATUS, "0");
        treeMap.put(RecordingContractExecuteSerial.REQ_CONTRACT_TERM_NUM, "2");

        String sign = RSAUtils.sign(treeMap, "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIeKI53196E9nodgwDDNO4fRbfoP2F6OnCXl7/v9PWfSSNTPsNHX+Y+DvTFwxmoFdn675qGIbL+jozl9fQbcaXdFLOU2FcBmonSBads8PNgpbsNwIK1FHQ7KthbCBYBYObDTtVR6/1u0pBA8ZyGHOMYiASjStFqe8lb2cJcBO4VLAgMBAAECgYAsejZJ5adHMDNPdX5cBV0dqqoOPxjfnqi6/wOLi4SVBzqDK0x48AijAbYZ/Un/DJ/f4sVCCMS29nnfqcBXegjZcb9NqOOXx2irjU9WkBZ3B0unZs+QuYvQO0i0W5vdwxfJ8v/GMzb12PchdtcvT+9NRt6a5K91NxatDADHyoATUQJBANCHX2p+u2Byw1+u5gruXOQzWfAVZQqmLL4h7n4tuexZg40u4Pc1u/ARYk1ekIoyQW+cC73rGJ/G2EiXigl6TgcCQQCmZRtsAzEsv7Dev+9d3JL2KO3pMH+dYJodO5MVrYZMAMfMopPDDbliqmkDDDUB5g33v0A6rud/qMRtKlrlzj2dAkBK1kqIDbNxib5UOJCWm7T6sPM2FneMutaT5dmRrxdqmzV7FbfnAIHSRmrhGW8GKcMSKw6LujO8I8C7TKddf2dBAkBsOaLmVAUt99gQaYJE6Gxl0yU64KKAOA7FKLR4Cw8oa/GINc4ptcl4VSJMKypm+7zmAOzDvnMv4xMvdbb9dXE5AkA7DENSAFj92AZrvHNiBYZaCNLfQ2n2WvrgDkpAu2hxo4ZKb2GdNWm2OGrYWBZAVK5EfbA3itMCZ5NDsHlG73Dj");

        param.add(new BasicNameValuePair(RecordingContractExecuteSerial.REQ_COMPANY_BLOCK_ID, "82sgD5SwhGfDea6SwCCiiiisgD582656545ea6ea"));
        param.add(new BasicNameValuePair(RecordingContractExecuteSerial.REQ_CONTRACT_BLOCK_ID, "91iihGea6iivP9PSwCCea6CCsgD591656545Swii"));
        param.add(new BasicNameValuePair(RecordingContractExecuteSerial.REQ_CONTRACT_ID, "85Swegisi6"));
        param.add(new BasicNameValuePair(RecordingContractExecuteSerial.REQ_CONTRACT_SERIAL_STATUS, "0"));
        param.add(new BasicNameValuePair(RecordingContractExecuteSerial.REQ_CONTRACT_TERM_NUM, "2"));
        param.add(new BasicNameValuePair(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, sign));
        return param;

    }
}
