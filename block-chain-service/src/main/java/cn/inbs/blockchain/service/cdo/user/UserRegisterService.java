package cn.inbs.blockchain.service.cdo.user;

import cn.inbs.blockchain.common.constants.CommonConfigPerpertyConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.utils.*;
import cn.inbs.blockchain.common.utils.valuecode.CheckValueCodeUtils;
import cn.inbs.blockchain.common.utils.valuecode.ValueCodeEnums;
import cn.inbs.blockchain.service.cdo.BaseCdoService;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.cdo.user
 * @ClassName:
 * @Date: 2018年05月11-17:10
 * @Author: createBy:zhangmingyang
 **/
public class UserRegisterService extends BaseCdoService {
    private static Logger logger = LoggerFactory.getLogger(UserRegisterService.class);

    private static final int USER_REGISTER_ERROR_CODE = -1;

    /**
     * 请求参数
     */
    private static final String ID_MOBILE = "strMobile";//手机号
    private static final String ID_MESSVER = "strMessVer";//验证码
    private static final String ID_MECHANISM = "strMechanism";//公司类型
    private static final String ID_PASSWD = "strPasswd";//用户密码
    private static final String ID_MAIL = "strMail";//用户邮箱

    /**
     * 插入参数
     */
    private static final String STR_USER_NAME = "strName";//用户名
    private static final String ROLE_CODE_KEY = "strRoleCode";//角色取值key
    private static final String EMPLOYEE_TYPE = "employeeType";//公司区块链ID


    public static final int COMPANY_TYPE_XD = 0;//信贷机构
    public static final int COMPANY_TYPE_ZJ = 1;//资金机构

    @Override
    protected Return doHandleTrans(CDO cdoRequest, CDO cdoResponse) throws Exception {
        String invokeMethodName = getInvokeMethodName(null, cdoRequest);

        Method method = this.getClass().getDeclaredMethod(invokeMethodName, CDO.class, CDO.class);
        return (Return) method.invoke(this, cdoRequest, cdoResponse);
    }

    /**
     * 用户注册
     *
     * @param cdoRequest
     * @param cdoResponse
     * @return
     */
    public Return userRegister(CDO cdoRequest, CDO cdoResponse) {
        //校验手机号
        String mobile;
        if (!cdoRequest.exists(ID_MOBILE)) {
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "请给定手机号");
        } else {
            mobile = cdoRequest.getStringValue(ID_MOBILE).trim();
            try {
                MobileUtils.checkMobile(mobile);
            } catch (Exception e) {
                return Return.valueOf(USER_REGISTER_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验验证码
        String code;
        if (!cdoRequest.exists(ID_MESSVER)) {
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "请给定验证码");
        } else {
            code = cdoRequest.getStringValue(ID_MESSVER).trim();
            try {
                MobileUtils.checkVerificationCode2Cache(mobile, code, CommonConfigPerpertyConstants.SEND_VERIFICATION_CODE_MESSAGE_TEMPLATE_KEY);
            } catch (Exception e) {
                return Return.valueOf(USER_REGISTER_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验公司类型
        String companyType;
        if (!cdoRequest.exists(ID_MECHANISM)) {
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "请给定公司类型");
        } else {
            companyType = cdoRequest.getStringValue(ID_MECHANISM).trim();

            //公司类型值允许为{0或者1}
            try {
                CheckValueCodeUtils.checkValueCode(false, companyType, ValueCodeEnums.COMPANY_TYPE);
            } catch (Exception e) {
                return Return.valueOf(USER_REGISTER_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验用户密码
        String password;
        if (!cdoRequest.exists(ID_PASSWD)) {
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "请输入密码");
        } else {
            password = cdoRequest.getStringValue(ID_PASSWD).trim();
            try {
                PasswordUtils.checkPassword(password);
            } catch (Exception e) {
                return Return.valueOf(USER_REGISTER_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        //校验用户邮箱
        String email;
        if (!cdoRequest.exists(ID_MAIL)) {
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "请输入邮箱");
        } else {
            email = cdoRequest.getStringValue(ID_MAIL).trim();
            try {
                EmailUtils.checkEmail(email);
            } catch (Exception e) {
                return Return.valueOf(USER_REGISTER_ERROR_CODE, ExceptionUtils.getResultMessage(e));
            }
        }

        cdoRequest.setStringValue(STR_USER_NAME, mobile);//用户名目前设置为手机号
        cdoRequest.setStringValue(ROLE_CODE_KEY, getRole(companyType.trim()));//设置用户角色
        cdoRequest.setStringValue(CompanyConstants.COMPANY_BLOCK_ID, BlockUtils.createCompanyBlockId(mobile));//公司区块链ID
        cdoRequest.setStringValue(EMPLOYEE_TYPE, getEmployeeType(companyType.trim()));//

        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            cdoRequest.setStringValue(CompanyConstants.COMPANY_PRIVATE_KEY, RSAUtils.getPrivateKey(keyMap));//用户私钥
            cdoRequest.setStringValue(CompanyConstants.COMPANY_PUBLIC_KEY, RSAUtils.getPublicKey(keyMap));//用户公钥
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("用户注册创建秘钥对失败,cause by:", e);
            }
            return Return.valueOf(USER_REGISTER_ERROR_CODE, "用户注册失败");
        }

        //执行数据库操作
        return this.servicePlugin.handleDataTrans(cdoRequest, cdoResponse);
    }


    /**
     * 根据机构类型分配角色
     *
     * @param companyType
     * @return
     */
    private String getRole(String companyType) {
        String companyRole;
        if (CompanyConstants.COMPANY_TYPE_ZJ.equals(companyType)) {
            companyRole = CompanyConstants.COMPANY_ROLE_ZJ;
        } else if ("0".equals(companyType)) {
            companyRole = CompanyConstants.COMPANY_ROLE_ZC;
        } else {
            companyRole = CompanyConstants.COMPANY_ROLE_BS;
        }
        return companyRole;
    }

    /**
     * 根据机构类型分配角色
     *
     * @param companyType
     * @return
     */
    private String getEmployeeType(String companyType) {
        String companyRole;
        if (CompanyConstants.COMPANY_TYPE_ZJ.equals(companyType)) {
            companyRole = "2";//资金
        } else if ("0".equals(companyType)) {
            companyRole = "1";//资产
        } else {
            companyRole = "3";//背书
        }
        return companyRole;
    }
}
