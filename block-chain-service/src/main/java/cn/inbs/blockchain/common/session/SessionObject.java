package cn.inbs.blockchain.common.session;

import cn.inbs.blockchain.common.cache.BaseCacheBean;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.common.session
 * @ClassName:
 * @Date: 2018年04月26-18:38
 * @Author: createBy:zhangmingyang
 **/
public class SessionObject extends BaseCacheBean {
    private static final long serialVersionUID = 1L;
    private String sessionId;//回话ID
    private long userId;//用户ID

    public SessionObject() {
        super();
    }

    public SessionObject(long userId) {
        super();
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
