package cn.inbs.blockchain.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.security.DES;

import cn.inbs.blockchain.common.utils.Constant;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.utils.Utility;

/**
 * 验证cookie，同时设置cdoLoginer
 *
 * @author tanglei
 * @date 2017-10-30
 */
public class CookieCheckerFilter extends BaseFilter {
    private static Logger logger = LoggerFactory.getLogger(CookieCheckerFilter.class);

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //禁用缓存
        unUseCache(resp);

        //获取路径
        String strPath = req.getServletPath();

        //组装rootPath
        StringBuffer url = req.getRequestURL();
        String rootPath = url.delete(url.length() - req.getRequestURI().length(), url.length())
                .append(req.getServletContext().getContextPath()).toString();

        //获取请求CDO信息
        String strServiceName = null;
        String strTransName = null;
        String strCDORequest = request.getParameter(Constant.CDO_Request);
        if (StringUtils.isNotEmpty(strCDORequest)) {
            CDO cdoRequest = CDO.fromXML(strCDORequest);
            if (cdoRequest.exists(Constant.CDO_strServiceName)) {
                strServiceName = cdoRequest.getStringValue(Constant.CDO_strServiceName);
            }
            if (cdoRequest.exists(Constant.CDO_strTransName)) {
                strTransName = cdoRequest.getStringValue(Constant.CDO_strTransName);
            }
        }

        //后台登陆的时候不拦截
        if (checkIsBossLogin(strServiceName, strTransName)) {
            chain.doFilter(request, response);
            return;
        }

