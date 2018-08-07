package cn.inbs.blockchain.common.constants;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.constants
 * @ClassName:
 * @Date: 2018年05月17-17:51
 * @Author: createBy:zhangmingyang
 **/
public class CompanyConstants {
    public static final String COMPANY_BLOCK_ID = "companyBlockId";
    public static final String COMPANY_STATUS = "companyStatus";//公司状态
    public static final String COMPANY_TYPE = "companyType";//公司类型
    public static final String TRIGGER_COMPANY_BLOCK_ID = "triggerCompanyBlockId";//合约触发机构

    public static final String ZC_COMPANY_BLOCK_ID = "zcCompanyBlockId";//资产端区块ID
    public static final String ZJ_COMPANY_BLOCK_ID = "zjCompanyBlockId";//资金端区块ID
    public static final String RELATION_STATUS = "relationStatus";//关联状态


    public static final String ID_CARD_FILE_PTOTO = "idCardPhotoFile";//身份证图片
    public static final String COMPANY_FILE_PHOTO = "companyPhotoFile";//营业执照图片
    public static final String COMPANY_LOGO_PHOTO = "companyLogoFile";//营业执照图片
    public static final String USER_ID = "lEmployeeId";//用户id
    public static final String COMPANY_NAME = "name";//公司名称
    public static final String COMPANY_PROVINCE_NAME = "provinceName";//公司-省
    public static final String COMPANY_CITY_NAME = "cityName";//公司-市
    public static final String COMPANY_LINKMAN = "linkman";//公司-联系人姓名
    public static final String COMPANY_PHONE = "phone";//公司-联系人电话


    /**
     * 公司类型(0-资产端,1-资金端)
     */
    public static final String COMPANY_TYPE_ZC = "0";//资产端
    public static final String COMPANY_TYPE_ZJ = "1";//资金端

    /**
     * 公司状态(0-未认证,1-待审核,2-审核通过,3-审核未通过)
     */
    public static final String COMPANY_STATUS_0 = "0";
    public static final String COMPANY_STATUS_1 = "1";
    public static final String COMPANY_STATUS_2 = "2";
    public static final String COMPANY_STATUS_3 = "3";
    public static final String COMPANY_STATUS_4 = "4";

    /**
     * 公司关联状态(关联状态(0-待关联,1-关联成功,2-关联拒绝))
     */
    public static final String COMPANY_RELATION_STATUS_0 = "0";
    public static final String COMPANY_RELATION_STATUS_1 = "1";
    public static final String COMPANY_RELATION_STATUS_2 = "2";

    /**
     * 公司角色分配
     */
    public static final String COMPANY_ROLE_ZJ = "roleZJ";//资金端角色
    public static final String COMPANY_ROLE_ZC = "roleXD";//资产端角色
    public static final String COMPANY_ROLE_BS = "roleBS";//背书端角色

    /**
     * 公司秘钥对
     */
    public static final String COMPANY_PRIVATE_KEY = "privateKey";//私钥
    public static final String COMPANY_PUBLIC_KEY = "publicKey";//私钥


    /**
     * 图片类型,房屋证书
     */
    public static final String COMPANY_PHOTO_TYPE_CERTIFICATE = "1";
    public static final String COMPANY_PHOTO_TYPE_LOGO = "2";

    /**
     * 机构审核下拉列表选项值 机构类型:全部
     */
    public static final String AGENCY_TYPE_ALL = "0";

    /**
     * 机构审核下拉列表选项值 机构状态:全部
     */
    public static final String AGENCY_STATUS_ALL = "0";


    /**
     * cookie相关
     */
    public static final String COOKIE_NAME_COMPANY_BLOCK_ID = "companyBlockIdCookie";//公司区块ID
    public static final String COOKIE_NAME_COMPANY_TYPE = "companyTypeCookie";//公司类型


    public static final String SYSTEM_COMPANY_ID = "88888888";//系统区块ID
    public static final String SYSTEM_COMPANY_NAME = "星桥数据";//系统区块ID
}
