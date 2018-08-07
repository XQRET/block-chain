package cn.inbs.blockchain.plugin.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.inbs.blockchain.common.constants.CommonConstants;
import cn.inbs.blockchain.common.constants.CompanyConstants;
import cn.inbs.blockchain.common.utils.SpringContextUtil;
import cn.inbs.blockchain.dao.company.BlockCompanyMapper;
import cn.inbs.blockchain.dao.po.BlockCompany;
import cn.inbs.blockchain.service.company.IBlockCompanyService;
import com.cdoframework.cdolib.base.DateTime;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.security.DES;
import com.cdoframework.cdolib.servlet.PostfixWebService;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.RedisInstance;
import cn.inbs.blockchain.common.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户登录成功后置方法
 *
 * @author tanglei
 * @date 2017-10-29
 */
public class EmployeePostfixService extends PostfixWebService {
    private static Logger logger = LoggerFactory.getLogger(EmployeePostfixService.class);

    @Resource
    private BlockCompanyMapper blockCompanyMapper;

    @Override
    public void onTransHandled(HttpServletRequest request,
                               HttpServletResponse response,
                               CDO cdoRequest,
                               CDO cdoResponse,
                               Return ret) {
        //设置字符集编码,记录请求日志
        response.setCharacterEncoding(CommonConstants.COMMON_ENCODING);

        if (logger.isInfoEnabled()) {
            logger.info("[EmployeePostfixService.onTransHandled]参数:{}", cdoRequest.toXML());
        }

        if (ret.getCode() == 0) {
            //获取请求服务名称及执行方法名称
            String strServiceName = cdoRequest.getStringValue(Constant.CDO_strServiceName);
            String strTransName = cdoRequest.getStringValue(Constant.CDO_strTransName);

            //登录时做以下操作
            if ("EmployeeService".equalsIgnoreCase(strServiceName)
                    && "doBossLogin".equalsIgnoreCase(strTransName)) {
                //校验获取登录IP
                String strCookieDomain = Utility.getCommonCookieDomain(request);

                //清除cookie
                Utility.clearAllCookies(request, response, strCookieDomain);

                //重新设置用户cookie
                setBossCookie(request, response, cdoRequest, cdoResponse, strCookieDomain);
            }
        }
    }

    /**
     * 登录后设置cookie
     *
     * @param request
     * @param response
     * @param cdoRequest
     * @param cdoResponse
     * @param strCookieDomain
     */
    private void setBossCookie(HttpServletRequest request,
                               HttpServletResponse response,
                               CDO cdoRequest,
                               CDO cdoResponse,
                               String strCookieDomain) {
        //获取用户信息
        CDO cdoUser = cdoResponse.getCDOValue("cdoEmployee");
        if (cdoUser == null) {
            return;
        }

        logger.debug("用户基本信息:{}", cdoUser.toXML());

        String lId = cdoUser.getStringValue("lId");//获取用户ID

        //获取sessionId
        String jsessionId = request.getSession().getId();

        //获取加密key
        DES des = Utility.getDES(request.getSession().getServletContext(), request.getSession(), Constant.COOKIE_DES_KEY);

        //创建用户ID cookie
        Cookie userIdCookie = createUserIdCookie(strCookieDomain, lId);

        //创建token cookie
        Cookie tokenCookie = createTokenCookie(strCookieDomain, cdoUser, lId, jsessionId, des);

        //构造访问时间 cookie
        Cookie timeCookie = createTimeCookie(strCookieDomain);

        //构造角色权限 cookie
        Cookie roleCookie = createRoleCookie(cdoResponse, strCookieDomain, lId, des);

        //创建公司相关信息cookie
        if (!"0".equals(cdoUser.getStringValue("employeeType"))) {
            //清除之前缓存
            Utility.clearCookie(response, request, CompanyConstants.COOKIE_NAME_COMPANY_BLOCK_ID, strCookieDomain);
            Utility.clearCookie(response, request, CompanyConstants.COOKIE_NAME_COMPANY_TYPE, strCookieDomain);

            //查询用户公司基本信息
            BlockCompany blockCompany = new BlockCompany();
            blockCompany.setEmployeeId(Long.valueOf(lId));

            IBlockCompanyService blockCompanyService = (IBlockCompanyService) SpringContextUtil.getBean("blockCompanyService");
            blockCompany = blockCompanyService.queryBlockCompanyByIndex(blockCompany);

            //创建区块ID cookie
            Cookie blockIdCookie = createCompanyCookie(CompanyConstants.COOKIE_NAME_COMPANY_BLOCK_ID, blockCompany.getCompanyBlockId(), strCookieDomain);

            //公司类型 cookie
            Cookie companyTypeCookie = createCompanyCookie(CompanyConstants.COOKIE_NAME_COMPANY_TYPE, blockCompany.getCompanyType(), strCookieDomain);

            //添加Cookie值
            response.addCookie(blockIdCookie);
            response.addCookie(companyTypeCookie);
        }

        //添加Cookie值
        response.addCookie(userIdCookie);
        response.addCookie(tokenCookie);
        response.addCookie(timeCookie);
        response.addCookie(roleCookie);

        //redis存入jsessionId,用于踢人
        RedisInstance.getIns().set(Constant.REDIS_bossJsessionid + lId, jsessionId);
    }