        //除了登陆，其他的都验证cookie
        if (checkOpenPageCookie(strPath)) {
            if (this.isValidCookie(req, resp) != 1) {
                //Cookie无效转至重定向页面
                gotoLogin(req, resp, rootPath);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 禁用缓存
     *
     * @param httpServletResponse
     */
    private void unUseCache(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("cache-control", "no-cache");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
    }

    private static final String UN_CHECK_SERVICE_EMPLOYEE_SERVICE = "EmployeeService";
    private static final String UN_CHECK_SERVICE_USER_REGISTER_SERVICE = "UserRegisterService";
    private static final String UN_CHECK_SERVICE＿USER＿RESET＿PASSWORD_SERVICE = "UserPasswordService";

    private static final String UN_CHECK_TRANS_BOSS_BEFORE = "bossBeforeLogin";
    private static final String UN_CHECK_TRANS_DO_BOSS = "doBossLogin";
    private static final String UN_CHECK_TRANS_USER_REGISTER = "userRegister";
    private static final String UN_CHECK_TRANS_RESET＿PASSWORD = "userResetPassword";

    /**
     * 检查是否为后台登录
     *
     * @param strServiceName
     * @param strTransName
     * @return
     */
    private boolean checkIsBossLogin(String strServiceName, String strTransName) {
        return (null != strServiceName
                && (UN_CHECK_SERVICE_EMPLOYEE_SERVICE.equals(strServiceName) ||
                UN_CHECK_SERVICE_USER_REGISTER_SERVICE.equals(strServiceName) ||
                UN_CHECK_SERVICE＿USER＿RESET＿PASSWORD_SERVICE.equals(strServiceName))
                && (UN_CHECK_TRANS_BOSS_BEFORE.equals(strTransName) ||
                UN_CHECK_TRANS_DO_BOSS.equals(strTransName) ||
                UN_CHECK_TRANS_USER_REGISTER.equals(strTransName) ||
                UN_CHECK_TRANS_RESET＿PASSWORD.equals(strTransName)));
    }


    private static final String EMPLOYEE_ID_KEY = "employeeId";
    private static final String TOKEN_KEY = "token";
    private static final String LAST_VISIT_TIME = "lastVisitTime";
    private static final String REDIRECT_GOTO_PAGE = "/login.htm";

    /**
     * 跳转到登录页面
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param rootPath
     * @throws IOException
     */
    private void gotoLogin(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           String rootPath) throws IOException {
        //清cookie
        String strCookieDomain = Utility.getCommonCookieDomain(httpServletRequest);
        Utility.clearAllCookie(httpServletRequest, httpServletResponse, strCookieDomain);

        String redirectuUrl = rootPath + REDIRECT_GOTO_PAGE;
        httpServletResponse.sendRedirect(redirectuUrl);
    }

    private static String[] uncheckCookiePageArray;

    static {
        uncheckCookiePageArray = new String[]{"login.htm", "register.htm", "retrievePwd.htm","index.htm","about.htm","buyFlow.htm"
        		,"buyProduct.htm","developDoc.htm","invest.htm","landlord.htm","mediaNews.htm","openAccount.htm","partner.htm","plan.htm"
        		,"productList.htm","capitalDemo.htm","AssetsDemo.htm"};
    }

    /**
     * cookie路径过滤
     *
     * @param requestPath
     * @return
     */
    private static boolean checkOpenPageCookie(String requestPath) {
        boolean val = true;
        String path = requestPath.substring(requestPath.lastIndexOf("/") + 1, requestPath.length());
        for (String uncheckPage : uncheckCookiePageArray) {
            if (path.equals(uncheckPage)) {
                val = false;
                break;
            }
        }
        return val;
    }

    /**
     * boss后台登录验证
     *
     * @param request
     * @param response
     * @return
     */
    private int isValidCookie(HttpServletRequest request, HttpServletResponse response) {
        //获取用户sessionID
        String jsessionid = request.getSession().getId();
        if (logger.isDebugEnabled()) {
            logger.debug("用户跳转页面sessionId:{}", jsessionid);
        }

        String strEmployeeId = "";
        String strToken = "";


        //获取cookie userid
        Cookie cookie = Utility.getCookie(request, Constant.COOKIE_NAME_EMPLOYEEID);
        if (cookie != null) {
            strEmployeeId = cookie.getValue();
            try {
                strEmployeeId = URLDecoder.decode(strEmployeeId, "utf-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
                return -1;
            }
        }
        if (strEmployeeId.length() == 0) {
            logger.error(Constant.COOKIE_NAME_EMPLOYEEID + " 不存在");
            return -1;
        }

        // 获取cookie token
        DES des = Utility.getDES(request.getSession().getServletContext(), request.getSession(), Constant.COOKIE_DES_KEY);
        Cookie cToken = Utility.getCookie(request, Constant.COOKIE_NAME_TOKEN);
        if (cToken != null) {
            strToken = cToken.getValue();
            try {
                strToken = URLDecoder.decode(strToken, "utf-8");
                strToken = Utility.DESDecrypt(strToken, des);// 解密
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
                return -1;
            }
        }
        if (strToken.equals("") == true) {
            logger.error(Constant.COOKIE_NAME_TOKEN + " 不存在");
            return -1;
        }

        // 验证token
        String[] tokenArr = strToken.split("#");
        if (tokenArr.length >= 2 && tokenArr[0] != null && tokenArr[1] != null) {
            if (tokenArr[0].equals(strEmployeeId) == false) {
                logger.error("不是同一个人登陆,已经篡改cookie信息");
                return -1;
            }

            /*
             * 踢人 TODO 暂时去掉踢人操作
            String redisKey = Constant.REDIS_bossJsessionid + strEmployeeId;
            String sysJsessionid = RedisInstance.getIns().get(redisKey);
            if (StringUtils.isEmpty(sysJsessionid) || !sysJsessionid.equalsIgnoreCase(jsessionid)) {
                logger.error("重新登录,jsessionid=" + jsessionid);

                //同时在redis里面清除
                if (RedisInstance.getIns().exists(redisKey)) {
                    RedisInstance.getIns().del(redisKey);
                }
                return -1;
            }*/

            CDO cdoLoginer = new CDO();
            cdoLoginer.setLongValue(Constant.Key_Boss_lEmployeeId, Long.parseLong(tokenArr[0]));
            cdoLoginer.setStringValue(Constant.Key_Boss_strMobile, tokenArr[1]);
            cdoLoginer.setStringValue(Constant.Key_Boss_strName, tokenArr[2]);

            //获取cookie bossToken,分配权限
            Cookie bossToken = Utility.getCookie(request, Constant.COOKIE_NAME_BOSSTOKEN);
            if (bossToken != null) {
                String strUserToken = "";
                strUserToken = bossToken.getValue();
                try {
                    strUserToken = URLDecoder.decode(strUserToken, "utf-8");
                    strUserToken = Utility.DESDecrypt(strUserToken, des); //解密
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                    return -1;
                }

                //分配用户权限
                String[] aBossToken = strUserToken.split("#");
                cdoLoginer.setLongValue(Constant.Key_Boss_nRoleId, Integer.parseInt(aBossToken[1]));
                cdoLoginer.setStringValue(Constant.Key_Boss_strRoleName, aBossToken[2]);
                cdoLoginer.setStringValue(Constant.Key_Boss_strPermission, aBossToken[3]);
            }
            request.setAttribute(Constant.Key_cdoBossUser, cdoLoginer);

            //判断是否超时
            String strCookieDomain = Utility.getCommonCookieDomain(request);
            String requestURL = request.getRequestURL().toString();
            if (requestURL.endsWith("header.htm") == false && requestURL.endsWith("footer.htm") == false && requestURL.endsWith("menu.htm") == false && Utility.updateVisitLasttime(request, response, strCookieDomain) == false) {// 超时，footer和header不判断超时
                return -2;
            }
        } else {
            return -1;
        }

        // 成功
        return 1;
    }

    @Override
    public void init(FilterConfig arg0) {
    }

    @Override
    public void destroy() {
    }
}
