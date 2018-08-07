package cn.inbs.blockchainpurse.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: Controller基类
 * @Package: cn.inbs.blockchain.common.web
 * @ClassName:
 * @Date: 2018年04月27-11:05
 * @Author: createBy:zhangmingyang
 **/
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 返回统一调用该方法
     *
     * @param data
     * @return
     */
    protected String retContent(Object data) {
        return new OutputJson(data).toString();
    }

    /**
     * 返回统一调用该方法
     *
     * @param data
     * @return
     */
    protected String retContent(Object data, int code, String message) {
        return new OutputJson(code, message, data).toString();
    }

    /**
     * 获取根目录，如果有项目名，会带上项目名
     *
     * @param request
     * @return
     */
    protected String getRootPath(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).toString();
    }
}
