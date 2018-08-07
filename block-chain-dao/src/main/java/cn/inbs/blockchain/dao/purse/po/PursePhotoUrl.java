package cn.inbs.blockchain.dao.purse.po;

import cn.inbs.blockchain.dao.BaseDaoBean;
import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @Description: 钱包图片信息
 * @Package: cn.inbs.blockchain.dao.purse.po
 * @ClassName: PursePhotoUrl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 13:20
 * @Version: 1.0
 */
public class PursePhotoUrl extends BaseDaoBean {
    /**
     * 用户图片
     */
    public static final String PURSE_USER_PHOTO_URL = "1";

    private Long id;//图片ID
    private Long purseUserId;//钱包用户ID
    private String type;//图片类型(1-钱包图像)
    private String url;//图片url
    private String orderCode;//排序编码
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
