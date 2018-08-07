package cn.inbs.blockchain;

import cn.inbs.blockchain.common.constants.ContractConstants;
import cn.inbs.blockchain.common.utils.HttpClientUtil;
import cn.inbs.blockchain.common.utils.RSAUtils;
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
 * @Description: 合约报备
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年05月15-18:05
 * @Author: createBy:zhangmingyang
 **/
public class TestHttpClient extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(TestHttpClient.class);


    public static void main(String[] args) throws Exception {
        for (int i = 4; i < 5; i++) {
            //发送http请求
            HttpResponse httpResponse = HttpClientUtil.doPostRequest(YU_MING + "/contract/registerContract.do", getPostParam("李四" + i, "不动产房屋OS9969327" + i));

            //处理返回参数
            String result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("注册返回结果{}", result);

            //将请求返回数据转换为json
            JSONObject jsonObject = JSONObject.parseObject(result);

            //获取返回码(0-成功,反之失败)
            String resultCode = jsonObject.getString("resultCode");
            if (!"0".equals(resultCode)) {
                logger.error("请求失败:{}", jsonObject.getString("resultMessage"));
            } else {
                //获取结果数据
                String resultDate = jsonObject.getString("resultDate");
                JSONObject resultDateJsonObject = JSONObject.parseObject(resultDate);

                //执行数据验签
                TreeMap<String, String> treeMap = new TreeMap<String, String>();
                String contractBlockId = resultDateJsonObject.getString(ContractConstants.CONTRACT_BLOCK_ID);//合约区块链ID
                String remark = resultDateJsonObject.getString(ContractConstants.COTRACT_REMARK);//合约概要
                String contractId = resultDateJsonObject.getString(ContractConstants.COTRACT_ID);//合约ID
                String registerResultSign = resultDateJsonObject.getString(ContractConstants.REGISTER_CONTRACT_SIGN_KEY);//签名数据
                treeMap.put(ContractConstants.CONTRACT_BLOCK_ID, contractBlockId);
                treeMap.put(ContractConstants.COTRACT_REMARK, remark);
                treeMap.put(ContractConstants.COTRACT_ID, contractId);
                boolean verify = RSAUtils.verify(treeMap,
                        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvgDnBAq1VRxYrYxhC0fhEWChKwiAyrbFJNFK5DVYiHZ0IIkB2y0BSFmFBjS68ll2NJK54nVG0LC4B/sGxIkff+ToT5I0EKHr0ZGTJXMUj+UrRFKaMqxww8u24W/GyHMko9HvShjr8Yb968p8+owDHJH60SdV0ivlGGqJqx3RgxwIDAQAB",
                        registerResultSign);

                if (verify) {
                    logger.info("面签通过,响应数据如下:\n======================合约区块链ID:{};\n======================合约概要:{};\n======================合约ID:{};\n",
                            contractBlockId,
                            remark,
                            contractId);
                }
            }
        }

    }

    /**
     * 组装请求参数
     *
     * @return
     * @throws Exception
     */
    private static List<NameValuePair> getPostParam(String name, String houseCode) throws Exception {
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put(ContractConstants.REGISTER, "资产李四公司");//登记人
        treeMap.put(ContractConstants.SIGNER, name);//签订人
        treeMap.put(ContractConstants.INTEREST_RATE, "0.03");//利率
        treeMap.put(ContractConstants.REPAYMENT_WAY, "1");//支付方式
        treeMap.put(ContractConstants.AMOUNT, "500.00");//金额
        treeMap.put(ContractConstants.TERM, "12");//期数
        treeMap.put(ContractConstants.BANK, "工商银行");//资金方银行
        treeMap.put(ContractConstants.HOUSE_INFO, "深圳南山科技园铜鼓路168号大冲城市花园A栋8091室1厅1厨1卫1阳台|广东省-深圳市-大冲城市花园-1室1厅1厨1卫1阳台");//房屋信息
        treeMap.put(ContractConstants.HOUSE_CODE, houseCode);//房产编号
        treeMap.put(ContractConstants.COMPANY_BLOCK_ID, "82sgD5hGThiihGSwiiCCea6sgD5656545Swea6vP");//公司区块ID
        treeMap.put(ContractConstants.PERSON_INFO_URL, "http://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/getBaseInfoToReport.do?id=142");//个人报告h5
        treeMap.put(ContractConstants.HOUSE_INFO_URL, "http://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/showHouseDetail.do?id=142");//房产信息h4
        treeMap.put(ContractConstants.DOWNLOAD_FILE_INFO_URL, "https://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/showContractDownload.do?id=142");//文件下载
        treeMap.put(ContractConstants.SIGNER_IDNO, "429001199408042815"); //身份证号
        treeMap.put(ContractConstants.SIGNER_OVERDUE_AMOUNT, "500.12");//平台逾期金额
        treeMap.put(ContractConstants.SIGNER_OVERDUE_NUM, "1");//逾期次数
        treeMap.put(ContractConstants.SIGNER_CREDIT_OVERDUE_NUM, "1");//征信逾期次数
        treeMap.put(ContractConstants.SIGNER_CREDIT_LOAN_NUM, "1");  //征信借款次数
        treeMap.put("houseImgUrls", "eeeeee");  //房屋信息图片
        treeMap.put("contractType", "0"); //合约类型
        treeMap.put("houseAddress", "深圳福田"); //房屋地址
        treeMap.put("houseApartments", "别墅"); //房屋类型
        treeMap.put("houseType", "1室两厅"); //房屋户型
        treeMap.put("houseSize", "100"); //房屋大小
        treeMap.put("houseTotalAmount", "1000"); //房屋总价
        treeMap.put("numberOfHairstyles", "12"); //房屋发型数量

        //签名数据
        String sign = RSAUtils.sign(treeMap, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI47aSoR0AoX7tt56/BU/Pk8HXqsCTjyta1rYKO49uWN/yAr55odPWX0oBcJPle1aNheoE7+ntTXyWegcApqmQLgPhLU9Mbxzm7iCcmJhjlb4P/LeJjPDEWfz1qUb8YhWIf6PmcRNCi0vdSUwiOBv1w5luq4YUvg5/yiISKIFPGdAgMBAAECgYB3+VNHAkl7Ch8YEOrdCcNttLClFlKdL9Xh145n7BCPJ8zJZV9yFzIAO22CoPBuaX5r7L3KhGqT3QVtnN6FWs9/ZJrLVFiCfQlFUctdioOvA52kwJJA6EAekFw27mJB5r7x5cv0/SEhpoU2H4BUXJle3owMnBmaqONVK6LogYmxwQJBAMC9vQxUGpdk5pBLThbREJZseZzUORahAQLe39mu7csv/Vx4TEMvIRPvXQBbYswIVZD/T1wEMlIANY9YWvkDuA0CQQC86dwBMKTFFybq5x8OzfSIlXGDeHrrfQFS9RX2mhxreZyFP9VKRmewL7LnFXCPDFyfQIlGyGJ6f0IdvGjmx6vRAkEApLO+z8Ey8/pKODXRphfDR/esOIL1wcuVtKFFwpwEIiSBKjq2WJb2ejWKUJrHIzlf/Ubexu/jHEWHf1Xub2ppJQJAHRW8Q4qjs5xu53eR9ouK/IRVp7Ii6qKiU41cRk1P1tQwXuuHARp52ExftOg+Hif/Ep2zBOzRDJ8PJNjfJbjUIQJAeagFpQOREnyCWJwLAcXOZU78EGE5q60KeKO37753JVZH797TZ9oTIZXg94n1uu4evG2OjBQs5zEI7m1Q5idRUg==");

        nameValuePairList.add(new BasicNameValuePair(ContractConstants.REGISTER_CONTRACT_SIGN_KEY, sign));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.REGISTER, "资产李四公司"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER, name));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.INTEREST_RATE, "0.03"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.REPAYMENT_WAY, "1"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.AMOUNT, "500.00"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.TERM, "12"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.BANK, "工商银行"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.HOUSE_INFO, "深圳南山科技园铜鼓路168号大冲城市花园A栋8091室1厅1厨1卫1阳台|广东省-深圳市-大冲城市花园-1室1厅1厨1卫1阳台"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.HOUSE_CODE, houseCode));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.COMPANY_BLOCK_ID, "82sgD5hGThiihGSwiiCCea6sgD5656545Swea6vP"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.PERSON_INFO_URL, "http://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/getBaseInfoToReport.do?id=142"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.HOUSE_INFO_URL, "http://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/showHouseDetail.do?id=142"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.DOWNLOAD_FILE_INFO_URL, "https://cbusiness.xianjindaishu.com/hengxin_credit-person/landlord/preLoan/showContractDownload.do?id=142"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER_IDNO, "429001199408042815"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER_OVERDUE_AMOUNT, "500.12"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER_OVERDUE_NUM, "1"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER_CREDIT_OVERDUE_NUM, "1"));
        nameValuePairList.add(new BasicNameValuePair(ContractConstants.SIGNER_CREDIT_LOAN_NUM, "1"));
        nameValuePairList.add(new BasicNameValuePair("houseImgUrls", "eeeeee"));
        nameValuePairList.add(new BasicNameValuePair("contractType", "0"));
        nameValuePairList.add(new BasicNameValuePair("houseAddress", "深圳福田"));
        nameValuePairList.add(new BasicNameValuePair("houseApartments", "别墅"));
        nameValuePairList.add(new BasicNameValuePair("houseType", "1室两厅"));
        nameValuePairList.add(new BasicNameValuePair("houseSize", "100"));
        nameValuePairList.add(new BasicNameValuePair("houseTotalAmount", "1000"));
        nameValuePairList.add(new BasicNameValuePair("numberOfHairstyles", "12"));


        return nameValuePairList;
    }
}
