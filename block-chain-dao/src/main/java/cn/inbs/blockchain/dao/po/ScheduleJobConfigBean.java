package cn.inbs.blockchain.dao.po;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 定时任务配置bean
 * @Package: cn.inbs.blockchain.common.schedulejob.config
 * @ClassName: ScheduleJobConfigBean.java
 * @Date: 2018/6/15 14:47
 * @author: create by zhangmingyang
 **/
public class ScheduleJobConfigBean implements Serializable {
    public static final String SCHEDULEJOB_KEY = "scheduleJob";//存储在JobDataMap里面的key,方便以后在jobDetail取出

    /**
     *
     */
    public static final String STATUS_RUNNING = "1";//表示运行
    public static final String STATUS_NOT_RUNNING = "0";//表示不运行

    /**
     *
     */
    public static final String CONCURRENT_IS = "1";//表示走的是无状态的执行，就是不管你执没执行完，我都按照轮询的时间走
    public static final String CONCURRENT_NOT = "0";//表示走的是有状态的执行,也就是会等待当前执行完毕以后再去轮询


    /**
     *
     */
    private Long jobId;//任务编号
    private String jobGroup;//任务组名称
    private String jobName;//任务名称
    private String description;//描述
    private String jobStatus;//任务状态 是否启动任务  1-启动，0-未启动
    private String cronExpression;//cron表达式
    private String isConcurrent;//任务是否有状态
    private String springId;//spring bean
    private String operationDes = "无状态";//运行状态
    private Long count;//执行次数
    private Date startTime;//上次任务开始时间
    private Date stopTime;//上次任务结束时间
    private Date createTime;//任务创建时间s
    private Date updateTime;//任务结束时间

    /**
     * 包含组名和任务名的
     *
     * @return
     */
    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(this.getJobGroup())
                .append(".")
                .append(this.getJobName());
        return stringBuilder.toString();
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId;
    }

    public String getOperationDes() {
        return operationDes;
    }

    public void setOperationDes(String operationDes) {
        this.operationDes = operationDes;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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
        return "定时任务配置信息:" + JSON.toJSONString(this);
    }
}
