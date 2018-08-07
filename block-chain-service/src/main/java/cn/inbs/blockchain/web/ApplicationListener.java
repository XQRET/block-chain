package cn.inbs.blockchain.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import cn.inbs.blockchain.common.utils.SpringContextUtil;
import cn.inbs.blockchain.service.schedulejob.IScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.cdoframework.cdolib.base.Return;

import cn.inbs.blockchain.common.utils.RedisInstance;

/**
 * @author zhangmingyang
 */
public class ApplicationListener extends ContextLoaderListener implements ServletRequestListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //启动spring容器
        super.contextInitialized(arg0);

        //启动CDO实例
        BusinessService service = BusinessService.getInstance();
        Return ret = service.start();
        if (ret.getCode() != 0) {
            if (logger.isErrorEnabled()) {
                logger.error("block-chain-service start cdo BusinessService failed , cause by : {}",
                        ret.getText());
            }
            return;
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("block-chain-service start cdo BusinessService successed .");
            }
        }

        //启动redis实例
        boolean bRedisRes = RedisInstance.getIns().start();
        if (!bRedisRes) {
            if (logger.isErrorEnabled()) {
                logger.error("block-chain-service start RedisInstance failed .");
            }
            return;
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("block-chain-service start RedisInstance successed .");
            }
        }

        //启动加载所有定时任务
        try {
            Object scheduleJobServiceObject = SpringContextUtil.getBean("scheduleJobService");
            if (null != scheduleJobServiceObject) {
                IScheduleJobService scheduleJobService = (IScheduleJobService) scheduleJobServiceObject;
                scheduleJobService.updateSeverStartedInitStartJobs();

                if (logger.isInfoEnabled()) {
                    logger.info("block-chain-service start scheduleJob successed .");
                }
            } else {

                if (logger.isErrorEnabled()) {
                    logger.error("block-chain-service start scheduleJob failed . cause by : context get bean :[scheduleJobService] is null .");
                }
                return;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("block-chain-service start scheduleJob failed . cause by : ", e);
            }
            return;
        }

        //记录服务启动成功日志
        if (logger.isInfoEnabled()) {
            logger.info("**************************************************************************************");
            logger.info("********************block-chain-service server started successed .********************");
            logger.info("**************************************************************************************");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //停止定时任务
        try {
            Object scheduleJobServiceObject = SpringContextUtil.getBean("scheduleJobService");
            if (null != scheduleJobServiceObject) {
                IScheduleJobService scheduleJobService = (IScheduleJobService) scheduleJobServiceObject;
                scheduleJobService.updateSeverDestroyStopJobs();

                if (logger.isInfoEnabled()) {
                    logger.info("block-chain-service stop scheduleJob successed .");
                }
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("block-chain-service stop scheduleJob failed . cause by : ", e);
            }
            return;
        }

        //销毁spring容器
        super.contextDestroyed(arg0);

        //停止redis
        BusinessService service = BusinessService.getInstance();
        if (!service.isRunning()) {
            if (logger.isErrorEnabled()) {
                logger.error("block-chain-service stop RedisInstance failed .");
            }
            return;
        } else {
            service.stop();
            if (logger.isInfoEnabled()) {
                logger.info("block-chain-service stop RedisInstance successed .");
            }
        }

        //记录服务销毁成功日志
        if (logger.isInfoEnabled()) {
            logger.info("**************************************************************************************");
            logger.info("********************block-chain-service server stoped successed .*********************");
            logger.info("**************************************************************************************");
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
        HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
        String strClientIP = request.getRemoteAddr();
        String strURL = request.getRequestURL().toString();
        if (!strURL.endsWith(".htm")) {
            return;
        }

        if (request.getQueryString() != null) {
            strURL += "?" + request.getQueryString();
        }
        if (logger.isInfoEnabled()) {
            logger.info(strClientIP + ": " + strURL);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
    }
}
