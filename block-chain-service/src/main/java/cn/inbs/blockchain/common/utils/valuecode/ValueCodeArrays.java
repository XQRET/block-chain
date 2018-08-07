package cn.inbs.blockchain.common.utils.valuecode;


/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.utils.valuecode
 * @ClassName:
 * @Date: 2018年05月21-12:21
 * @Author: createBy:zhangmingyang
 **/
public class ValueCodeArrays {

    public static final String CONTRACT_STATUS_STR = "10,20,21,30,40";
    public static final String[] CONTRACT_STATUS = dataToArray(CONTRACT_STATUS_STR);


    /**
     * 公司类型(0-资产端,1-资金端,2-背书端)
     */
    public static final String COMPANY_TYPE_STR = "0,1,2";
    public static final String[] COMPANY_TYPE = dataToArray(COMPANY_TYPE_STR);


    /**
     * 0-未认证,1-待审核,2-审核通过,3-审核未通过
     */
    public static final String COMPANY_STATUS_STR = "0,1,2,3";
    public static final String[] COMPANY_STATUS = dataToArray(COMPANY_STATUS_STR);

    /**
     * 关联状态(0-待关联,1-关联成功,2-关联拒绝)
     */
    public static final String RELATION_STATUS_STR = "0,1,2";
    public static final String[] RELATION_STATUS = dataToArray(RELATION_STATUS_STR);

    /**
     * 合约执行流水状态 0-正常还款流水,1-逾期还款流水
     */
    public static final String REQ_CONTRACT_EXECUTE_SERIAL_STATUS_STR = "0,1";
    public static final String[] REQ_CONTRACT_EXECUTE_SERIAL_STATUS = dataToArray(REQ_CONTRACT_EXECUTE_SERIAL_STATUS_STR);

    /**
     * 合约类型 0-长租;1-澳洲,2-izu
     */
    public static final String CONTRACT_TYPE_STR = "0,1,2";
    public static final String[] CONTRACT_TYPE = dataToArray(CONTRACT_TYPE_STR);

    /**
     * 合约支付方式  1 等额本息、2 先息后本
     */
    public static final String CONTRACT_REPAYMENT_WAY_STR = "1,2";
    public static final String[] CONTRACT_REPAYMENT_WAY = dataToArray(CONTRACT_REPAYMENT_WAY_STR);


    /**
     * 合约产品类型:0-固收,1-非固收
     */
    public static final String CONTRACT_PRODUCT_TYPE_STR = "0,1";
    public static final String[] CONTRACT_PRODUCT_TYPE = dataToArray(CONTRACT_PRODUCT_TYPE_STR);

    /**
     * 合约募集状态,1-待募集,2-募集中,3-募集完成
     */
    public static final String RAISE_STATUS_STR = "1,2,3";
    public static final String[] RAISE_STATUS = dataToArray(RAISE_STATUS_STR);

    /**
     * 销售状态,1-待预售,2-预售中,3-预售完成
     */
    public static final String PRODUCT_STATUS_STR = "1,2,3";
    public static final String[] PRODUCT_STATUS = dataToArray(PRODUCT_STATUS_STR);

    private static String[] dataToArray(String dataStr) {
        return dataStr.split(",");
    }
}