    /**
     * 创建用户ID cookie
     *
     * @param strCookieDomain
     * @param lId
     * @return
     */
    private Cookie createUserIdCookie(String strCookieDomain, String lId) {
        Cookie userIdCookie;
        try {
            userIdCookie = new Cookie(Constant.COOKIE_NAME_EMPLOYEEID, URLEncoder.encode(lId, CommonConstants.COMMON_ENCODING));//用户ID
            userIdCookie.setMaxAge(Constant.COOKIE_MAXAGE);//超时时间
            userIdCookie.setPath(Constant.COOKIE_PATH);//路径
            userIdCookie.setDomain(strCookieDomain);//Ip
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("设置{}失败!", Constant.COOKIE_NAME_EMPLOYEEID, e);
            }
            throw new IllegalArgumentException("设置用户[employeeId]Cookie失败");
        }
        return userIdCookie;
    }


    /**
     * 创建token cookie
     *
     * @param strCookieDomain
     * @param cdoUser
     * @param lId
     * @param jsessionId
     * @param des
     * @return
     */
    private Cookie createTokenCookie(String strCookieDomain,
                                     CDO cdoUser,
                                     String lId,
                                     String jsessionId,
                                     DES des) {
        Cookie cToken;
        try {
            //构造token值[用户id#用户手机号#用户名]
            StringBuilder sb = new StringBuilder();
            sb.append(lId);
            sb.append("#");
            sb.append(cdoUser.getStringValue("strMobile"));
            sb.append("#");
            sb.append(cdoUser.getStringValue("strName"));
            sb.append("#");
            sb.append(jsessionId);
            String strToken = sb.toString();

            //加密转码token值
            String strEncToken = URLEncoder.encode(Utility.DESEncrypt(strToken, des), CommonConstants.COMMON_ENCODING);
            cToken = new Cookie(Constant.COOKIE_NAME_TOKEN, strEncToken);
            cToken.setMaxAge(Constant.COOKIE_MAXAGE);
            cToken.setDomain(strCookieDomain);
            cToken.setPath(Constant.COOKIE_PATH);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("设置:{}失败!", Constant.COOKIE_NAME_TOKEN, e);
            }
            throw new IllegalArgumentException("设置用户[token]Cookie失败");
        }
        return cToken;
    }

    /**
     * 穿件时间cookie
     *
     * @param strCookieDomain
     * @return
     */
    private Cookie createTimeCookie(String strCookieDomain) {
        Cookie cLasttime = new Cookie(Constant.COOKIE_NAME_LASTVISITTIME, String.valueOf(DateTime.now().getTime()));
        cLasttime.setDomain(strCookieDomain);
        cLasttime.setPath(Constant.COOKIE_PATH);
        cLasttime.setMaxAge(Constant.COOKIE_MAXAGE);
        return cLasttime;
    }


    /**
     * 穿件角色cookie
     *
     * @param cdoResponse
     * @param strCookieDomain
     * @param lId
     * @param des
     * @return
     */
    private Cookie createRoleCookie(CDO cdoResponse,
                                    String strCookieDomain,
                                    String lId,
                                    DES des) {
        CDO[] cdosRole = cdoResponse.getCDOArrayValue("cdosRole");
        CDO cdoRole = null;
        if (cdosRole.length > 0) {
            cdoRole = cdosRole[0];
        }

        int nRoleId = 0;
        String strRoleName = "none";
        String strPermission = "none";

        if (null == cdoRole) {
            cdoRole = new CDO();
        }
        if (cdoRole.exists("nId")) {
            nRoleId = cdoRole.getIntegerValue("nId");
        }
        if (cdoRole.exists("strName")) {
            strRoleName = cdoRole.getStringValue("strName");
        }
        if (cdoRole.exists("strPermission")) {
            strPermission = cdoRole.getStringValue("strPermission");
        }

        //把剩下的权限加进来
        for (int i = 1; i < cdosRole.length; i++) {
            strPermission += "|" + cdosRole[i].getStringValue("strPermission");
        }

        //去掉多个角色的重复权限
        String[] permissionArray = strPermission.split("\\|");
        List<String> permissionList = new LinkedList<String>();
        for (int i = 0; i < permissionArray.length; i++) {
            if (!permissionList.contains(permissionArray[i])) {
                permissionList.add(permissionArray[i]);
            }
        }

        Object[] newPermissionArray = permissionList.toArray();
        String strNewPermission = "";
        for (int i = 0; i < newPermissionArray.length; i++) {
            strNewPermission += newPermissionArray[i] + "|";
        }
        strNewPermission = strNewPermission.substring(0, strNewPermission.length() - 1);
        StringBuilder bossStr = new StringBuilder();
        bossStr.append(lId);
        bossStr.append("#");
        bossStr.append(nRoleId);
        bossStr.append("#");
        bossStr.append(strRoleName);
        bossStr.append("#");
        bossStr.append(strNewPermission);
        String strBossToken = bossStr.toString();
        strBossToken = Utility.DESEncrypt(strBossToken, des);

        //创建bosstoken cookie
        Cookie cBossToken;
        try {
            cBossToken = new Cookie(Constant.COOKIE_NAME_BOSSTOKEN, URLEncoder.encode(strBossToken, CommonConstants.COMMON_ENCODING));
            cBossToken.setDomain(strCookieDomain);
            cBossToken.setPath(Constant.COOKIE_PATH);
            cBossToken.setMaxAge(Constant.COOKIE_MAXAGE);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("设置:{}失败!", Constant.COOKIE_NAME_BOSSTOKEN, e);
            }
            throw new IllegalArgumentException("设置用户[bossToken]Cookie失败");
        }
        return cBossToken;
    }

    /**
     * 设置公司相关cookie
     *
     * @param cookieName
     * @param cookieValue
     * @param strCookieDomain
     * @return
     */
    private Cookie createCompanyCookie(String cookieName,
                                       String cookieValue,
                                       String strCookieDomain) {
        Cookie cookie;
        try {
            cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, CommonConstants.COMMON_ENCODING));
            cookie.setDomain(strCookieDomain);
            cookie.setPath(Constant.COOKIE_PATH);
            cookie.setMaxAge(Constant.COOKIE_MAXAGE);
        } catch (UnsupportedEncodingException e) {
            if (logger.isErrorEnabled()) {
                logger.error("设置:{}失败!", cookieName, e);
            }
            throw new IllegalArgumentException("设置用户[" + cookieName + "]Cookie失败");
        }
        return cookie;
    }
}
