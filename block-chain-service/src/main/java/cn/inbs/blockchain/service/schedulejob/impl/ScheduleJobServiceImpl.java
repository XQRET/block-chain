package cn.inbs.blockchain.service.schedulejob.impl;

import cn.inbs.blockchain.common.exception.BusinessErrorConstants;
import cn.inbs.blockchain.common.exception.BusinessException;
import cn.inbs.blockchain.common.schedulejob.IScheduleJobControlCenterService;
import cn.inbs.blockchain.common.schedulejob.jobcenter.IScheduleJob;
import cn.inbs.blockchain.common.utils.CollectionUtils;
import cn.inbs.blockchain.common.utils.SpringContextUtil;
import cn.inbs.blockchain.common.utils.StringUtils;
import cn.inbs.blockchain.common.commonbean.PagePo;
import cn.inbs.blockchain.dao.po.ScheduleJobConfigBean;
import cn.inbs.blockchain.dao.schedulejob.ScheduleJobMapper;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Package: cn.inbs.blockchain.service.schedulejob.impl
 * @ClassName: ScheduleJobServiceImpl.java
 * @Date: 2018/6/15 16:25
 * @author: create by zhangmingyang
 **/
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements IScheduleJobService {
    private static Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    @Resource
    private IScheduleJobControlCenterService scheduleJobControlCenterService;

    @Override
    public void insertNewScheduleJob(ScheduleJobConfigBean scheduleJobConfigBean) {
        //判断是否存在该定时任务
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        query.setJobName(scheduleJobConfigBean.getJobName());//任务名称
        query.setJobGroup(scheduleJobConfigBean.getJobGroup());//任务组
        query.setSpringId(scheduleJobConfigBean.getSpringId());//beanId
        List<ScheduleJobConfigBean> scheduleJobConfigBeans = scheduleJobMapper.selectScheduleJob(query);
        if (CollectionUtils.isNotEmpty(scheduleJobConfigBeans)) {
            /*=====已存在=====*/
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0007,
                    scheduleJobConfigBean.getName());
        } else {
            /*=====不存在=====*/
            //定时任务bean必须实现[IScheduleJob]接口
            Object bean = SpringContextUtil.getBean(scheduleJobConfigBean.getSpringId());
            if (null == bean) {
                throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0008,
                        scheduleJobConfigBean.getSpringId(),
                        scheduleJobConfigBean.getSpringId());
            } else {
                if (!(bean instanceof IScheduleJob)) {
                    throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0009,
                            scheduleJobConfigBean.getSpringId());
                } else {
                    //添加配置定时任务
                    int count = scheduleJobMapper.insertScheduleJob(scheduleJobConfigBean);
                    if (1 != count) {
                        throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0010,
                                scheduleJobConfigBean.getName());
                    } else {
                        if (logger.isInfoEnabled()) {
                            logger.info("配置定时任务:[{}]成功", scheduleJobConfigBean.getName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public PagePo queryScheduleJobPage(PagePo pagePo) {
        HashMap<String, Object> conditionParamMap = pagePo.getConditionParamMap();
        if (null == conditionParamMap) {
            conditionParamMap = new HashMap<String, Object>();
        }

        //分页查询条件
        conditionParamMap.put(PagePo.PAGE_CONDITION_START_INDEX, pagePo.getPageStartCountIndex());
        conditionParamMap.put(PagePo.PAGE_CONDITION_PAGE_COUNT, pagePo.getPageCount());

        //查询分页数据
        List<ScheduleJobConfigBean> scheduleJobConfigBeans = scheduleJobMapper.selectPageList(conditionParamMap);
        if (CollectionUtils.isEmpty(scheduleJobConfigBeans)) {
            return pagePo;
        } else {
            //运行定时任务状态
            for (ScheduleJobConfigBean scheduleJobConfigBean : scheduleJobConfigBeans) {
                if (ScheduleJobConfigBean.STATUS_RUNNING.equals(scheduleJobConfigBean.getJobStatus())) {
                    String runningJobStatus = scheduleJobControlCenterService.getRunningJobStatus(scheduleJobConfigBean);
                    if (StringUtils.isNotEmpty(runningJobStatus)) {
                        scheduleJobConfigBean.setOperationDes(runningJobStatus);
                    }
                }
            }

            //查询总行数
            pagePo.setPageInfoList(scheduleJobConfigBeans);
            Integer totalCount = scheduleJobMapper.selectTotalCount(conditionParamMap);
            pagePo.setTotalCount(totalCount);
            return pagePo;
        }
    }

    @Override
    public void updateJobByStart(ScheduleJobConfigBean scheduleJobConfigBean) {
        //查询当前定时任务信息
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        query.setJobId(scheduleJobConfigBean.getJobId());
        query = scheduleJobMapper.selectScheduleJob(query).get(0);

        //记录定时任务信息
        ScheduleJobConfigBean update = new ScheduleJobConfigBean();
        Date updateDate = new Date();
        update.setJobId(query.getJobId());
        update.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
        update.setUpdateTime(updateDate);
        update.setCount(0L);
        update.setStartTime(updateDate);
        int count = scheduleJobMapper.updateScheduleJobConfigBean(update);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0013, query.getName());
        } else {
            //发起定时任务
            query.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
            scheduleJobControlCenterService.addAndExecuteNewJob(query);

            if (logger.isInfoEnabled()) {
                logger.info("开启定时任务:[{}]成功", query.getName());
            }
        }
    }

    @Override
    public void updateJobByStop(ScheduleJobConfigBean scheduleJobConfigBean) {
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        query.setJobId(scheduleJobConfigBean.getJobId());
        query = scheduleJobMapper.selectScheduleJob(query).get(0);

        //记录定时任务信息
        Date updateDate = new Date();
        ScheduleJobConfigBean update = new ScheduleJobConfigBean();
        update.setJobId(query.getJobId());
        update.setJobStatus(ScheduleJobConfigBean.STATUS_NOT_RUNNING);
        update.setStopTime(updateDate);
        update.setUpdateTime(updateDate);
        int count = scheduleJobMapper.updateScheduleJobConfigBean(update);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0012, query.getName());
        } else {
            scheduleJobControlCenterService.stopAndDeleteJobInFactory(query);
            if (logger.isInfoEnabled()) {
                logger.info("停止定时任务:[{}]成功", query.getName());
            }
        }
    }

    @Override
    public void updateJobCronExpression(ScheduleJobConfigBean scheduleJobConfigBean) {
        //查询当前定时任务配置
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        query.setJobId(scheduleJobConfigBean.getJobId());
        query = scheduleJobMapper.selectScheduleJob(query).get(0);

        //记录
        ScheduleJobConfigBean update = new ScheduleJobConfigBean();
        update.setJobId(query.getJobId());
        update.setCronExpression(scheduleJobConfigBean.getCronExpression().trim());
        update.setUpdateTime(new Date());
        int count = scheduleJobMapper.updateScheduleJobConfigBean(update);
        if (1 != count) {
            throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0014, query.getName());
        } else {
            //当前任务还在运行时
            if (ScheduleJobConfigBean.STATUS_RUNNING.equals(query.getJobStatus())) {
                query.setCronExpression(scheduleJobConfigBean.getCronExpression());
                scheduleJobControlCenterService.refreshJobTriggerCron(query);

                if (logger.isInfoEnabled()) {
                    logger.info("修改定时任务:[{}] 表达式成功", query.getName());
                }
            }
        }
    }

    @Override
    public void updateSeverStartedInitStartJobs() {
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        List<ScheduleJobConfigBean> scheduleJobConfigBeans = scheduleJobMapper.selectScheduleJob(query);
        if (CollectionUtils.isNotEmpty(scheduleJobConfigBeans)) {
            Date startDate = new Date();
            for (ScheduleJobConfigBean scheduleJobConfigBean : scheduleJobConfigBeans) {
                //修改定时任务状态
                ScheduleJobConfigBean update = new ScheduleJobConfigBean();
                update.setJobId(scheduleJobConfigBean.getJobId());
                update.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
                update.setUpdateTime(startDate);
                update.setCount(0L);
                update.setStartTime(startDate);
                int count = scheduleJobMapper.updateScheduleJobConfigBean(update);

                //拉起定时任务
                if (1 != count) {
                    throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0013, query.getName());
                } else {
                    //发起定时任务
                    scheduleJobConfigBean.setJobStatus(ScheduleJobConfigBean.STATUS_RUNNING);
                    scheduleJobControlCenterService.addAndExecuteNewJob(scheduleJobConfigBean);

                    if (logger.isInfoEnabled()) {
                        logger.info("容器启动拉起定时任务:[{}]成功", scheduleJobConfigBean.getName());
                    }
                }
            }
        }
    }

    @Override
    public void updateSeverDestroyStopJobs() {
        ScheduleJobConfigBean query = new ScheduleJobConfigBean();
        List<ScheduleJobConfigBean> scheduleJobConfigBeans = scheduleJobMapper.selectScheduleJob(query);
        if (CollectionUtils.isNotEmpty(scheduleJobConfigBeans)) {
            Date stopDate = new Date();
            for (ScheduleJobConfigBean scheduleJobConfigBean : scheduleJobConfigBeans) {
                if (ScheduleJobConfigBean.STATUS_RUNNING.equals(scheduleJobConfigBean.getJobStatus())) {
                    ScheduleJobConfigBean update = new ScheduleJobConfigBean();
                    update.setJobId(scheduleJobConfigBean.getJobId());
                    update.setJobStatus(ScheduleJobConfigBean.STATUS_NOT_RUNNING);
                    update.setStopTime(stopDate);
                    update.setUpdateTime(stopDate);
                    int count = scheduleJobMapper.updateScheduleJobConfigBean(update);
                    if (1 != count) {
                        throw new BusinessException(BusinessErrorConstants.SCHEDULE_JOB_0012, query.getName());
                    } else {
                        scheduleJobControlCenterService.stopAndDeleteJobInFactory(scheduleJobConfigBean);
                        if (logger.isInfoEnabled()) {
                            logger.info("容器销毁,停止定时任务:[{}]成功", scheduleJobConfigBean.getName());
                        }
                    }
                }
            }
        }
    }
}
