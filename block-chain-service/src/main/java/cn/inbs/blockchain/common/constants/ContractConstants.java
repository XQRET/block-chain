package cn.inbs.blockchain.common.constants;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.constants
 * @ClassName:
 * @Date: 2018年05月15-12:02
 * @Author: createBy:zhangmingyang
 **/
public class ContractConstants {
    public static final String REGISTER_CONTRACT_SIGN_KEY = "sign";//签名key
    public static final String REGISTER = "register";//登记方
    public static final String SIGNER = "signer";//合约签订人
    public static final String INTEREST_RATE = "interestRate";//+(利率)
    public static final String REPAYMENT_WAY = "repaymentWay";//还款方式(取值：1 等额本息、2 先息后本)
    public static final String AMOUNT = "amount";//合约额度(单位:元)
    public static final String TERM = "term";//合约期数(单位:月)
    public static final String BANK = "bank";//资金银行
    public static final String HOUSE_INFO = "houseInfo";//房产信息
    public static final String HOUSE_CODE = "houseCode";//房产编号
    public static final String COMPANY_BLOCK_ID = "companyBlockId";//公司区块链ID
    public static final String HOUSE_INFO_URL = "houseInfoUrl";//获取房屋信息URl
    public static final String PERSON_INFO_URL = "personInfoUrl";//获取个人信息URL
    public static final String DOWNLOAD_FILE_INFO_URL = "downloadFileInfoUrl";//下载文件URL
    public static final String REGIST_DATE = "registDate"; //注册日期
    public static final String IMPORT_STATUS = "importStatus"; //导入状态
    public static final String EMPLOYEE_ID = "employeeId";//公司区块链ID
    public static final String SIGNER_IDNO = "idNo"; //身份证
    public static final String SIGNER_PHONE_NUMBER = "phoneNumber"; //手机号码
    public static final String IMPORT_UNIQUE_SIGN = "importUniqueSign"; //导入唯一标示


    public static final String SIGNER_OVERDUE_AMOUNT = "overdueAmount"; //平台逾期金额
    public static final String SIGNER_OVERDUE_NUM = "overdueNum"; //平台逾期次数
    public static final String SIGNER_CREDIT_OVERDUE_NUM = "creditOverdueNum"; //征信逾期次数
    public static final String SIGNER_CREDIT_LOAN_NUM = "creditLoanNum"; //征信借款次数
    public static final String SIGNER_HOUSE_IMG_URLS = "houseImgUrls"; //房源图片
    public static final String SIGNER_CONTRACT_TYPE = "contractType"; //合约类型 (0-长租,1-澳洲)
    public static final String SIGNER_HOUSE_ADDRESS = "houseAddress"; //房屋地址
    public static final String SIGNER_HOUSE_APARTMENTS = "houseApartments"; //房产类型
    public static final String SIGNER_HOUSE_TYPE = "houseType"; //户型
    public static final String SIGNER_HOUSE_SIZE = "houseSize"; //房屋大小
    public static final String SIGNER_HOUSE_TOTAL_AMOUNT = "houseTotalAmount"; //房屋总价值
    public static final String SIGNER_NUMBER_OF_HAIRSTYLES = "numberOfHairstyles"; //发型数量

    public static final String CONTRACT_BLOCK_ID = "contractBlockId";//合约区块ID
    public static final String COTRACT_ID = "contractId";//
    public static final String COTRACT_REMARK = "contractRemark";//

    public static final String H5_PARAM_KEY_REQUEST_URL = "requestUrl";//
    public static final String H5_REQUEST_IS_THIRD = "isThird";//
    public static final String SYM_EQUAL = "=";//
    public static final String SYM_PARAM_APPEND_SYM = "&";//
    public static final String IMPORT_PLACEHOLDER = "%${$%";

    /**
     * 合约查询分页条件
     */
    public static final String PAGE_CONDITION_REGISTER = "register";//登记人
    public static final String PAGE_CONDITION_SIGNER = "signer";//签订人
    public static final String PAGE_CONDITION_COTRACT_NAME = "contractName";//合同名称
    public static final String PAGE_CONDITION_START_TIME = "startTime";//开始时间
    public static final String PAGE_CONDITION_END_TIME = "stopTime";//结束时间
    public static final String PAGE_CONDITION_CONTRACT_STATUS = "contractStatus";//结束时间
    public static final String PAGE_CONDITION_PAGE_COUNT = "pageCount";//每页显示条数
    public static final String PAGE_CONDITION_PAGE_INDEX = "pageIndex";////第几页
    public static final String PAGE_CONDITION_START_INDEX = "startIndex";////第几页
    public static final String PAGE_CONDITION_IMPORT_TYPE = "importType"; //导入状态
    public static final String PAGE_IS_FUNDS_TRIGGER = "isFundsTrigger"; //是否是资金端查询触发合约列表
    public static final String PAGE_IMPORT_UNIQUE_SIGN = "importUniqueSign"; //导入唯一标示

    /**
     * 合约状态
     */
    public static final String CONTRACT_STATUS_ZC = "10";//注册-待触发
    public static final String CONTRACT_STATUS_CF_PASS = "20";//触发-通过
    public static final String CONTRACT_STATUS_CF_UN_PASS = "21";//触发-拒绝
    public static final String CONTRACT_STATUS_ZX = "30";//执行中
    public static final String CONTRACT_STATUS_ZX_FINISHED = "31";//合约执行完成
    public static final String CONTRACT_STATUS_XH = "40";//销毁


    /**
     * 合约信息URL类型
     */
    public static final String CONTRACT_INFO_PERSON_INFO = "1";//个人报告
    public static final String CONTRACT_INFO_HOUSE_INFO = "2";//房屋信息
    public static final String CONTRACT_INFO_FILE_INFO = "3";//文件下载

    /**
     * 合约导入url生成
     */
    public static final String CONTRACT_IMPORT_PERSON_INFO_URL = "/bangrong/personalData.htm?contractID=";
    public static final String CONTRACT_IMPORT_HOUSE_INFO_URL = "/bangrong/houseData.htm?contractID=";
    public static final String CONTRACT_IMPORT_FILE_INFO_URL = "/contractDeal/contractdownload.htm?contractID=";

    /**
     * 合约导入方式
     */
    public static final String CONTRACT_TYPE_0 = "0";//http接口导入
    public static final String CONTRACT_TYPE_1 = "1";//批量导入
    public static final String CONTRACT_TYPE_2 = "2"; //导入关联完成

    /**
     * 合约类型
     */
    public static final String CONTRACT_LEASE_TYPE_0 = "0";//长租
    public static final String CONTRACT_LEASE_TYPE_1 = "1";//澳洲
    /**
     * 合约执行流水状态 0-正常 1-逾期
     */
    public static final String CONTRACT_SERIAL_STATUS_ZC = "0";
    public static final String CONTRACT_SERIAL_STATUS_YQ = "1";

    /**
     * 房屋信息分割符
     */
    public static final String HOUSE_INFO_SPLIT_SYM = "\\|";

    /**
     * 地区没有合约分布
     */
    public static final int NO_CONTRACT = 0;

}
