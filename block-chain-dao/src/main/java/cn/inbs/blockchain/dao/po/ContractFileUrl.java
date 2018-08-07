package cn.inbs.blockchain.dao.po;

import cn.inbs.blockchain.dao.BaseDaoBean;

import java.util.Date;

/**
 * 
 * ClassName: ContractFileUrl <br/>  
 * Description: 合约文件上传. <br/>  
 * @author anxiaoyu  
 * Date:2018年6月5日上午10:32:10
 */
public class ContractFileUrl extends BaseDaoBean {
    /**  
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
	 */
	private static final long serialVersionUID = 1L;
	
	/** ID. */
	private Long id;

	/** 合约编号. */
    private String contractId;

    /** 文件所属模块. */
    private String fileMode;

    /** 文件类型. */
    private String fileType;

    /** 文件URL. */
    private String fileUrl;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;
    
    /**
	 * Gets the iD.
	 *
	 * @return the iD
	 */
    public Long getId() {
        return id;
    }

    /**
	 * Sets the iD.
	 *
	 * @param id
	 *            the new iD
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * Gets the 合约编号.
	 *
	 * @return the 合约编号
	 */
    public String getContractId() {
        return contractId;
    }

    /**
	 * Sets the 合约编号.
	 *
	 * @param contractId
	 *            the new 合约编号
	 */
    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    /**
	 * Gets the 文件所属模块.
	 *
	 * @return the 文件所属模块
	 */
    public String getFileMode() {
        return fileMode;
    }

    /**
	 * Sets the 文件所属模块.
	 *
	 * @param fileMode
	 *            the new 文件所属模块
	 */
    public void setFileMode(String fileMode) {
        this.fileMode = fileMode == null ? null : fileMode.trim();
    }

    /**
	 * Gets the 文件类型.
	 *
	 * @return the 文件类型
	 */
    public String getFileType() {
        return fileType;
    }

    /**
	 * Sets the 文件类型.
	 *
	 * @param fileType
	 *            the new 文件类型
	 */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
	 * Gets the 文件URL.
	 *
	 * @return the 文件URL
	 */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
	 * Sets the 文件URL.
	 *
	 * @param fileUrl
	 *            the new 文件URL
	 */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    /**
	 * Gets the 创建时间.
	 *
	 * @return the 创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }

    /**
	 * Sets the 创建时间.
	 *
	 * @param createTime
	 *            the new 创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
	 * Gets the 更新时间.
	 *
	 * @return the 更新时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
	 * Sets the 更新时间.
	 *
	 * @param updateTime
	 *            the new 更新时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    
}