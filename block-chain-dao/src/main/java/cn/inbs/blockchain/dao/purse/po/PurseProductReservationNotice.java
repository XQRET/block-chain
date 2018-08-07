package cn.inbs.blockchain.dao.purse.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @Description: 产品预售通知用户
 * @Package: cn.inbs.blockchain.dao.purse.po
 * @ClassName: PurseProductReservationNotice
 * @Author: zhangmingyang
 * @CreateDate: 2018/8/1 14:19
 * @Version: 1.0
 */
public class PurseProductReservationNotice extends BaseDaoBean {
    private Long id;//ID
    private Long purseUserId;//订阅钱包用户ID
    private Long productId;//订阅产品id
    private String sendMessageMobile;//发送短信手机号
    private String reservationNoticeName;//订阅人姓名
    private String sendMessageStatus;//0-未发送,1-已发送
    private String reservationNoticeStatus;//0-已订阅,1-取消订阅
    private Date reservationNoticeTime;//预定时间
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurseUserId() {
        return purseUserId;
    }

    public void setPurseUserId(Long purseUserId) {
        this.purseUserId = purseUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSendMessageMobile() {
        return sendMessageMobile;
    }

    public void setSendMessageMobile(String sendMessageMobile) {
        this.sendMessageMobile = sendMessageMobile;
    }

    public String getReservationNoticeName() {
        return reservationNoticeName;
    }

    public void setReservationNoticeName(String reservationNoticeName) {
        this.reservationNoticeName = reservationNoticeName;
    }

    public String getSendMessageStatus() {
        return sendMessageStatus;
    }

    public void setSendMessageStatus(String sendMessageStatus) {
        this.sendMessageStatus = sendMessageStatus;
    }

    public String getReservationNoticeStatus() {
        return reservationNoticeStatus;
    }

    public void setReservationNoticeStatus(String reservationNoticeStatus) {
        this.reservationNoticeStatus = reservationNoticeStatus;
    }

    public Date getReservationNoticeTime() {
        return reservationNoticeTime;
    }

    public void setReservationNoticeTime(Date reservationNoticeTime) {
        this.reservationNoticeTime = reservationNoticeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
