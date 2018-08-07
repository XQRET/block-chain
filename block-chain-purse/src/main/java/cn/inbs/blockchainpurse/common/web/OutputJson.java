package cn.inbs.blockchainpurse.common.web;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


/**
 * @Description: 返回统一数据结构
 * @Package: cn.inbs.blockchain.common.web
 * @ClassName:
 * @Date: 2018年04月27-10:38
 * @Author: createBy:zhangmingyang
 **/
public class OutputJson implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SYS_EXCEPTION_MESSAGE = "异常,请稍后重试!!!";//系统异常消息定义
    public static final String SUCCESS_MESSAGE = "成功";//执行成功消息

    public static final int SUCCESS_CODE = 0;//全局成功
    public static final int FAILED_CODE = -1;//全局失败
    public static final int FAILED_TOKEN_NULL = -77;//token为空
    public static final int FAILED_OFF_SITE_LOGININ = -88;//用户异地登录
    public static final int FAILED_LOGIN_TIMEOUT = -99;//登录失效

    private int code = SUCCESS_CODE;//状态码
    private String message = SUCCESS_MESSAGE;//必要的提示信息
    private Object data;//业务数据

    public OutputJson(Object data) {
        this.data = data;
    }

    public OutputJson(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (null == this.data) {
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
