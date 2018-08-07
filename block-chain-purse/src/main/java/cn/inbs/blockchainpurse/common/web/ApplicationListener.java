package cn.inbs.blockchainpurse.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @Description: spring启动监听器
 * @Package: cn.inbs.blockchainpurse.common.web
 * @ClassName: ApplicationListener
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 17:04
 * @Version: 1.0
 */
public class ApplicationListener extends ContextLoaderListener implements ServletRequestListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //启动spring容器
        super.contextInitialized(arg0);

        //记录服务启动成功日志
        if (logger.isInfoEnabled()) {
            logger.info("**************************************************************************************");
            logger.info("********************block-chain-purse server started successed .**********************");
            logger.info("**************************************************************************************");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        //销毁spring容器
        super.contextDestroyed(arg0);

        //记录服务销毁成功日志
        if (logger.isInfoEnabled()) {
            logger.info("**************************************************************************************");
            logger.info("********************block-chain-purse server stoped successed .***********************");
            logger.info("**************************************************************************************");
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
    }
}
