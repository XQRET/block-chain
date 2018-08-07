package cn.inbs.blockchain.common.web;

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

    private int resultCode = SUCCESS_CODE;//状态码
    private String resultMessage = SUCCESS_MESSAGE;//必要的提示信息
    private Object resultDate;//业务数据

    public OutputJson(Object resultDate) {
        this.resultDate = resultDate;
    }

    public OutputJson(int resultCode, String resultMessage, Object resultDate) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultDate = resultDate;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getResultDate() {
        return resultDate;
    }

    public void setResultDate(Object resultDate) {
        this.resultDate = resultDate;
    }

    @Override
    public String toString() {
        if (null == this.resultDate) {
            this.setResultDate(new Object());
        }
        return JSON.toJSONString(this);
    }
}
